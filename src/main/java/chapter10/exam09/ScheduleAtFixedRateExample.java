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
