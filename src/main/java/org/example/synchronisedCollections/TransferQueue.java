package org.example.synchronisedCollections;


import java.util.concurrent.LinkedTransferQueue;

/**
 * Transfer queue is similar to BlockingQueue, but it has that backpressure. It kind of shows producer consumer implementation
 * when producer thread sends tasks to the queue with transfer(); it blocks until consumer thread retrieves and executes that task*/
public class TransferQueue {

    public static void main(String[] args) {
        // Instantiate the only standard implementation of TransferQueue
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                System.out.println("[Consumer] Simulating initialization work for 2 seconds...");
                Thread.sleep(2000);

                System.out.println("[Consumer] Ready to receive. Calling take()...");
                String message = queue.take(); // Blocks until an item is available
                System.out.println("[Consumer] Successfully received: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Producer Thread
        Thread producer = new Thread(() -> {
            try {
                System.out.println("[Producer] Attempting to transfer data...");
                long startTime = System.currentTimeMillis();

                // This blocks until the consumer thread calls take()
                queue.transfer("Critical Payload");

                long duration = System.currentTimeMillis() - startTime;
                System.out.println("[Producer] Transfer complete! Blocked for " + duration + " ms.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start both threads
        consumer.start();
        producer.start();
    }
}

/**
Transfer(); method enqueuea to an existing queue and waits until consumer consumes it with take(); or poll();.
tryTranfer(Element e); returns true if any waiting threadin that instant, false if none.
tryTransfer(Element e, long timeout, TimeUnit.Unit); checks for availqble consumerinstantly, if none exists it waits for specified timeout and onpy then hives up
hasWaitingConsumer(); returns true if any consumer thread is waiting false if none.
getWaitingConsumerCount(); returns number of consumer threads waiting in the queue.*/


