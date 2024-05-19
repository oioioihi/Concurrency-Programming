package chapter10.exam07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * [ ExecutorService 작업 중단 및 종료 ]
 *
 * <p>
 * 1. void shutdown() - 정상적인 스레드 풀 종료
 * 이전에 제출된 작업은 실행하고 더 이상 새로운 작업은 수락하지 않는다. 작업이 모두 완료되면 ExecutorService가 종료된다.
 * 실행 중인 스레드를 강제로 interrupt하지 않기 때문에, interrupt에 응답하는 작업이나 InterruptedException 예외 구문을 작성할 필요가 없다. (이미 제출된 작업을 완료할 때 까지 기다림)
 * <p>
 * 2. List<Runnable> shutdownNow() - 강제적은 스레드 풀 종료
 * 이전에 제출된 작업을 취소하고, 현재 실행중인 작업도 중단하려고 시도한다. 그리고 작업 대기 중이었던 작업 목록을 반환한다.
 * 실행 중인 스데르르 강제로 interrupt 하지만, 해당 작업이 interrupt에 응답하는 작업이 아닌 경우 작업 종료를 보장하지 않는다.
 * Task를 종료하기 위해서는 Thread.isInterrupted()나 sleep() 과 같은 인터럽트 관련 API를 사용해야한다.
 * <p>
 * <p>
 * shutdown 후 작업을 제출하려고 시도하면, RejectedExecutionException 예외가 발생한다.
 * shutdown 호출한 스레드는 실행 중인 작업이 종료될 때까지 기다리지 않고 바로 다음 라인을 실행한다.
 * 만약 스레드가 shutdown 호출 후 blocking되기 위해서는 awaitTermination을 사용해야한다.
 */
public class ShutDownExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            int j = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " : 작업 종료" + j);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("인터럽트 걸림");
                }
                return 2024;

            });
        }
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(3, TimeUnit.SECONDS))
                ; // 일정 시간동안 종료 대기를 했는데도, executorService가 완전히 종료되지 않았으면
            executorService.shutdownNow(); // 강제 종료를 수행한다.
            System.out.println("스레드 풀 강제 종료 수행");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if (executorService.isShutdown()) {
            System.out.println("스레드 풀 종료 여부 : " + executorService.isShutdown());
        }

        if (executorService.isTerminated()) {
            System.out.println("스레드 풀 종료 중...");
        }

        System.out.println("모든 작업이  종료되고 스레드 풀이 종료 됨.");

    }
}