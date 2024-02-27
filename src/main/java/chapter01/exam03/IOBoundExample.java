package chapter01.exam03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOBoundExample {
    public static void main(String[] args) {

        int threadNum = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

        for (int i = 0; i < threadNum; i++) {
            executorService.submit(() -> {

                try {

                    //IO가 집중 되는 작업
                    for (int j = 0; j < 5; j++) {

                        Files.readAllLines(Path.of("/Users/boeuns/Study/Spring/Reactive/Concurrency-Programming/README.md"));
                        System.out.println("스레드 : " + Thread.currentThread().getName() + ", " + j);
                        // IO Bound 일때 Context Switching이 일어난다.
                    }

                    int result = 0;
                    for (int j = 0; j < 10; j++) {
                        result += j;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();

        /**
         * 결론 :
         * IOBound 작업은 성능을 위하여 Context Switching이 필수적이다.
         * IOBound 작업은 CPU의 동시성에 조금더 초점을 맞춘다.
         */
    }
}
