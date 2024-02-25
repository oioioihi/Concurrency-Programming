package chapter01.exam01;

import java.util.ArrayList;
import java.util.List;

public class ParallelismExample {

    public static void main(String[] args) {

        int cpuCores = Runtime.getRuntime().availableProcessors(); // 8코어 맥북을 사용하고 있다.

        // CPU 개수만큼 할일을 생성
        List<Integer> jobs = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            jobs.add(i);
        }

        // CPU 개수만큼 데이터를 병렬로 처리
        long startTime = System.currentTimeMillis();

        long result = jobs.parallelStream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i;
                })
                .sum();

        long endTime = System.currentTimeMillis();

        System.out.println("CPU 코어 개수 : " + cpuCores);
        System.out.println("CPU 개수만큼 데이터를 병렬로 처리하는데 걸린 시간 : " + (endTime - startTime) + "ms");
        System.out.println("결과 : " + result);

        /**
         * CPU 코어 개수 : 8
         * CPU 개수만큼 데이터를 병렬로 처리하는데 걸린 시간 : 1014ms
         * 결과 : 28
         */
    }
}
