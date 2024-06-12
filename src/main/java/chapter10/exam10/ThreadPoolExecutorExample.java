package chapter10.exam10;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    /**
     * [ ThreadPoolExecutor ]
     * ExecutorService를 구현한 클래스로서 매개변수를 통해 다양한 설정과 조정이 가능하며, 사용자가 직접 컨트롤 할 수 있는 스레드 풀이다.
     * <p>
     * ThreadPoolExecutor는 corePoolSize 및 maximumPoolSize로 설정된 개수에 따라 풀 크기를 자동으로 조정한다.
     * ThreadPoolExecutor는 새로운 task가 제출 될 때, coreSizePool 미만의 스레드가 실행 중이면 corePoolSize가 될때까지 새 스레드를 생성한다.
     * setCorePoolSize 및 setMaximumPoolSize 메서드를 사용하여 동적으로 값을 변경할 수 있다.
     * 기본적으로 스레드 풀은 스레드를 미리 생성하지 않고, 새 작업이 도착할 때만 생성하지만, prestartCoreThread 또는 prestartAllCoreThreads 메서드를 사용하여 재 정의할 수있다.
     * <p>
     * corePoolSize를 초과할 경우엔,
     * 1) Queue 사이즈가 남아 있으면, Queue에 작업을 추가한다.
     * 2) Queue가 가득 차 있는 경우는 maximumPoolSize가 될때까지 새 스레드가 생성된다.
     */

    public static void main(String[] args) {

        int corePoolSize = 100;
        int maxPoolSize = 100;
        long keepAliveTime = 0L; // idle상태인 스레드를 제거하는 기준. corePoolSize만큼만 남기고 노는 스레드 정리.
        //BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(); //task를 제출한 만큼 다 Queue에 저장함.
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(); // capacity만큼의 task만 Queue에 저장함.
        int taskNum = 200;

        ExecutorService executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("인터럽트 된 Thread : " + Thread.currentThread().getName() + " ==== 태스크 : " + taskId);
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "가 태스크" + taskId + "를 실행하고 있습니다.");
            });
        }
        executor.shutdown();
    }
}
