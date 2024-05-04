package chapter10.exam03;

import java.util.concurrent.*;

public class CallableExample {
    /**
     * [ Callable ]
     * Method Signature : call() 메서드를 정의하며 인수가 없고 결과와 예외 구문이 있다.
     * 예외 처리 : Checked Exception 예외를 던질 수 있다.
     * 용도 : 결과를 반환하며 예외를 처리해야 하는 작업 정의
     * 결과 : 작업이 완료되면 결과를 반환하며 Future 로 결과를 추적함.
     */
    public static void main(String[] args) {
        int result;
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("Callable 작업 수행 중..");
            System.out.println("Callable 작업 완료");

            return 2024;
        };

        Future<Integer> future = executorService.submit(callableTask);
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Callable 작업 결과 : " + result);
    }
}
