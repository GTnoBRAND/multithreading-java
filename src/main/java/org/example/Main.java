package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Thread first = new Thread(new ThreadOne());
        Thread second = new Thread(new ThreadTwo());

        //starts the thread and its processes
        first.start();
        second.start();
    }
}
//implementing runnable interface allows to inherit other base classes and lets to extend anything else
class ThreadOne implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("Thread one: " + i);
        }
    }
}

class ThreadTwo implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i<15; i++){
            System.out.println("Thread two: " + i);
        }
    }
}
