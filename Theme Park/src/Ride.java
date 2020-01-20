/************************************************************************************
 *  Program:      Ride.java                                                         *
 *  Date:         May  1,  2019                                                     *
 *  Purpose:      Has values and queues that make up the ride object                *
 *  Author:       Tanyaradzwa Matasva,  University  of  Bridgeport                  *
 *  Course:       Introduction  to  Computing  2  with  Java                        *
 *  Assignment:   Final Project : Theme Park Simulation                             *
 ************************************************************************************/
public class Ride {
    private Queue<String> queue;
    private Queue<String> fastPass;
    private int rideNumber;
    private String guestName;
    private int popularity;
    private boolean occupied;
    private int startTime;
    private int maxTime;



    public Ride(){
        queue=new Queue<>();
        fastPass=new Queue<>();
        rideNumber=0;
        guestName="_____________";
        occupied=false;
        popularity=0;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public Queue<String> getFastPass() {
        return fastPass;
    }

    public void setFastPass(Queue<String> fastPass) {
        this.fastPass = fastPass;
    }

    public int getRideNumber() {
        return rideNumber;
    }

    public void setRideNumber(int rideNumber) {
        this.rideNumber = rideNumber;
    }

  public void display(){
      System.out.println();
        queue.display();
      System.out.println();
      fastPass.display();
  }


    public void addGuest(String guest){
        queue.enqueue(guest);
    }

    public void addFastPassGuest(String fast){
        fastPass.enqueue(fast);
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getOnRide(){
        if(this.getFastPassLength()>0){
            return fastPass.dequeue();
        }else{
            return queue.dequeue();
        }
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
    //public void updateTime(int time){
        //this.setCurrentTime(time-this.getStartTime());
  //  }

    //get the number of people waiting in line
    public int getQueueLength(){
        return queue.getCount();
    }


    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getFastPassLength(){return fastPass.getCount();}
}
