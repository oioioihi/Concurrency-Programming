package chapter11.exam07;

import java.util.concurrent.CompletableFuture;

public class ExceptionallyExample {

    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(500);
                        throw new IllegalStateException("error");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return 10;
                }).thenApply(result -> {
                    return result + 10; // 이 작업은 실행되지 않는다.
                })
                .exceptionally(e -> { // 예외를 받아 처리하고 대체 결과를 반환한다.
                    System.out.println(e.getMessage());
                    return -1;
                });

        System.out.println("result : " + cf.join());
    }
}
