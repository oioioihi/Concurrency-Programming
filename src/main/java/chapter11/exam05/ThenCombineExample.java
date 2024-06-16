package chapter11.exam05;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCombineExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyService myService = new MyService();

        CompletableFuture<Integer> cf1 = myService.getData1(10);
        CompletableFuture<Integer> cf2 = cf1.thenCompose(result -> {
            System.out.println("cf1 result : " + result);
            System.out.println("Thread 2 : " + Thread.currentThread().getName());
            return myService.getData2(result);
        });

        Integer finalResult = cf2.get();
        System.out.println("finalResult : " + finalResult);
    }

    static class MyService {

        public CompletableFuture<Integer> getData1(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("Thread 1 : " + Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return input * 2;
            });
        }

        public CompletableFuture<Integer> getData2(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("Thread 3 : " + Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return input * 2;
            });
        }
    }
}
