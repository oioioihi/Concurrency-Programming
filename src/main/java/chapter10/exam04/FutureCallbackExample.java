package chapter10.exam04;

import java.util.concurrent.*;

public class FutureCallbackExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println(Thread.currentThread().getName() + " : start 2");
            Thread.sleep(1000);
            return 2024;
        };
        Future<Integer> future = executorService.submit(callableTask);
        System.out.println(Thread.currentThread().getName() + " : start 1");
        System.out.println("비동기 작업 시작");

        registerCallback(future, result -> {
            System.out.println("비동기 작업 결과 : " + result);
            System.out.println(Thread.currentThread().getName() + " : start 4");
        });
        executorService.shutdown();
    }

    private static void registerCallback(Future<Integer> future, FutureCallbackExample.Callback callback) {
        new Thread(() -> {
            int result;
            try {
                result = future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " : start 3");
            callback.onComplete(result);
        }).start();
    }

    interface Callback {
        void onComplete(int result);
    }

}
