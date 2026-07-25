package org.example.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExecutor {
    static void main() {

        /**
         * here in cached thread pool executor you do not specify threads needed to be run
         * it checks in the pool for the available threads and uses one thread per task, if there is none
         * it creates and assign it to new task, after execution finished for the previous threads
         * they come to pool again and checks if there is new task it needs to execute, if none found
         * within a minute it is killed if found it repeats its lifecycle*/
        try(ExecutorService service  = Executors.newCachedThreadPool()){
            for (int i = 0; i < 700; i++) {
                service.execute(new Task(i));
            }
        }
    }
}

class Task implements Runnable{

    private final int taskId;

    Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("TaskId is: " + taskId + " running on thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}