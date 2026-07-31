package org.example.synchronisedCollections;


import java.util.concurrent.*;

/**
 * Synchronised collections are simply thread-safe mechanism that prevents race-condition by allowing only one thread at the time to
 * execute tasks, so it synchronizes thread ti access shared mutable data, because there is monitor siting behind it, using locks.
 * threads running in a monitor could be blocked while waiting for certain conditions to be met*/
public class BlockingQueue {
    static void main() {
        java.util.concurrent.BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        int n_producer = 4;
        int n_consumer = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = n_producer / n_consumer;


        try(ExecutorService service = Executors.newFixedThreadPool(n_consumer)){
            for (int i = 0; i < n_producer; i++) {
                service.execute(new Producer(queue, poisonPill, poisonPillPerProducer));
            }
            for (int i = 0; i < n_consumer; i++){
                service.execute(new Consumer(queue, poisonPill));
            }
        }
    }
}

class Producer implements Runnable{

    private java.util.concurrent.BlockingQueue<Integer> numbersQueue;
    private final int poisonPill;
    private final int poisonPilPerProducer;

    Producer(java.util.concurrent.BlockingQueue<Integer> numbersQueue, int poisonPill, int poisonPilPerProducer) {
        this.numbersQueue = numbersQueue;
        this.poisonPill = poisonPill;
        this.poisonPilPerProducer = poisonPilPerProducer;
    }


    @Override
    public void run() {
        while (true){
            try {
                generateNumbers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    public void generateNumbers() throws InterruptedException{
        for(int i = 0; i < 100; i++){
            numbersQueue.put(ThreadLocalRandom.current().nextInt(100));
        }
        for (int i = 0; i < poisonPilPerProducer; i++) {
            numbersQueue.put(poisonPill);
        }
    }
}


class Consumer implements Runnable{


    private java.util.concurrent.BlockingQueue<Integer> numbersQueue;
    private final int poisonPill;

    Consumer(java.util.concurrent.BlockingQueue<Integer> numbersQueue, int poisonPill) {
        this.numbersQueue = numbersQueue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            while (true){
                Integer number = numbersQueue.take();
                if(number.equals(poisonPill)){
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " result: " + number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * In blocking queue interface there are mainly 3 ways.
 * 1.BlockingQueue
 * 2.BlockingDeque
 * 3.TransferQueue
 * */