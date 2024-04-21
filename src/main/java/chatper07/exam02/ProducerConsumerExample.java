package chatper07.exam02;


import java.util.LinkedList;
import java.util.Queue;

class ShardQueue {
    private final Object lock = new Object();
    private int CAPACITY = 5;
    private Queue<Integer> queue = new LinkedList<>();

    public void produce(int item) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == CAPACITY) {
                System.out.println("queue가 가득 찼습니다. 생산 중지 ..");
                lock.wait();
            }
            queue.add(item);
            System.out.println("Produce 생산 : " + item);
            lock.notifyAll(); // queue가 비어있어서, consume()에서 wait()이 걸린 스레드들을 깨워줌.
        }
    }

    public void consume(int item) throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                System.out.println("queue가 비었습니다. 소비 중지 ..");
                lock.wait();
            }
            Integer poll = queue.poll();
            System.out.println("Consume 소비 : " + poll);
            lock.notifyAll(); // queue가 가득차 wait()이 걸려있는 Produce에 진입한 스레드들을 깨워줌
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) throws InterruptedException {

        ShardQueue shardQueue = new ShardQueue();

        Thread produce = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    shardQueue.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Produce");


        Thread consume = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    shardQueue.consume(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Consume");

        produce.start();
        consume.start();

        produce.join();
        consume.join();
    }
}
