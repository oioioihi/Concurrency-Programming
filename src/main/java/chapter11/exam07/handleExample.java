package chapter11.exam07;

import java.util.concurrent.CompletableFuture;

public class handleExample {

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
                    return result + 10; // 이 작업은 실행되지 않는다. -> 넘어온 결과가 예외이면 주어진 함수를 실행하지 않고, 받은 예외를 그대로 저장한다.
                })
                .handle((result, e) -> { // 정상적인 결과와 예외처리를 모두 다룰 수 있는 메서드이다. result 또는 e가 넘어온다.
                    if (e != null) {
                        System.out.println(e.getMessage());
                        return -1;
                    }
                    return result;
                });

        System.out.println("result : " + cf.join());  // cf는 정상적인 값을 가지고 있다.
    }
}
