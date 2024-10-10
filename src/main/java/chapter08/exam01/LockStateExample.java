package chapter08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateExample {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("스레드 1이 락을 1번 획득했습니다.");
                try {
                    lock.lock();
                    System.out.println("스레드 1이 락을 2번 획득했습니다.");
                    try {
                        lock.lock();
                        System.out.println("스레드 1이 락을 3번 획득했습니다.");
                    } finally {
                        lock.unlock();
                        System.out.println("스레드 1이 1번 락을 해제했습니다.");
                    }
                } finally {
                    lock.unlock();
                    System.out.println("스레드 1이 2번 락을 해제했습니다.");
                }
            } finally {
                // lock.unlock();
                System.out.println("스레드 1이 3번 락을 해제했습니다.");
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("스레드 2가 락을 획득했습니다.");
            } finally {
                lock.unlock();
                System.out.println("스레드 2가 락을 해제했습니다.");
            }
        }).start();
    }


}
