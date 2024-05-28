package chapter10.exam11;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PrestartThreadsExample {

    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L; // idle상태인 스레드를 제거하는 기준. corePoolSize만큼만 남기고 노는 스레드 정리.
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(); //task를 제출한 만큼 다 Queue에 저장함.
        int taskNum = 8;

        // ThreadPoolExecutor는 task가 제출되기 전까지는 스레드를 미리 만들어 놓지 않음.
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        //   executor.prestartCoreThread(); //1개의 스레드만 미리 만들어 놓음.
        executor.prestartAllCoreThreads(); // 생성자에서 지정한 corePoolSize만큼 스레드를 미리 만듬.
        for (int i = 0; i < taskNum; i++) {
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
        executor.shutdown();
    }
}
