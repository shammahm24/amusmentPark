/************************************************************************************
 *  Program:      ThemePark.java                                                    *
 *  Date:         May  1,  2019                                                     *
 *  Purpose:      Contains logic for the theme park operations initializing rides   *
 *  Author:       Tanyaradzwa Matasva,  University  of  Bridgeport                  *
 *  Course:       Introduction  to  Computing  2  with  Java                        *
 *  Assignment:   Final Project : Theme Park Simulation                             *
 ************************************************************************************/
import java.util.Random;

import static java.lang.Double.NaN;

public class ThemePark {

    private static  Ride[] rides=new Ride[10];

    private  int totalGuests=0;
    private int servedGuests=0;
    private boolean open=true;
    private static Random random=new Random();
      int counter=0;
    // Driver.Stats stats=new Driver.Stats();
     //Driver driver=new Driver();

    public ThemePark() {
        rides[0]=new Ride();
        rides[1]=new Ride();
        rides[2]=new Ride();
        rides[3]=new Ride();
        rides[4]=new Ride();
        rides[5]=new Ride();
        rides[6]=new Ride();
        rides[7]=new Ride();
        rides[8]=new Ride();
        rides[9]=new Ride();

    }
    public  void run(){
        //stats.repaint();


        //set the maximum ride time for each ride
        for(int i=0;i<rides.length;i++){
         rides[i].setMaxTime(random.nextInt(11-1)+1);
        }

        while(counter<60){
            int arrivalProbability=random.nextInt(10);

            counter++;
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){

                System.out.println(e.getMessage());
            }
            if(arrivalProbability<=6) {
                int enteringGuests=random.nextInt(10-1)+1;
                int prevTotal=totalGuests;
                totalGuests+=enteringGuests;

                //String form of the guest number
                int displayedNumber=prevTotal+1;
                String numberString;
                if(displayedNumber<10){
                    numberString="00"+displayedNumber;
                }else if(displayedNumber<100){
                    numberString="0"+displayedNumber;
                }else{
                    numberString=""+displayedNumber;
                }

                //for the multiple users that enter the park at one time, select a ride for each user
                for(int i=1;i<enteringGuests;i++){
                    String guest = "guest: "+numberString;
                    int ride = random.nextInt(10);

                    System.out.println("" + counter);
                    if(arrivalProbability<=2){
                        rides[ride].addFastPassGuest(guest);
                    }else{
                        rides[ride].addGuest(guest);
                    }
                    rides[ride].setPopularity(rides[ride].getPopularity()+1);
                }

                //display current guests in each ride line
                for(int i=0;i<rides.length;i++){
                    System.out.println();
                    System.out.println("Ride number: "+i);
                    rides[i].display();
                }

                //take a guest from the queue on to the ride **pop a guest off the queue
                for(int i=0;i<rides.length;i++){
                    //rides[i].updateTime(counter);
                    if(!rides[i].isOccupied()&&rides[i].getQueueLength()>0||!rides[i].isOccupied()&&rides[i].getFastPassLength()>0){
                        rides[i].setGuestName(rides[i].getOnRide());
                        rides[i].setOccupied(true);
                        servedGuests++;
                    }
                }

                ///find a ride with someone who wants to get off and take them off the ride
                for(int i=0;i<rides.length;i++){
                    if(rides[i].isOccupied()){
                        if(counter%rides[i].getMaxTime()==0){
                            rides[i].setOccupied(false);
                            rides[i].setGuestName("_____________");
                        }
                    }
                }

            }

        }

        open=false;

    }
    public int getTime(){
        return this.counter;
    }
    public int getTotalGuests(){
        return this.totalGuests;
    }
    public int getServedGuests(){return this.servedGuests;}
    public boolean isOpen(){
        return open;
    }
    public String displayStats(int i){
        String stats="";
        /*
        go through each ride and get the information and stats
         */
            String rate;
            if(rides[i].getPopularity()>0) {
                if (((rides[i].getPopularity()) / (totalGuests / 100.0))<10.0) {
                    rate =String.format("0%.1f",((rides[i].getPopularity()) / (totalGuests / 100.0)));
                }else{
                    rate=String.format("%.1f",((rides[i].getPopularity()) / (totalGuests / 100.0)));
                }

            }else{
                rate="00.0";
            }
            String name;
            if(rides[i].getGuestName()!=null){
                name=rides[i].getGuestName();
            }else{
                name="_____________";
            }
       // stats=String.format("%20d%20d",
              //  i,rides[i].getMaxTime());
        String time;
            if(rides[i].getMaxTime()<10){
                time="0"+rides[i].getMaxTime();
            }else{
                time=""+rides[i].getMaxTime();
            }
        String lineSize;
            if(rides[i].getQueueLength()<10){
                lineSize="0"+rides[i].getQueueLength();
            }else{
                lineSize=""+rides[i].getQueueLength();
            }

        String fpSize;
        if(rides[i].getFastPassLength()<10){
            fpSize="0"+rides[i].getFastPassLength();
        }else{
            fpSize=""+rides[i].getFastPassLength();
        }

                    stats=String.format("%1$20d%2$20s%3$20b%4$20s%5$20s%6$30s%7$30s",
                i,time,rides[i].isOccupied(),lineSize,fpSize,name,rate);



         return stats;

    }
}
