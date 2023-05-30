package Model;

public class Task implements Comparable{

    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int compareTo(Object o) {
        Task task2 = (Task) o;
        if(this.arrivalTime < task2.getArrivalTime()){
            return -1;
        }
        if(this.arrivalTime== task2.getArrivalTime())
            return 0;

        return 1;
    }

    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void UpdatingServiceTime(){
        if(this.serviceTime>0) {
            this.serviceTime = this.serviceTime - 1;
        }
    }

    @Override
    public String toString() {
        return "Task(" +
                 + ID +
                ", " + arrivalTime +
                ", " + serviceTime +
                ')';
    }
}
