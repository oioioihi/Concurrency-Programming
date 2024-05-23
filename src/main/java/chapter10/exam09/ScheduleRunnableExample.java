package chapter10.exam09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleRunnableExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("작업이 한 번 실행되고 결과를 반환합니다.");
        };

        executorService.schedule(task, 3, TimeUnit.SECONDS);
        System.out.println("작업제출 완료");

        executorService.shutdown();
    }
}
