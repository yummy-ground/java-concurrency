package org.yummyground.chap02.exam01;

public class ExtendThread {
    public static void main(String[] args) {
        Thread worker1 = new WorkerThread();
        Thread worker2 = new Thread() {
            @Override
            public void run() {
                System.out.println("> Thread Run...");
            }
        };

        worker1.start();
        worker2.start();
    }
}

class WorkerThread extends Thread {
    @Override
    public void run() {
        System.out.println("> Thread Run...");
    }
}