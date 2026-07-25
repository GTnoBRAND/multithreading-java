package org.example.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    static void main() {


        String paymentId = "#2112";

        BackgroundJobService jobService = new BackgroundJobService();
        jobService.start();
        jobService.retryPaymentLately(paymentId);
        jobService.stop();

    }
}

class  BackgroundJobService{

    //create scheduledExecutorService
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);


    //scheduledAtFixedRate = it does its work in that period from that delay periodic
    public void start(){
        scheduler.scheduleAtFixedRate(
           this::refreshProductCacheSafely,
           0,
           1,
           TimeUnit.MINUTES
        );
    //scheduledAtFixedRate = it does its work in that period from that delay periodic, it waits until the task is finished and then delay starts
        scheduler.scheduleWithFixedDelay(
                this::removeExpiredCartsSafely,
                0,
                10,
                TimeUnit.MINUTES
        );
    }


    //one time task execution, good for email verification notification, payment retry
    public void retryPaymentLately(String paymentId){
        scheduler.schedule(
                ()-> retryPaymentSafely(paymentId),
                30,
                TimeUnit.SECONDS
        );
    }



    private void refreshProductCacheSafely(){
        try {
            System.out.println("Product cache refreshing...");
            //refresh logic
        } catch (Exception e) {
            throw new RuntimeException("Cache refresh failed: "+ e.getMessage());
        }
    }

    private void removeExpiredCartsSafely(){
        try {
            System.out.println("Safely removing carts...");
            //logic to safely remove carts
        } catch (Exception e) {
            throw new RuntimeException("Carts remove failed: " + e.getMessage());
        }
    }

    private void retryPaymentSafely(String paymentId){
        try {
            System.out.println("Retrying payment: " + paymentId);
            //logic to retry payment
        } catch (Exception e) {
            throw new RuntimeException( "Payment retry failed: "+ e.getMessage());
        }
    }

    public void stop(){
        scheduler.shutdown();
        try {
            if(!scheduler.awaitTermination(10, TimeUnit.SECONDS)){
                scheduler.shutdownNow();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
