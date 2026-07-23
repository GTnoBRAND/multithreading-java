package org.example.MonitorLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotifyWait {

    static void main() {
        Worker buffer = new Worker(7, 0);   //instance of worker class

        //actual logic
        Thread producer = new Thread(()->{
            try {
                buffer.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(()->{
            try {
                buffer.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //starting threads
        producer.start();
        consumer.start();
    }

}

class Worker{

    private int sequence = 0;
    private final Integer top;      //limited size of the array list
    private final Integer bottom;
    private final List<Integer> container;      //actual list
    private final Lock lock = new ReentrantLock();

    public Worker(Integer top, Integer bottom){
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        synchronized (lock){
            while (true){
                if(container.size() == top){    // check if the container is then wait until consumer consumes some values
                    System.out.println("Container size is full...");
                    try {
                        lock.wait();        //in a wait state does not let any thread to enter yet
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    System.out.println(sequence + " Added to the container");
                    container.add(sequence++);      //add values to container
                    lock.notify();      //notify other waiting threads about the changes
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock){
            while (true){
                if(container.size() == bottom){
                    System.out.println("Container is empty...");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    System.out.println(container.removeFirst() + " removed from the container");
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}