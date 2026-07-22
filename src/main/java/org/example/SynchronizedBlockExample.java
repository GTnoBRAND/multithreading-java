package org.example;

public class SynchronizedBlockExample {


    //global count
    private static int count = 0;

    static void main() {

        Thread first = new Thread(()->{
            for(int i = 0; i < 100; i++){
                increment();
            }
        });

        Thread second = new Thread(()->{
            for(int i = 0; i < 100; i++){
                increment();
            }
        });

        first.start();
        second.start();
        try{
            first.join();
            second.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("count value is: " + count);

    }

    //makes sure only one thread at instant can access to it and write or read from it
    //it also makes sure when thread 1 leaves its changes are visible so thread 2 can see the latest changes instant it opens it
    private synchronized static void increment(){
        count++;
    }

    /*
    * Use synchronized (lock){} instead of method level synchronization.
    * lock allows you to to lock a dedicated objects, protect only small critical section
    * use different locks for different independent state*/

    /*
    * DO not hold lock while doing slow work such as file I/O, calculations, network request*/
}
class SynchronizedLockDemo{

    private static final Object LOCK = new Object();

    static void main() {


        //Wrong, DO NOT USE LIKE THIS!
        synchronized (LOCK){
            System.out.println("Downloading large file...");     //assume downloading largeFIle
            System.out.println("Reading large file...");
        }

        //Correct
        System.out.println("Downloading large file...");

        synchronized (LOCK){
            System.out.println("Reading large file...");
        }
    }
}
