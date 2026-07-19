package org.example;


//difference between Thread and Runnable;

/*Thread blocks ability to extend any other class while
 with Runnable you can implement more than one interface and still can extend other class*/

//implementation is identical with runnable
public class ExtendingThreadExample {

    static void main() {

        Thread first = new ThreadA();
        Thread second = new ThreadB();

        first.start();
        second.start();
    }

}

class ThreadA extends Thread{

    @Override
    public void run(){
        for(int i = 0; i < 12; i++){
            System.out.println("Thread one: " + i);
        }
    }
}

class ThreadB extends Thread{
    @Override
    public void run(){
        for(int i = 0; i < 12; i++){
            System.out.println("Thread two: " + i);
        }
    }
}

