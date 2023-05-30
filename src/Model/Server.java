package Model;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread{

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingTasks;

    private static int countTask=0;
    private static int totalWaitingTime=0;
    private static int totalServingTime=0;

    public AtomicInteger getWaitingPeriod() {
        return waitingTasks;
    }

    public Server(int MaxNumberOfTasks){
        this.tasks = new ArrayBlockingQueue<>(MaxNumberOfTasks);
        this.waitingTasks= new AtomicInteger(0);
    }
    public Server(){}

    public BlockingQueue<Task> getTasks(){
        return tasks;
    }


    public boolean addTask(Task t) {
        this.totalServingTime=totalServingTime+(t.getServiceTime());
        ArrayList<Task> tasksLine = new ArrayList<>();
        tasksLine.addAll(tasks);
        for(Task task : tasksLine){
            totalWaitingTime=totalWaitingTime+(task.getServiceTime());
        }
        boolean ok = tasks.offer(t);
        if (!ok) {
            System.out.println("Server is full. Tasks " + t + " is not added.");
            return false;
        }else {
            this.waitingTasks.incrementAndGet();
            this.countTask++;
            return true;
        }
    }

    public static int getCountTask() {
        return countTask;
    }

    public static int getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public static int getTotalServingTime() {
        return totalServingTime;
    }


    @Override
    public void run(){
        while(true){
            try {
                if(!tasks.isEmpty()) {
                    Task nextTask = tasks.peek();
                    if(nextTask.getServiceTime()==0){
                        nextTask= tasks.take();
                    }
                    Thread.sleep(1000);
                    waitingTasks.set(tasks.size());
                    nextTask.UpdatingServiceTime();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

    }
}
