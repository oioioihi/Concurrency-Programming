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
        }).thenApply(r -> { // thenApply()는 이전 작업 결과가 완료 되었다면 메인 스레드에서 동기 처리되고 그렇지 않으면 이전과 동일한 스레드에서 비동기 처리된다.
            System.out.println("Thread 2 : " + Thread.currentThread().getName());
            int result = myService.getData1();
            return r + result;
        }).thenApplyAsync(r -> { // thenApplyAsync()는 이전 작업 결과와 상관없이 무조건 풀 스레드에서 비동기 처리된다. 풀 스레드는 이전과 동일한 스레드 혹은 새롭게 생성된 스레드가 될수 있다.
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
