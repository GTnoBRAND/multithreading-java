package org.example.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureInterfaceImplementation {
    static void main() throws Exception{
        try(ExecutorService service = Executors.newSingleThreadExecutor()){
            Future<Integer> future = service.submit(()->{
                try {
                    Thread.sleep(1000);
                    return 21;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            //wait until the task is done
            //isDone() checks if the task completed or not
            while (!future.isDone()){
                Thread.sleep(200);
            }

            //now use ExceptionNow to get the cause of the error immideatly instead of ExecutionException wrapped
            if (future.state() == Future.State.FAILED){
                Throwable error = future.exceptionNow().getCause();
                System.out.println("Task failed with: " + error.getMessage());
            }

            //now safe to call resultNow();
            /**
             * resultNow() return the result immideately without waiting unlike get();
             *use it when it is sure the task completed and you need quick unchecked way to grad the result*/
            int result = future.resultNow();
            System.out.println("Result is: " + result);

        }
    }
}

/**
 * future.get(); waits for the computation to finish if necessary then returns the result and blocks others until result is computed
 * future.cancel(boolean p) cancels the task immideately if it is not completed yet and returns true otherwise false. It`s parameter
 * determines whether the task is interrupted otherwise in progress one allowed to complete*/
