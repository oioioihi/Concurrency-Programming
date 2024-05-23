package chapter10.exam09;

import java.util.concurrent.*;

public class ScheduleCallableExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Callable<String> task = () -> {
            return "작업이 한 번 실행되고 결과를 반환합니다.";
        };

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
