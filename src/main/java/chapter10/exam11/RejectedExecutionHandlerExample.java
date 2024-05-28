package chapter10.exam11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectedExecutionHandlerExample {

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
                new MyRejectedExecutionHandler());

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

/**
 * [ RejectedExecutionHandler ]
 * <p>
 * execute(Runnable)로 제출된 작업이 풀의 포화로 인해 거부 될 경우, execute 메서드는 RejectedExecutionHandler.rejectedExecution()메서드를 호출한다.
 * 미리 정의된 네 가지 핸들러 정책 클래스가 제공되며 직접 사용자 정의 클래스를 만들어 사용할 수 있다.
 */

class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        System.out.println("Task가 거부되었습니다.");

        if (!threadPoolExecutor.isShutdown()) {
            threadPoolExecutor.getQueue().poll();
            threadPoolExecutor.getQueue().offer(runnable);
        }
    }
}