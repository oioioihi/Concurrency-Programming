package chapter11.exam05;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class ThenCombineExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyService myService = new MyService();

        CompletableFuture<String> cf1 = myService.getData1();
        CompletableFuture<String> cf2 = myService.getData2();

        // cf1과 cf2는 비동기 적으로 실행되고 두 작업 모두 완료되면 콜백이 수행되기 때문에 병렬적인 성능 이점을 가질 수 있다.
        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, (result1, result2) -> { // cf1과 cf2가 완료되면 thenCombine이 호출된다.
            return result1 + result2 + "Java";
        });
        String finalResult = cf3.join();
        System.out.println("finalResult : " + finalResult);

    }

    static class MyService {

        public CompletableFuture<String> getData1() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("Thread 1 : " + Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Hello ";
            });
        }

        public CompletableFuture<String> getData2() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("Thread 3 : " + Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "World ";
            });
        }
    }
}
