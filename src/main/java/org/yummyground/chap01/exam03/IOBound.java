package org.yummyground.chap01.exam03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IOBound {
    private static final String TEST_TXT_FILE_PATH =
            "/Users/jdg/Desktop/study/java-concurrency/concurrency/src/main/java/org/yummyground/chap01/exam03/data.txt";

    public static void main(String[] args) {
        int matchingThreadNum = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(matchingThreadNum);

        for (int i = 0; i < matchingThreadNum; i++) {
            submitTask(executorService);
        }

        executorService.shutdown();
    }
    private static Future<?> submitTask(ExecutorService executorService) {
        return executorService.submit(() -> {
            try {
                // IO 가 집중 되는 작업
                for (int j = 0; j < 5; j++) {
                    Files.readAllLines(Path.of(TEST_TXT_FILE_PATH));
                    System.out.println("[Thread] " + Thread.currentThread().getName() +" | " +j); // IO Bound 일때 ContextSwitching 이 일어난다
                }

                // 아주 빠른 Cpu 연산 (형 변환, 누적계 연산)
                int result = 0;
                for (long j = 0; j < 10; j++) {
                    result += j;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
