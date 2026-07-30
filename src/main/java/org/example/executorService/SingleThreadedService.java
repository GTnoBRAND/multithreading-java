package org.example.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadedService {

    static void main() {
        /*
         *We create instance of ExecutorService  and new SingleThreadExecutor method from Executors
         * class. It will aouto start the thread and use same one thread for all the tasks in the
         * blocking queue*/
        try(ExecutorService executor = Executors.newSingleThreadExecutor()){
            for(int i = 0; i < 5; i++){
                executor.execute(new Job(i));
            }
        }
    }
}

class Job implements Runnable{

    private final int jobId;

    Job(int jobId) {
        this.jobId = jobId;
    }

    @Override
    public void run() {
        System.out.println("Job " + jobId + " is executed by thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
