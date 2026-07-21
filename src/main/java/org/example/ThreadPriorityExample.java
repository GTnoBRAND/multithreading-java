package org.example;

public class ThreadPriorityExample {
    static void main() {

        Thread low = new Thread(new Low());
        Thread high = new Thread(new High());

        low.setPriority(Thread.MIN_PRIORITY);   //setting priority as low which is 1
        high.setPriority(Thread.MAX_PRIORITY);  //setting priority as high which is 10
                                                //normal priority value is 5, by default all

        low.start();
        high.start();


        ThreadGroup group = new ThreadGroup("Custom-group");
        group.setMaxPriority(7);

        Thread groupThread1 = new Thread(group, ()->{
            System.out.println(Thread.currentThread().getPriority());
        });

        Thread groupThread2 = new Thread(group, ()->{
            System.out.println(Thread.currentThread().getPriority());
        });
    }
}

class Low implements Runnable{

    int count = 0;
    long endTime = System.currentTimeMillis() + 1;

    @Override
    public void run() {
        while(System.currentTimeMillis() < endTime){
            count++;
            System.out.println("Low count is: " + count);
        }
    }
}

class High implements Runnable{

    int count = 0;
    long endTime = System.currentTimeMillis() + 1;

    @Override
    public void run() {
        while(System.currentTimeMillis() < endTime){
            count++;
            System.out.println("High count is: " + count);
        }
    }
}
