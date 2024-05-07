package chapter10.exam04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {

    /**
     * [ Callback ]
     * 정의 : 비동기 작업이 완료되었을 때 수행할 동작을 정의한 인터페이스 또는 클래스
     * 블로킹 여부 : 블로킹 되지 않고 비동기 작업이 완료되면 콜백을 호출. onComplete()
     * 작업 결과 : 콜백 메스드를 통해 작업 결과를 처리
     * 활용 용도 : 비동기 작업의 완료 후 동작을 정의할 때 주로 활용됨.
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println(Thread.currentThread().getName() + "이 실행 중..1");

        executorService.execute(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "이 실행 중..2");

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int result = 2024;
            MyCallback myCallback = new MyCallback();
            myCallback.onComplete(result);
        });
        System.out.println("비동기 작업 시작");
        executorService.shutdown();
    }

    interface Callback {
        void onComplete(int result);
    }

    static class MyCallback implements Callback {

        @Override
        public void onComplete(int result) {
            System.out.println(Thread.currentThread().getName() + "이 실행 중.. 3");
            System.out.println("비동기 작업 결과 : " + result);
        }
    }
}
