package org.example.executorService;

import java.util.concurrent.*;

public class CallableAndFuture {
    static void main() {
        try(ExecutorService service = Executors.newFixedThreadPool(3)){

            /**
             * A Future interface provides methods to check if the computation is complete, to wait for it`s completion
             * and to retrieve the result of that computation using .get(); which waits until the task is finished and blocks other tasks
             * to be performed*/

            Exercise exercise1 = new Exercise(10);
            Exercise exercise2 = new Exercise(12);

            FutureTask<Integer> futureTask1 = new FutureTask<>(exercise1);
            FutureTask<Integer> futureTask2 = new FutureTask<>(exercise2);

            service.submit(futureTask1, 10);
            service.submit(futureTask2, 12);

            while (true){
                try {
                    //check if both exercises completed with .isDone(); for futureTask
                    if(futureTask1.isDone()&&futureTask2.isDone()){
                        System.out.println("All exercises are done!");
                        service.shutdown();
                        return;
                    }

                    //.get(); means wait for the task to finish, then retrieve the result
                    if (futureTask1.isDone()){
                        System.out.println("Exercise is done, waiting for exercise2 to complete... " + futureTask1.get());
                    }

                    System.out.println("waiting for exercise2 to complete");
                    Integer s = futureTask2.get();

                    if(s!=null){
                        System.out.println("Future exercise2 output: " + s);
                    }
                } catch (RuntimeException | InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

}
/**
 * implementing Callable interface lets to return the result of the thread and its job while runnable does not,
 * and callable can be submitted to an executing service to be executed*/
class Exercise implements Callable<Integer> {

    private final Integer numOfExercise;

    Exercise(Integer numOfExercise){
        this.numOfExercise = numOfExercise;
    }

    @Override
    public Integer call() throws Exception {

        System.out.println("COUNTING NUMBER OF EXERCISES...");
        for (int i = 0; i < numOfExercise; i++){
            System.out.println("The count is: " + i);
            System.out.println("Process running on thread: " + Thread.currentThread().getName());
            Thread.sleep(1000);
        }

        return numOfExercise;
    }
}

