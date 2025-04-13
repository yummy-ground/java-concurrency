package org.yummyground.chap02.exam01;

public class ImplementRunnable {
    public static void main(String[] args) {
        ExecuteTask task = new ExecuteTask();
        Thread worker1 = new Thread(task);
        Thread worker2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task Execute...");
            }
        });
        Thread worker3 = new Thread(() -> System.out.println("Task Execute..."));


        worker1.start();
        worker2.start();
        worker3.start();
    }
}

class ExecuteTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Task Execute...");
    }
}