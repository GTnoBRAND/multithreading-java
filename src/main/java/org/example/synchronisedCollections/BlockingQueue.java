package org.example.synchronisedCollections;


/**
 * Synchronised collections are simply thread-safe mechanism that prevents race-condition by allowing only one thread at the time to
 * execute tasks, so it synchronizes thread ti access shared mutable data, because there is monitor siting behind it, using locks.
 * threads running in a monitor could be blocked while waiting for certain conditions to be met*/
public class BlockingQueue {
    static void main() {

    }
}


/**
 * In blocking queue interface there are mainly 3 ways.
 * 1.BlockingQueue
 * 2.BlockingDeque
 * 3.TransferQueue
 * */