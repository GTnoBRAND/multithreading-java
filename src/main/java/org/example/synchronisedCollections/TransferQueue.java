package org.example.synchronisedCollections;



/**
 * Transfer queue is similar to BlockingQueue, but it has that backpressure. It kind of shows producer consumer implementation
 * when producer thread sends tasks to the queue with transfer(); it blocks until consumer thread retrieves and executes that task*/
public class TransferQueue {

    static void main() {

    }
/**
Itransfer(); method enqueuea to an existing queue and waits until consumer consumes it with take(); or poll();.
tryTranfer(Element e); returns true if any waiting threadin that instant, false if none.
tryTransfer(Element e, long timeout, TimeUnit.Unit); checks for availqble consumerinstantly, if none exists it waits for specified timeout and onpy then hives up*/


