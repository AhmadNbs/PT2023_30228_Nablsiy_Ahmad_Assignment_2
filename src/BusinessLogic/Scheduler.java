package BusinessLogic;

import Model.Server;
import Model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;

    private List <Thread> threadsServer;
    static  FileWriter printIntoFile;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        try {
            printIntoFile = new FileWriter("OutPutData.txt");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<>();
        this.threadsServer = new ArrayList<>();

        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server(maxTasksPerServer);
            servers.add(server);
            threadsServer.add(new Thread(server));
        }
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public static FileWriter getPrintIntoFile(){
        return printIntoFile;
    }


    public boolean dispatchTask(Task task) throws InterruptedException {
        int currrentPeriod = servers.get(0).getWaitingPeriod().get();
        int poz = 0;

        for (int i = 1; i < servers.size(); i++) {
            Server server = servers.get(i);
            int waitingPeriod = server.getWaitingPeriod().get();
            if (waitingPeriod < currrentPeriod) {
                currrentPeriod = waitingPeriod;
                poz = i;
            }
        }
        if(servers.get(poz).addTask(task)){
            return true;
        }else{
            return false;
        }
    }

    public List<Server> getServers()
    {
        return servers;
    }
}
