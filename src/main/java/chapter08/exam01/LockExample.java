package chapter08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * [ Lock ]
 * <p>
 * Lock 구현은 synchronized 구문과 마친가지로 상호배제와 가시성 기능을 가진 동기화 기법이며, synchronized 보다 더 확장된 락 작업을 제공한다.
 */
public class LockExample {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        LockExample lockExample = new LockExample();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lockExample.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lockExample.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Count : " + lockExample.getCount());
    }

    public void increment() {
        lock.lock();
        // 락이 다른 스레드에 의해 보유되고 있지 않다면, 락을 즉시 획득하고 락의 보유 횟수를 1로 설정한다.
        // 현재 스레드가 이미 이 락을 보유하고 있다면 보유 횟수가 1 증가하고 메서드는 즉시 반환된다.
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
