package chapter10.exam11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AbortPolicyExample {

    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0L;
        int workQueueCapacity = 2;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize, keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(workQueueCapacity),
                new ThreadPoolExecutor.AbortPolicy()); // 기본적으로 실행되는 정책, task 제출 실패 시 RejectedExecutionException 예외를 발생시킨다.

        submitTasks(executor);
        executor.shutdown();
    }

    private static void submitTasks(ThreadPoolExecutor executor) {
        for (int i = 0; i < 5; i++) {
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
