package org.example;

import java.util.ArrayList;

public class ThreadPriorityExample {
    static void main() throws InterruptedException {

        Thread.currentThread().setPriority(6);

        //child thread which inherits the priority of the parent
        Thread child = new Thread(()->{});
        System.out.println("Running child with priority " + child.getPriority());   //prints 6

        //Throws illegalArgumentException if setting priority exceeds the max value(10)
        Thread low = new Thread(new Low());
        Thread high = new Thread(new High());

        low.setPriority(Thread.MIN_PRIORITY);   //setting priority as low which is 1
        high.setPriority(Thread.MAX_PRIORITY);  //setting priority as high which is 10
                                                //normal priority value is 5, by default all

        low.start();
        high.start();


        ThreadGroup group = new ThreadGroup("Custom-group");
        group.setMaxPriority(7);        //threads priority of this group can not exceed  max priority allowed by its group

        Thread groupThread1 = new Thread(group, ()->{
            System.out.println(Thread.currentThread().getPriority());
        });

        Thread groupThread2 = new Thread(group, ()->{
            System.out.println(Thread.currentThread().getPriority());
        });

        groupThread1.setPriority(7);
        groupThread2.setPriority(8);    //this one throws illegalArgumentException
//        groupThread1.join();
//        groupThread1.join();

        System.out.println(groupThread1.getPriority());
        System.out.println(groupThread2.getPriority());

        System.out.println(low.getPriority());
        System.out.println(high.getPriority());
    }
}

class Low implements Runnable{

    int count = 0;
    int givenNumber = 12;


    @Override
    public void run() {
        while(count < givenNumber){
            count++;
            System.out.println("Low count is: " + count);
        }
    }
}

class High implements Runnable{

    int count = 0;
    int givenNumber = 12;


    @Override
    public void run() {
        while(count < givenNumber){
            count++;
            System.out.println("High count is: " + count);
        }
    }
}
