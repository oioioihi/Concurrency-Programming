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
         *
         * 병렬성 : CPU하나당 Thread가 하나씩 붙어서 일괄적으로 일을 수행하는 상태
         * CPU코어가 8개 Job도 8개이면, 한 CPU가 하나의 job을 수행하여 8개의 job들이 1초면 끝난다. (8차선 도로)
         * 하지만 Job이 9개가 되면 8개를 먼저 한 셋트로 끝내고, 나머지 하나를 cpu가 처리 하여서 수행시간은 총 2초가 된다.
         * => 하나의 CPU가 동시에 2개의 Thread를 수행할 수 없다.
         */
    }
}
