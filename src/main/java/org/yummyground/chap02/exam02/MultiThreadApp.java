package org.yummyground.chap02.exam02;

public class MultiThreadApp {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new RunnableTask(i));
            thread.start(); // `start()` -> JVM -> Kernel 매핑 & `run()` 호출 가능 상태  (바로 `run()` 실행되는 것 아님)
        }

        System.out.println("메인 스레드 종료"); // Main Thread의 가장 마지막 호출 메서드 -> 가장 먼저 처리
    }
}
/* (비동기적 실행)
메인 스레드 종료
> Thread-0 - Running...
> Thread-2 - Running...
> Thread-1 - Running...
2 : Hello World!
0 : Hello World!
1 : Hello World!
(Thread ID = 1 / Value = 101)
(Thread ID = 0 / Value = 100)
(Thread ID = 2 / Value = 102)
 */

class RunnableTask implements Runnable {
    private final int threadId;

    public RunnableTask(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        System.out.println("> " + Thread.currentThread().getName() + " - Running...");
        methodA(threadId);
    }

    private void methodA(int threadId) {
        int localVar = threadId + 100;
        methodB(localVar);
    }

    private void methodB(int value) {
        System.out.println(threadId + " : Hello World!");
        System.out.println("(Thread ID = " + threadId + " / Value = " + value + ")");
    }
}
