package chapter01.exam03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CPUBoundExample {
    public static void main(String[] args) {
        int threadNum = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

        long startTime = System.currentTimeMillis();
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            Future<?> future = executorService.submit(() -> {

                //CPU 연산이 집중되고 오래 걸리는 작업
                long result = 0;
                for (int j = 0; j < 100000L; j++) {
                    result += j;
                }

                // 아주 잠깐 대기함
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }

                System.out.println("스레드 : " + Thread.currentThread().getName() + ", " + result);
                // CPU Bound 작업일 때 Context Switching은 크게 발생하지 않는다.
            });

            futures.add(future);
        }
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        long endTime = System.currentTimeMillis();
        System.out.println("CPU 개수를 초과하는 데이터를 병렬로 처리하는데 걸린 시간 : " + (endTime - startTime) + "ms");
        executorService.shutdown();

        /**
         * 결론 :
         * CPU Bound 작업은 성능을 위하여 Context Switching이 작게 일어나는 것이 좋다.
         * CPU Bound 작업은 CPU의 병렬성에 조금더 초점을 맞춘다.
         */

    }
}
