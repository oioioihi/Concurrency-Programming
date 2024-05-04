package chapter10.exam03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * [ Runnable ]
 * Method Signature : run() 메서드를 정의하며 인수가 없다.
 * 예외 처리 : Checked Exception 예외를 던질 수 없다.
 * 용도 : 스레드에서 실행할 작업 정의
 * 결과 반환 : 작업이 완료되면 결과를 반환하지 않음.
 */
public class RunnableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable runnableTask = () -> {
            System.out.println("Runnable 작업 수행 중..");
            System.out.println("Runnable 작업 완료");
        };

        executorService.execute(runnableTask);
        executorService.shutdown();
    }
}
