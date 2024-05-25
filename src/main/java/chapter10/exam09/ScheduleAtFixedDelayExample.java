package chapter10.exam09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedDelayExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        Runnable task = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        // 초기 지연 시간 이후에 Runnable 작업을 주기적으로 실행하도록 예약하고, ScheduledFuture를 반환한다. 작업이 완료되고 나서 지연 시간 후 실행된다.
        // 주어진 command가 종료된 후를 기준으로 delay 간격으로 command 실행.
        ScheduledFuture<?> scheduledFuture1 = scheduler.scheduleWithFixedDelay(task, 1, 2, TimeUnit.SECONDS); // 1초 후, 2초 간격으로
        ScheduledFuture<?> scheduledFuture2 = scheduler.scheduleWithFixedDelay(task, 1, 2, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture3 = scheduler.scheduleWithFixedDelay(task, 1, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scheduler.shutdown();

    }
}
