package chapter11.exam06;

import java.util.concurrent.CompletableFuture;

/**
 * [ anyOf ]
 * 여러개의 CompletableFuture를 동시에 실행하고, 가장 먼저 완료되는 하나의 CompletableFuture를 반환하는 메서드이다
 * ExecutorService의 invokeAny()과 유사한 개념이다.
 */
public class AnyOfExample {
    public static void main(String[] args) {
        ServiceA service = new ServiceA();

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(service.getData1(), service.getData2(), service.getData3());// 모든 잡들은 비동기 병렬 실행된다.

        CompletableFuture<Void> voidCompletableFuture = anyOf.thenAccept(result -> {
            System.out.println("result : " + result);
        });


        voidCompletableFuture.join();

        System.out.println("Main Thread 종료");
    }

    static class ServiceA {
        public CompletableFuture<Integer> getData1() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(5000);
                    System.out.println("비동기 작업 시작 1");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return 10;
            });
        }

        public CompletableFuture<Integer> getData2() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("비동기 작업 시작 2");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return 20;
            });
        }

        public CompletableFuture<Integer> getData3() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println("비동기 작업 시작 3");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return 30;
            });
        }
    }
}
