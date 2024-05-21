package chapter10.exam08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvokeAnyExample {
    /**
     * [ invokeAny ]
     * 여러 개의 Callable 작업을 동시에 실행하고, 그 중 빨리 성공적으로 완료된(예외를 던지지 않은) 작업의 결과를 반환한다.
     * 어떤 작업이라도 성공적으로 완료하면 블록을 해제하고 해당 작업의 결과를 반환한다.
     * 정상적인 반환 또는 예외 발생 시 완료되지 않은 작업들은 모두 취소한다.
     *
     * @param args
     */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = new ArrayList<>();

        tasks.add(() -> {
            Thread.sleep(3000);
            return "Task 1";
        });
        tasks.add(() -> {
            Thread.sleep(2000);
            return "Task 2";
        });

        tasks.add(() -> {
            Thread.sleep(1000);
            throw new RuntimeException("InvokeAll Error");
        });

        long started = 0;
        try {
            started = System.currentTimeMillis();
            String result = executorService.invokeAny(tasks); // Future안의 결과를 바로 반환함.
            System.out.println("result = " + result);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
            System.out.println("총 소요시간 : " + (System.currentTimeMillis() - started));
        }
    }
}
