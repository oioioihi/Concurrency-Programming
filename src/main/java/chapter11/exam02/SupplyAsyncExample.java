package chapter11.exam02;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * CompletableFuture는 비동기 작업을 실해하기 위해 내부적으로 ForkJoinPool.commonPool()의 스레드 풀을 사용하며 선택적으로 ThreadPoolExecutor를 사용할 수 있다.
 */
public class SupplyAsyncExample {
    public static void main(String[] args) {

        MyService myService = new MyService();
        CompletableFuture<List<Integer>> cf = CompletableFuture.supplyAsync(new Supplier<List<Integer>>() { // 별도의 스레드로 비동기 실행

            //ForkJoinPool의 WorkQueue에 AsyncSupply객체가 저장되고, ForkJoinWorkerThread가 큐에서 작업을 꺼내어 수행함.
            @Override
            public List<Integer> get() {
                System.out.println(Thread.currentThread().getName() + "가 비동기 작업을 시작합니다.");
                return myService.getData();
            }
        });
        List<Integer> result = cf.join();
        result.stream().forEach(r -> System.out.println("result : " + r));
        System.out.println("Main Thread End..");

    }
}

class MyService {
    public List<Integer> getData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(1, 2, 3);
    }
}
