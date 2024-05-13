package chapter10.exam05;

import java.util.concurrent.*;

/**
 * [ cancel(boolean mayInterruptIfRunning) ]
 * 자바에서 Future는 비동기 작업의 결과를 나중에 가져올 수 있도록 도와주는 인터페이스이다.
 * Future 는 비동기 작업이 완료되었느지 여부를 확인할 수 있고, 조건에 따라 작업을 취소할 수 있으며 작업의 결과를 얻는 방법을 제공한다.
 * <p>
 * 작업이 이미 완료되었거나, 취소되었거나 다른 이유로 취소할 수 없는 경우 => 아무런 일도 발생하지 않으며 false를 반환한다.
 * 작업이 시작되지 않은 경우 => 해당 작업은 실행되지 않으며 true 를 반환한다.
 * 작업이 이미 시작된 경우 => mayInterruptIfRunning 매개변수에 따라 결정된다.
 * 1) mayInterruptIfRunning 파라미터가 true 인 경우 => 해당 작업을 중지시키기 위해 현재 작업을 실행 중인 스레드를 인터럽트 한다. 작업 결과를 가져 올 때 취소 예외가 발생한다.
 * 2) mayInterruptIfRunning 파라미터가 false인 경우 => 진행 중인 작업을 완료 할 수 있다. 작업결과를 가져 올때 취소 예외가 발생한다.
 * 작업과 연관된 모든 대기 중인 스레드를 깨우고 시그널을 보내며 done()을 호출하고 callable을 null로 설정한다.
 */
public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {

            System.out.println("비동기 작업 시작..");
            Thread.sleep(2000);
            System.out.println("비동기 작업 완료");

            return 2024;
        };

        Future<Integer> future = executorService.submit(callableTask);

        Thread.sleep(1000);
        future.cancel(true);
        //future.cancel(false);

        try {
            Integer result = future.get(); //main 스레드가 result를 얻을 때 까지 blocking 됨
            System.out.println("결과 : " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
