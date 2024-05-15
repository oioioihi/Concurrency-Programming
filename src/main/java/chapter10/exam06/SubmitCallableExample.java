package chapter10.exam06;

import java.util.concurrent.*;

public class SubmitCallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("비동기 작업 실행");
                return "Hello World ";
            }
        });

        String result = future.get();
        System.out.println("result" + result);
        executorService.shutdown();
    }
}
