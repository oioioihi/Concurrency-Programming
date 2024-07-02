package chapter11.exam08;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompleteExample {
    public static void main(String[] args) {

        MyService myService = new MyService();
        CompletableFuture<Integer> cf = myService.performTask();
        cf.thenApply(r -> r + 20);

        System.out.println("result : " + cf.join());
        System.out.println("Main Thread 종료");
    }

    static class MyService {
        public CompletableFuture<Integer> performTask() {

            CompletableFuture<Integer> cf = new CompletableFuture<>();
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                cf.complete(2024);
                cf.complete(2025);  // 반영 안됨 -> 내부적으로 compareAndSet 연산을 통해 원자성이 보장됨.
            });


            return cf;

        }
    }
}
