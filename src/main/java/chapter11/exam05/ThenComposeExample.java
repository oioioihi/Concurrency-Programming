package chapter11.exam05;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * [ thenCompose ]
 * Funtion<T,CompletionStage<U>>함수를 인수로 받고 작업 결과를 반환한다. Stream의 flatMap()과 유사하다.
 * 실행객체 : UniCompose
 */
public class ThenComposeExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyService myService = new MyService();

        CompletableFuture<Integer> cf1 = myService.getData1(10);
        CompletableFuture<Integer> cf2 = cf1.thenCompose(result -> { // 결과를 받아서 새로운 CompletableFuture를 시작하고 결과를 조작한 다음, 그 결과를 처음의 CompletableFuture에 반환한다.ㄴ
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
