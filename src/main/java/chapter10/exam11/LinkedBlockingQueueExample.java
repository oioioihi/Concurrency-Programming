package chapter10.exam11;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
    /**
     * [ BlockingQueue ]
     * 기본적으로 스레드 풀은 작업이 제출되면 corePoolSize의 새 스레드를 추가해서 작업을 할당하고 Queue에 작업을 바로 추가하지 않는다.
     * corePoolSize를 초과해서 스레드가 실행 중이면, 새 스레드를 추가해서 작업을 할당하지 않고 Queue가 가득찰 때까지 작업을 추가한다.
     * Queue에 더이상 공간이 남아 있지않고, 스레드도 maxPoolSize 만큼 실행 중이면, 더 이상 작업은 추가되지 않고 거부 된다.
     *
     * @param args
     */
    public static void main(String[] args) {

        /**
         * [ LinkedBlockingQueue ]
         * Executors.newFixedThreadPool()에서 사용한다.
         * 무제한 크기의 Queue로써, corePoolSize의 스레드가 모두 사용 중인 경우 새로운 작업이 제출 되면 대기열에 등록하고 대기하게 된다.
         * 무제한 크기의 Queue이기 때문에 corePoolSize의 스레드만 생성하고, 더 이상 추가 스레드를 생성하지 않기에 사실상 maximumPoolSize를 설정해도 아무런 효과가 없다.
         * LinkedBlockingQueue 방식은 일시적인 요청의 폭증을 완화하는데 유용할 수 있지만, 평균 처리속도 보다 더 빨리 task가 추가되느 경우 Queue의 사이즈가 무한정 증가할 수 있다.
         */

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(); // Queue의 최대 용량은 5

        // 생산자 스레드
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Producing : " + i);
                try {
                    queue.put(i); // 데이터를 큐에 넣음 (큐가 가득 차면 blocking 됨)
                    Thread.sleep(1000); // 생산자 스레드는 1초마다 데이터를 생산
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // 소비자 스레드
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Integer data = queue.take();
                    System.out.println("Consuming : " + data); // 데이터를 큐에서 가져옴 (Queue가 비어있으면 blocking 됨)
                    Thread.sleep(100); // 소비자 스레드는 1.5초마다 데이터를 소비
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
