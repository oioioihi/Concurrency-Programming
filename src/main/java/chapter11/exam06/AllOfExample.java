package chapter11.exam06;

import java.util.concurrent.CompletableFuture;

/**
 * [ allOf ]
 * 여러개의 CompletableFuture를 동시에 실행하고 모든 CompletableFuture가 완료될 때까지 대기하는데 사용된다.
 * ExecutorService의 invokeAll()과 유사한 개념이다.
 */
public class AllOfExample {
    public static void main(String[] args) {
        ServiceA service = new ServiceA();

        CompletableFuture<Void> allOf = CompletableFuture.allOf(service.getData1(), service.getData2(), service.getData3()); // 모든 잡들은 비동기 병렬 실행된다.

        CompletableFuture<Void> voidCompletableFuture = allOf.thenAccept(result -> {
            System.out.println("result : " + result);
        });

        /**
         * Main Thread가 join()으로 대기하고 다가 해제 되는 기준은 allOf()로 반환된 CompletableFuture의 결과가 null이 아닌 어떠한 값(해당 경우에는 AltResult)으로 채워져야한다.
         * AltResult는 모든 CompletableFuture의 비동기 작업이 완료되었을 때 allOf()의 CompletableFuture에 저장되는 null 개념의 객체이다.
         */
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
                    Thread.sleep(1000);
                    System.out.println("비동기 작업 시작 3");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return 30;
            });
        }
    }
}
