package org.example;

public class DaemonThreadExample {
    static void main() {
        //every thread is user unless not specified explicitly to be daemon
        Thread opt = new Thread(()->{
            while(true){
                System.out.println("Background tasks...");
                try {//waits for 3 seconds
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        //set daemon before starting the thread
        opt.setDaemon(true);
        //check if it is daemon
        System.out.println(opt.isDaemon());     //also opt.isAlive(); to see if the thread is still active
        opt.start();
        System.out.println("Finish main thread.");
    }
}
