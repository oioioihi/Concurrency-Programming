package chapter07.exam02;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [ wait() ]
 * 스레드를 대기 상태로 전환시키고(Wait Set) 모니터 락은 해제되며 다른 스레드가 모니터 락을 획득하여 작업을 수행할 수 있다.
 * 조건 변수와 함께 사용되어 특정 조건이 만족될 때가지 대기하게 되며, 이를 통해 스레드 간의 효율적인 협력을 구현할 수 있다.
 * 다른 스레드가 동일한 모니터 락을 획득하고 notify() 또는 notifyAll()메서드를 호출하면 대기 중의 한 스레드 혹 모든 스레드가 깨어난다.(Entry Set으로 들어간다.)
 * wait(long timeout)을 사용하여 일정 시간 동안 대기하도록 타임아웃을 지정할 수 있어며, 타임아웃이 경과하면 스레드는 자동으로 깨어난다.
 * Interrupt 가 걸리면 InterruptedException 예외가 발생하고 인터럽트 된 스레드는 대기에서 깨어나게 된다. 적절한 예외처리가 필요하다.
 * <p>
 * [ notify() & notifyAll() ]
 * notify()는 같은 모니터의 조건 변수에서 대기 중인 스레드 중에서 임으의 하나를 깨우며 notifyAll()은 대기 중은 스레드 전체를 깨운다.
 * 어떤 스레드가 깨어날 것인지 알 수 없으며, 무작위로 선택되기 때문에 notify()보다 notifyAll()을 사용하는 것을 선호한다.
 * 깨어난 스레드가 다시 실행되어야 할 때는 해당 객체의 모니터 락을 다시 획득하기 위해 경쟁해야한다.
 * 스레드를 깨울때 우선순위가 높은 스레드가 깨어날 것이라는 보장은 없다. OS와 JVM의 스케줄링 정책에 따라 결정된다.
 * 메서드 호출 후 synchronized 블록이 끝나기 전까지 락이 해제되지 않으며, 해당 블록에서 빠져나가야 락이 해제된다.
 */
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

    public void consume() throws InterruptedException {
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
                    shardQueue.consume();
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
