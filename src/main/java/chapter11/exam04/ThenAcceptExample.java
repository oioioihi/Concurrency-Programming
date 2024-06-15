package chapter11.exam04;

import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample {
    public static void main(String[] args) {

        MyService myService = new MyService();

        long start = System.currentTimeMillis();

        CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 1 : " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 40;
        }).thenAccept(r -> {
            System.out.println("Thread 2 : " + Thread.currentThread().getName());
            int result = myService.getData1();
            System.out.println("result : " + result);
        }).thenAcceptAsync(r -> {
            System.out.println("Thread 3 : " + Thread.currentThread().getName());
            int result = myService.getData2();
            System.out.println("result : " + result);


        }).join();

        System.out.println("총 소요 시간 : " + (System.currentTimeMillis() - start));

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
