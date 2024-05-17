package chapter10.exam07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDownExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " : 작업 종료");

                } catch (InterruptedException e) {
                    throw new RuntimeException("인터럽트 걸림");
                }
                return 2024;

            });
        }
        executorService.shutdown();

        try {
            if (executorService.awaitTermination(2, TimeUnit.SECONDS)) ;
            executorService.shutdownNow();
            System.out.println("스레드 풀 강제 종료 수행");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
    }
}