/************************************************************************************
 *  Program:      Queue.java                                                        *
 *  Date:         May  1,  2019                                                     *
 *  Purpose:      Data type for storing information                                 *
 *  Author:       Tanyaradzwa Matasva,  University  of  Bridgeport                  *
 *  Course:       Introduction  to  Computing  2  with  Java                        *
 *  Assignment:   Final Project : Theme Park Simulation                             *
 ************************************************************************************/
public class Queue<E> {
    private int size;
    private int front;
    private int rear;
    private E[] data;
    private int count;

    public Queue(int size){
        this.size=size;
        front=0;
        rear=-1;
        data=(E[]) new Object[size];
        count=0;
    }

    public Queue() {
        this(100);
    }

    public void enqueue(E element){
        if(!isFull()){
            rear=(rear+1)%size;
            data[rear]=element;
            this.count++;
        }
    }

    public E dequeue(){
        if(!isEmpty()){
            E val=data[front];
            front=(front+1)%size;
            this.count--;
            return val;

        }
        return null;

    }

    public  E frontValue(){
        if(!isEmpty()){
            return data[front];
        }
        return null;
    }

    public boolean isFull(){
        return count==size;
    }

    public boolean isEmpty(){
        return count==0;
    }

    public void display(){
        System.out.print(this);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toString(){
        StringBuilder string=new StringBuilder("");
        if(!isEmpty()){
           for(int i=front;i!=rear;i=((i+1)%size)){
               string.append("|").append(data[i]).append("|");
            }
            string.append("|").append(data[rear]).append("|");
        }else{
            string.append("| |");
        }

        return string.toString();
    }
}
