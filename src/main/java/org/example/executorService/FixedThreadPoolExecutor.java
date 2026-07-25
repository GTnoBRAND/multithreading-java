package org.example.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExecutor {
    static void main() {

        //the only difference with single thread executor is that you can specify how many threads needs to be run
        try(ExecutorService service = Executors.newFixedThreadPool(3)){
            for(int i = 0; i <7; i++){
                service.execute(new Work(i));
            }
        }
    }
}

class Work implements Runnable{

    private final int workId;

    Work(int workId) {
        this.workId = workId;
    }

    @Override
    public void run() {
        System.out.println("WorkId is : " + workId + " using thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
