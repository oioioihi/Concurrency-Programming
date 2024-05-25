package chapter10.exam09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        Runnable task = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        // 초기 지연 시간 이후에 Runnable 작업을 주기적으로 실행하도록 예약하고, ScheduledFuture를 반환한다. 이후에 주어진 주기로 실행된다.
        // 주어진 command의 종료 여부와 관계가 없다.
        ScheduledFuture<?> scheduledFuture1 = scheduler.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture2 = scheduler.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture3 = scheduler.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scheduler.shutdown();

    }
}
