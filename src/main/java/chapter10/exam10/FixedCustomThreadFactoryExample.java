package chapter10.exam10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedCustomThreadFactoryExample {

    public static void main(String[] args) {

        CustomThreadFactory customThreadFactory = new CustomThreadFactory("CustomThread");
        //매개변수 ThreadFactory를 통해 스레드생성과 관련된 로직을 정할 수 있다. 즉, 커스텀하게 스레드 생성방식을 적용해, 스레드 이름이나 우선순위 등을 조절할수 있다.
        ExecutorService executorService = Executors.newFixedThreadPool(3, customThreadFactory);

        List<Future<Integer>> futureList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;

            Callable<Integer> task = () -> {
                System.out.println("Current Thread : " + Thread.currentThread().getName() + ", Result : " + taskNumber);
                return taskNumber;
            };
            Future<Integer> future = executorService.submit(task);
            futureList.add(future);
        }
        for (Future<Integer> future : futureList) {
            try {
                future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();

    }

    /**
     * [ ThreadFactory ]
     * 스레드 생성과 관련된 세부 사항을 추상화하고 원하는 방식으로 스레드를 커스터마이징 할 수 있도록 도와주는 객체
     */
    static class CustomThreadFactory implements ThreadFactory {

        private final String name;
        private int threadCount = 0;

        CustomThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            // 우선순위, 이름, 데몬상태 여부, ThreadGroup 등을 초기화 할 수 있다.
            threadCount++;
            String threadName = name + " - " + threadCount;
            Thread thread = new Thread(runnable, threadName);
            System.out.println("커스텀 스레드 이름 : " + threadName);
            return thread;
        }
    }
}
