package chapter10.exam02;

import java.util.concurrent.Executor;

public class SyncExecutorExample {
    public static void main(String[] args) {
        SyncExecutor syncExecutor = new SyncExecutor();

        syncExecutor.execute(() -> {
            System.out.println("동기 작업 1 수행 중...");
            // 작업 수행
            System.out.println("동기 작업 1 완료...");
        });


        syncExecutor.execute(() -> {
            System.out.println("동기 작업 2 수행 중...");
            // 작업 수행
            System.out.println("동기 작업 2   완료...");
        });


    }

    /**
     * 제출 (Submit)된 Runnalbe 작업을 실행(Execute)하는 객체
     * - Executor 인터페이스는 각 작업의 실행(실행방법, 스레드 사용, 스케줄링 등의 세부 사항)과 작업의 제출을 분리하는 방법을 제공한다.
     * 단 하나의 execute() 메서드를 가지고 있으며 주어진 명령을 미래의 어떤 시점에 실행한다.
     * Runnable 명령은 Executor 구현 방식에 따라 새 스레드, 풀 스레드 또는 호출 스레드에서 실행 될 수 있다.
     * 직접 스레드를 생성하고 작업을 실행하는 것이 아니라, 작업을 제출하면 스레드 생성과 작업 실행은 Executor에서 처리하도록 하는 것이 더 유연하고 좋은 설계이다.
     */

    static class SyncExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

}
