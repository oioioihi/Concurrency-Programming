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
        }).thenAccept(r -> { // 결과를 받아서 (비)동기적으로 작업 결과를 수행하고 완료 혹은 다음 작업을 수행한다.
            // thenAccept()는 이전 작업 결과가 완료 되었다면 메인 스레드에서 동기 처리되고 그렇지 않으면 이전과 동일한 스레드에서 비동기 처리된다.
            System.out.println("Thread 2 : " + Thread.currentThread().getName());
            int result = myService.getData1();
            System.out.println("result : " + result);
        }).thenAcceptAsync(r -> { // Async 메서드이면 무조건 비동기로 처리. 이전과 동일한 스레드 혹은 새롭게 생성된 스레드가 될 수 있다.
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
