package org.example.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureInterfaceImplementation {
    static void main() throws Exception{
        try(ExecutorService service = Executors.newSingleThreadExecutor()){
            Future<Integer> future = service.submit(()->{
                Thread.sleep(1000);
                throw new RuntimeException("Something went wrong");
            });

            //wait until the task is done
            while (!future.isDone()){
                Thread.sleep(200);
            }

            //now use ExceptionNow to get the cause of the error immideatly instead of ExecutionException wrapped
            if (future.state() == Future.State.FAILED){
                Throwable error = future.exceptionNow().getCause();
                System.out.println("Task failed with: " + error.getMessage());
            }
        }
    }
}
