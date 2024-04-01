package chapter04.exam05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolThreadLocalExample {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * ThreadPool을 사용시, 로직 처리 후 threadLocal의 값을 지워주어야 한다.
     *
     * @param args
     */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 첫 번째 작업 : ThreadLocal 값을 설정
        executorService.submit(() -> {
            threadLocal.set("작업 1의 값");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.remove(); // 다시 값을 초기화 해줌
        });

        // 잠시 대기
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 여러번의 두 번째 작업 : ThreadLocal 값을 설정하지 않고 바로 값을 가져와 출력
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            });
        }

        executorService.shutdown();

    }
}
