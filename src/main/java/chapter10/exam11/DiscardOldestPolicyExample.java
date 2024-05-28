package chapter10.exam11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DiscardOldestPolicyExample {

    public static void main(String[] args) {

        int corePoolSize = 3;
        int maxPoolSize = 3;
        long keepAliveTime = 0L;
        int workQueueCapacity = 2;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize, keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(workQueueCapacity),
                new ThreadPoolExecutor.DiscardOldestPolicy()); // Executor가 종료되지 않은 경우 Queue의 맨 앞에 있는 작업을 삭제하고 실행이 재 시도 된다. 재시도가 실패하면 반복 될 수 있다.

        submitTasks(executor);
        executor.shutdown();
    }

    private static void submitTasks(ThreadPoolExecutor executor) {
        for (int i = 0; i < 6; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "가 태스크" + taskId + "를 실행하고 있습니다.");
            });
        }
    }
}
