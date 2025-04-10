package org.yummyground.chap01.exam01;

import java.util.ArrayList;
import java.util.List;

public class Parallelism {
    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        System.out.println("=".repeat(30));
        System.out.printf("[ CPU Core (총 %d개) 병렬 처리 ]\n", cpuCores);
        System.out.println();

        // CPU Core 갯수만큼 데이터 생성
        List<Integer> datas = new ArrayList<>(cpuCores);
        for (int i = 0; i < cpuCores; i++) {
            datas.add(i);
        }

        // CPU Core 갯수만큼 데이터 병렬 처리
        long start = System.currentTimeMillis();
        long totalSum = datas.parallelStream()
                .mapToLong(data -> {
                    try {
                        System.out.printf(">> now data : %d (%d * %d)\n", data, data, data);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return (long) data * data;
                })
                .sum();
        long time = System.currentTimeMillis() - start;

        System.out.println();
        System.out.printf("소요 시간 : %dms\n", time);
        System.out.printf("결과 : %d\n", totalSum);
        System.out.println("=".repeat(30));


        System.out.println("=".repeat(30));
        System.out.printf("[ CPU Core (총 %d개) 순차 처리 ]\n", cpuCores);
        System.out.println();

        // CPU Core 갯수만큼 데이터 순차 처리
        start = System.currentTimeMillis();
        totalSum = datas.stream()
                .mapToLong(data -> {
                    try {
                        System.out.printf(">> now data : %d (%d * %d)\n", data, data, data);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return (long) data * data;
                })
                .sum();
        time = System.currentTimeMillis() - start;

        System.out.println();
        System.out.printf("소요 시간 : %dms\n", time);
        System.out.printf("결과 : %d\n", totalSum);
        System.out.println("=".repeat(30));
    }

}
