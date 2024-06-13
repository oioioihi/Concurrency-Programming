package chapter11.exam03;

import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {
    public static void main(String[] args) {

        MyService myService = new MyService();

        long start = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 1 : " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 40;
        }).thenApply(r -> {
            System.out.println("Thread 2 : " + Thread.currentThread().getName());
            int result = myService.getData1();
            return r + result;
        }).thenApplyAsync(r -> {
            System.out.println("Thread 2 : " + Thread.currentThread().getName());
            int result = myService.getData2();
            return r + result;
        });

        Integer finalResult = completableFuture.join();
        System.out.println("총 소요 시간 : " + (System.currentTimeMillis() - start));
        System.out.println("finalResult = " + finalResult);

    }


    static class MyService {
        public int getData1() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        }

        public int getData2() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        }
    }
}
