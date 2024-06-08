package chapter11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture는 비동기 프로그래밍을 쉽게 다루고, 복잡한 비동기 작업을 효과적으로 처리할 수 있도록 해주는 도구로 Java8에 도입되었다.
 * CompletableFuture는 값과 상태를 설정함으로 명시적으로 완료시킬 수 있는 Future이다. 코드의 가독성을 높이고 비동기 작업의 조합을 간단하게 처리 할 수 있다.
 */
public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Integer finalResult = CompletableFuture.supplyAsync(() -> {
            System.out.println("Service 1 시작");
            // 비동기 서비스 1의 작업 수행
            return 1;
        }).thenApplyAsync(result1 -> {
            System.out.println("Service 2 시작");
            // 비동기 서비스 2의 작업 수행 (service1 결과 사용)
            return result1 + 2;
        }).thenApplyAsync(result2 -> {
            System.out.println("Service 3 시작");
            // 비동기 서비스 3의 작업 수행 (service2 결과 사용)
            return result2 + 3;
        }).thenApplyAsync(result3 -> {
            System.out.println("Service 4 시작");
            // 비동기 서비스 4의 작업 수행 (service3 결과 사용)
            return result3 + 4;
        }).thenApplyAsync(result4 -> {
            System.out.println("Service 5 시작");
            // 비동기 서비스 5의 작업 수행 (service4 결과 사용)
            return result4 + 5;
        }).get();


        // 최종 결과를 얻기 위해 service5의 완료를 기다림
        System.out.println("최종 결과 : " + finalResult);
    }
}
