package chapter10.exam05;

import java.util.concurrent.*;

public class FutureGetExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작..");
            Thread.sleep(2000);
            System.out.println("비동기 작업 완료");

            return 2024;
        };
        Future<Integer> future = executorService.submit(callableTask);

//        while (!future.isDone()) {
//            System.out.println("아직 작업을 기다리는 중 ..");
//            Thread.sleep(500);
//        }
//
        try {
            Integer result = future.get(); //main 스레드가 result를 얻을 때 까지 blocking 됨. (2초)
            System.out.println("결과 : " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
