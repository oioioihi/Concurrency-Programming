package chapter10.exam10;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KeepAliveTimeExample {
    /**
     * [ keepAliveTime ]
     * corePoolSize 보다 더 많은 스레드가 존재하는 경우 각 스레드가 keepAliveTime보다 오랜 시간 동안 유휴 상태였다면 해당 스레드는 종료된다.
     * keep-alive 정책은 corePoolSize 스레드 보다 많은 스레드가 있을 때만 적용되지만 allowCoreThreadTimeOut(boolean) 메서드를 사용하여 core 스레드에도 적용할 수 있다.
     * Executors.newCachedThreadPool()로 풀이 생성된 경우 대기 제한 시간이 60초이며 Executors.newFixedThreadPool()로 생성된 경우 제한 시간이 없다.
     */
    public static void main(String[] args) throws InterruptedException {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 4L; // idle상태인 스레드를 제거하는 기준. corePoolSize만큼만 남기고 노는 스레드 정리.
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        int taskNum = 6;

        // ThreadPoolExecutor는 task가 제출되기 전까지는 스레드를 미리 만들어 놓지 않음.
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);


        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "가 태스크" + taskId + "를 실행하고 있습니다.");
            });
        }

        executor.allowCoreThreadTimeOut(true); // 코어 스레드도 keepAliveTime이 지나면 없애버림.
        Thread.sleep(4000);
        executor.shutdown();
    }
}
