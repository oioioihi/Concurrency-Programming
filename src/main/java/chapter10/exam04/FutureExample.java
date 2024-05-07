package chapter10.exam04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * [ Future ]
 * 정의 : 비동기 작업의 결과를 나타내는 객체.
 * 블로킹 여부 : 비동기 작업이 완료될 때까지 Blocking.
 * 작업 결과 : 비동기 작업이 완료되면 결과를 얻을 수 있음.
 * 활용 용도 : 결과를 받아오는 작업에서 활용됨.
 */
public class FutureExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 2024;
        });
        System.out.println("비동기 작업 시작");

        try {
            // Blocking 시작
            Integer result = future.get();
            //Blocking 끝 : 결과 값을 얻기까지 Main Thread는 wait()되고, Task에서 setResult()로 결과 값을 Future에 저장한뒤 Main Thread를 notify() 함

            System.out.println("비동기 작업 결과 : " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
