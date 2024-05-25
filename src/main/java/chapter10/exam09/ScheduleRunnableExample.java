package chapter10.exam09;

import java.util.concurrent.*;

public class ScheduleRunnableExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("작업이 한 번 실행되고 결과를 반환합니다.");
        };

        // 주어진 지연 시간 이후에 Runnable 작업을 예약하고 SchedulaedFuture를 반환한다. 예약된 작업은 한 번만 실행된다.
        ScheduledFuture<?> schedule = executorService.schedule(task, 3, TimeUnit.SECONDS);
        try {
            Object result = schedule.get();
            System.out.println("result = " + result); // 결과는 null이다.
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("작업제출 완료");

        executorService.shutdown();
    }
}
