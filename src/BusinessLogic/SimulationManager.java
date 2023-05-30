package BusinessLogic;

import GUI.View;
import Model.Task;
import Model.Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import java.util.*;
import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimulationManager extends Thread{

    private Scheduler scheduler;
    private View simulationView;
    private List<Task> generatedTasks;

    public int minServiceTime=4;
    public int maxServiceTime=6;
    public int limitTime=30;
    public int minArrivalTime=2;
    public int maxArrivalTime=8;
    public int numberOfTasks=8;
    public int numberOfServers=3;

    private StringBuilder sb;

    FileWriter fileWriter = Scheduler.getPrintIntoFile();

    public SimulationManager( int numberOfTasks ,  int numberOfServers, int limitTime ,
                              int minArrivalTime,int maxArrivalTime, int minServiceTime, int maxServiceTime, View v1)
    {
        this.simulationView=v1;
        this.numberOfTasks = numberOfTasks;
        this.numberOfServers= numberOfServers;
        this.limitTime = limitTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime=maxArrivalTime;
        this.minServiceTime=minServiceTime;
        this.maxServiceTime=maxServiceTime;
        generatedTasks = Collections.synchronizedList(new ArrayList<>());
        this.scheduler = new Scheduler(numberOfServers, numberOfTasks);
        this.sb = new StringBuilder();

        for (int i = 0; i < numberOfServers; i++) {

            Thread myThread = new Thread(scheduler.getServers().get(i));
            myThread.start();
        }
        generatedTasks=generateRandomTasks();
        fileWriter = scheduler.getPrintIntoFile();
    }

    public SimulationManager(){};

    public List<Task> generateRandomTasks()
    {
            Random rand = new Random();
            generatedTasks = new ArrayList<>();
            for (int i=0 ; i< numberOfTasks ; i++)
            {
                Task t = new Task(1+i, rand.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime, rand.nextInt(maxServiceTime - minServiceTime) + minServiceTime);
                generatedTasks.add(t);
            }
            Collections.sort(generatedTasks);
            return generatedTasks;
    }

    public static double calculateAverageWaitingTime()
    {
        return Server.getTotalWaitingTime()/Server.getCountTask();
    }

    public static double calcalateAverageServiceTime()
    {
        return Server.getTotalServingTime()/Server.getCountTask();
    }

    public void printServersData()
    {
        for(int i=0 ; i<scheduler.getServers().size();i++)
        {
            Server ser=scheduler.getServers().get(i);
            BlockingQueue<Task> tasks = new ArrayBlockingQueue<>(scheduler.getMaxTasksPerServer());

            tasks.addAll(ser.getTasks());
            try {
                fileWriter.append("\nQueue:" + (i+1) + " " + tasks + " there are " + tasks.size() + " tasks");
                System.out.print("\nQueue: " + (i+1) + " " + tasks + " there are " + tasks.size() + " tasks");
                sb.append("\nQueue:" + (i+1) + " " + tasks + " there are " + tasks.size() + " tasks");
                simulationView.addText(sb.toString());
            }catch (IOException  e){
                throw new RuntimeException(e);
            }
        }
        try{
            fileWriter.append("\n");
            System.out.print("\n");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void run() {
        int currentTime = 0;
        int peakHour=0;
        int peakHourQueue=0;
        while (currentTime  < limitTime) {
            Iterator<Task> ListTasks = generatedTasks.iterator();
            while (ListTasks.hasNext()) {
                Task t = ListTasks.next();
                if (t.getArrivalTime() == currentTime) {
                    try {
                        if(scheduler.dispatchTask(t)) {
                            ListTasks.remove();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                fileWriter.append("\nTime " + currentTime + "\nleft Tasks: ");
                System.out.print("\nTime " + currentTime + "\nleft Tasks: ");
                sb.append("\nTime " + currentTime + "\nleft Tasks: ");
                simulationView.addText(sb.toString());
                for (Task task : generatedTasks) {
                    fileWriter.append(""+task);
                    System.out.print(task);
                    sb.append(""+task);

                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            this.printServersData();
            currentTime++;
            for(Server server: scheduler.getServers()){
                if(server.getTasks().size()>=peakHour){
                    peakHourQueue=peakHour;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter.append("\nNot Served Tasks: " + generatedTasks);
            System.out.print("\nNot Served Tasks: " + generatedTasks);
            sb.append("\nNot Served Tasks: " + generatedTasks);
            fileWriter.append("\nAverage waiting time: "+this.calculateAverageWaitingTime());
            fileWriter.append("\nAverage Service time: "+this.calcalateAverageServiceTime());
            fileWriter.append("\nPeak hour: "+peakHourQueue);
            fileWriter.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        System.exit(0);
    }




}
