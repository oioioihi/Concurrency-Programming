package chapter10.exam10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    /**
     * [ Executors ]
     * Executors는 Executor, ExecutorService, ScheduledExecutorService, ThreadFactory 및 Callable 클래스를 위한 유틸리티 팩토리 클래스이다.
     * Executors는 스레드 풀 및 작업 스케줄링에 대한 다양한 메서드와 팩토리 메서드를 제공함으로, 복잦ㅂ한 멀티 스레드 환경에서이 작업을 간단하게 다룰 수 있도록 해준다.
     */
    public static void main(String[] args) {

/**
 * static ExecutorService newFixedThreadPool(int nThreads)
 *
 * 메서드의 매개변수로 원하는 스레드 개수를 지정할 수 있으며, 지정한 개수만큼 스레드가 생성되어 작업을 처리한다.
 * 스레드 풀은 모든 스레드가 공유하는 대기열(Queue)를 가지고 있으며, 대기열은 무한한 크기의 대기열로 스레드가 가용 상태이면 대기 중인 작업을 처리한다.
 * 모든 스레드가 활성 상태이고, 작업이 추가되면, 스레드가 사용 가능한 상태가 될 때까지 작업들은 대기열에서 대기한다.
 * 스레드 풀이 종료하기 전에 어떤 스레드가 실패로 종료하게 되면, 필요한 경우 새로운 스레드가 대신 작업을 수행한다.
 */
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread = " + Thread.currentThread().getName());
            });
        }

        executorService.shutdown();

    }
}
