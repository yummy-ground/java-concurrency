package org.yummyground.chap01.exam03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CPUBound {
    public static void main(String[] args) {
        int matchingThreadNum = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(matchingThreadNum);
        List<Future<?>> futures = new ArrayList<>();

        long start = System.currentTimeMillis();

        for (int i = 0; i < matchingThreadNum; i++) {
            Future<?> future = submitTask(executorService);
            futures.add(future);
        }

        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        long end = System.currentTimeMillis();
        System.out.println("Total Time (Data > CPU Core && Parallel) : " + (end - start) + "ms");

        executorService.shutdown();
    }

    private static Future<?> submitTask(ExecutorService executorService) {
        return executorService.submit(() -> {
            // Cpu Burst (CPU 연산 집중 & 긴 소요시간)
            long result = 0;
            for (long j = 0; j < 1000000000L; j++) {
                result += j;
            }

            // 매우 짧은 대기
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Cpu Bounded Task -> ContextSwitching 크지 않음
            System.out.println("[Thread] " + Thread.currentThread().getName() + " | " + result);
        });
    }
}
