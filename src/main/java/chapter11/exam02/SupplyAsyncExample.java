package chapter11.exam02;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class SupplyAsyncExample {
    public static void main(String[] args) {

        MyService myService = new MyService();
        CompletableFuture<List<Integer>> cf = CompletableFuture.supplyAsync(new Supplier<List<Integer>>() {
            @Override
            public List<Integer> get() {
                System.out.println(Thread.currentThread().getName() + "가 비동기 작업을 시작합니다.");
                return myService.getData();
            }
        });
        List<Integer> result = cf.join();
        result.stream().forEach(r -> System.out.println("result : " + r));

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
