package org.example;

public class DaemonThreadExample {
    static void main() throws InterruptedException {

        Thread top = new Thread(()->{
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        //every thread is user unless not specified explicitly to be daemon
        Thread opt = new Thread(()->{
            while(true){
                System.out.println("Background tasks...");
                try {//waits for 3 seconds
                    Thread.sleep(1000);
                    System.out.println("daemon thread is running...");
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        top.start();
        //set daemon before starting the thread
        opt.setDaemon(true);

        //check if it is daemon
//        System.out.println(opt.isDaemon());     //also opt.isAlive(); to see if the thread is still active
        opt.start();
        top.join();
        System.out.println("all threads are finished");
    }
}
