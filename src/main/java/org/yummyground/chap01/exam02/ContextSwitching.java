package org.yummyground.chap01.exam02;

public class ContextSwitching {
    public static void main(String[] args) {
        Thread thread1 = makeThread(1);
        Thread thread2 = makeThread(2);
        Thread thread3 = makeThread(3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
    /*
    스케쥴링 알고리즘에 의해 작업 순차 동시적 처리 (우선순위 및 CPU 자원 할당은 운영체제의 몫)
    순서가 뒤죽박죽

    Thread 1: 0
    Thread 3: 0
    Thread 2: 0
    Thread 1: 1
    Thread 3: 1
    Thread 2: 1
    Thread 1: 2
    Thread 3: 2
    Thread 2: 2
    Thread 3: 3
    Thread 1: 3
    Thread 2: 3
    Thread 3: 4
    Thread 1: 4
    Thread 2: 4
     */

    private static Thread makeThread(int id) {
        return new Thread(() -> { // Argument -> Task (Runnable)
            for (int i = 0; i < 5; i++) {
                System.out.printf("Thread %d: %d\n", id, i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
