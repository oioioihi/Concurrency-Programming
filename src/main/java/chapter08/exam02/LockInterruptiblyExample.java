package chapter08.exam02;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                reentrantLock.lockInterruptibly();
                System.out.println("스레드 1 이 락을 획득했습니다.");
                try {
                    Thread.sleep(1000);

                } finally {
                    reentrantLock.unlock();
                    System.out.println("스레드 1 이 락을 해제했습니다.");
                }
            } catch (InterruptedException e) {
                System.out.println("스레드 1이 인터럽트를 받았습니다.");
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                reentrantLock.lockInterruptibly();
                System.out.println("스레드 2가 락을 획득했습니다.");
                try {
                    Thread.sleep(1000);

                } finally {
                    reentrantLock.unlock();
                    System.out.println("스레드 2가 락을 해제했습니다.");
                }
            } catch (InterruptedException e) {
                System.out.println("스레드 2가 인터럽트를 받았습니다.");
            }
        });

        thread1.start();
        thread2.start();

        // main 스레드가 thread1을 인터럽트
        thread1.interrupt();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
