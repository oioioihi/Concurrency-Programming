package chapter11.exam04;

import java.util.concurrent.CompletableFuture;

public class ThenRunExample {
    public static void main(String[] args) {

        MyService myService = new MyService();

        long start = System.currentTimeMillis();

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread 1 : " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 40;
        }).thenApply((r) -> {
            System.out.println("Thread 2 : " + Thread.currentThread().getName());
            return myService.getData1();
        });

        Integer result = cf1.join();

        cf1.thenRun(() -> { // Runnable 함수를 인수로 받고 작업을 수행한다.
            System.out.println("Thread 3 : " + Thread.currentThread().getName());
            System.out.println("비동기 작업이 완료되었습니다.");
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
