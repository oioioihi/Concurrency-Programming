package chapter10.exam11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor Hook Method
 * <p>
 * ThreadPoolExecutor 클래스는 스레드 풀을 관리하고, 작업 실행 시점에 특정 이벤트를 처리하기 위한 Hook 메서드를 제공한다.
 * ThreadPoolExecutor 클래스를 상속하고, Hookk 메서드를 커스텀 할 수 있다.
 */
public class ThreadPoolExecutorHook extends ThreadPoolExecutor {

    public ThreadPoolExecutorHook(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0L;
        int workQueueCapacity = 5;

        ThreadPoolExecutorHook executor = new ThreadPoolExecutorHook(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(workQueueCapacity));

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

    /**
     * 작업 스레드가 작업을 실행하기 전에 호출되는 메서드로서, 제출도니 각 작업마다 한 번씩 호출되고 작업 실행 전에 원하는 동작을 추가할 수 있다.
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("Thread -> '" + t.getName() + "' is about to execute task. -> " + r.toString());
        super.beforeExecute(t, r);
    }

    /**
     * 작업 스레드가 작업 실행을 완료한 후에 호출되는 메서드로서, 제출된 각 작업마다 한 번씩 호출되고 작업 실행 후에 원하는 동작을 추가하거나 예외 처리를 수행할 수 있다.
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (t != null) {
            System.out.println("Task -> '" + r.toString() + "' encountered an exception" + t);
        }
        System.out.println("Task -> '" + r.toString() + "' has completed successfully.");
        super.afterExecute(r, t);

    }

    /**
     * 스레드 풀이 완전히 종료된 후 호출되는 메서드로서 스레드 풀이 종료되면 이 메서드를 사용하여 clean up 작업을 수행할 수 있다.
     */
    @Override
    protected void terminated() {
        System.out.println("Thread pool has been terminated!");
        super.terminated();
    }

}
