package chapter10.exam06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            System.out.println("비동기 작업 실행중..");
        }, "Hello World");

        String result = future.get();
        System.out.println("result : " + result);

        Future<?> future1 = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("비동기 작업 실행중..");
            }
        });

        String result1 = (String) future1.get(); // null
        System.out.println("result1 : " + result1);
        executorService.shutdown();
    }
}
