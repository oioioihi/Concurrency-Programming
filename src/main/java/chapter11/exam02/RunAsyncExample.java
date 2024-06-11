package chapter11.exam02;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RunAsyncExample {
    public static void main(String[] args) {
        MyService2 myService = new MyService2();

        /* runAsync() : 비동기 작업을 생성하고 결과가 없는 작업을 실행한다.
        runAsync()는 보통 실행 로그를 남기거나, 독립적인 백그라운드 작업 또는 다음 작업에서 결과를 기다리지 않고 다른 작업을 수행해야 할 경우 사용할 수 있다.
         */
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(new Runnable() { // 별도의 스레드로 비동기 실행
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "가 비동기 작업을 시작합니다.");
                myService.getData().stream().forEach(System.out::println);
            }
        });
        runAsync.join();
        System.out.println("Main Thread End..");

    }


}

class MyService2 {
    public List<Integer> getData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(1, 2, 3);
    }
}