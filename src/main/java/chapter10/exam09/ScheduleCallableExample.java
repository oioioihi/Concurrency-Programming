package chapter10.exam09;

import java.util.concurrent.*;

public class ScheduleCallableExample {
    /**
     * [ ScheduledFuture ]
     * ScheduledFuture는 ScheduledExecutorService를 사용하여 작업을 예약한 결과이다.
     * 주요 목적은 지연이나 주기적인 작업 실행을 위한 것이며, 결과를 처리하는 것은 아니다.
     * getDelay(TimeUnit unit) - 작업이 실행 되기까지 남은 지연 시간을 반환한다.
     */
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Callable<String> task = () -> {
            return "작업이 한 번 실행되고 결과를 반환합니다.";
        };

        // 주어진 지연 시간 이후에 Callable 작업을 예약하고 SchedulaedFuture를 반환한다. 예약된 작업은 한 번만 실행된다.
        ScheduledFuture<String> future = executorService.schedule(task, 3, TimeUnit.SECONDS);

        try {
            String result = future.get(); //Blocking
            System.out.println("result = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
