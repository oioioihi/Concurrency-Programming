package chapter10.exam11;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolStateExample {
    public static void main(String[] args) throws InterruptedException {

        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0L;
        int workQueueCapacity = 5;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(workQueueCapacity));

        submitTasks(executor);

        // 스레드 풀 종료
        executor.shutdown();
        System.out.println("===============================================");
        printThreadPoolState(executor);

        System.out.println("===============================================");
        boolean terminated = executor.awaitTermination(1, TimeUnit.SECONDS);

        if (!terminated) {
            // 스레드 풀 종료
            executor.shutdown();
        }
        System.out.println("===============================================");

        while (!executor.isTerminated()) {
            System.out.println("스레드 풀 종료 중 ...");
        }

        // 최종 스레드 풀 상태 출력
        printThreadPoolState(executor);
        System.out.println("===============================================");
    }

    private static void submitTasks(ThreadPoolExecutor executor) {
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "가 태스크" + taskId + "를 실행하고 있습니다.");
                return 2024;
            });
        }
    }

    private static void printThreadPoolState(ThreadPoolExecutor executor) {
        if (executor.getActiveCount() > 0) { // 현재 작업을 실행 중인 스레드가 존재하는 상태
            System.out.println("ThreadPoolExecutor is RUNNING");
        }
        if (executor.isShutdown()) {
            System.out.println("ThreadPoolExecutor is SHUTDOWN or STOP");
        }
        if (executor.isTerminating()) {
            System.out.println("ThreadPoolExecutor is TIDYING"); // 모든 작업이 종료되었으며, workerCount가 0인 상태에서 terminated() 훅 메서드를 실행한다.
        }
        if (executor.isTerminated()) {
            System.out.println("ThreadPoolExecutor is TERMINATED"); // terminated()메서드가 완료되었고, 스레드 풀이 종료된 상태이다.
        }

    }

}
