package chapter08.exam02;

import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            boolean acquired = false;

            while (!acquired) {
                acquired = reentrantLock.tryLock(); // Lock 획득 유무에 따라 다른 처리를 하고 싶을때 trylock()을 활용
                if (acquired) {
                    System.out.println("스레드 1 이 락을 획득했습니다.");
                    try {
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {
                        System.out.println("인터럽트 발생");
                    } finally {
                        reentrantLock.unlock();
                        System.out.println("스레드 1 이 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드 1 이 락을 획득하지 못했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            boolean acquired = false;

            while (!acquired) {
                acquired = reentrantLock.tryLock();
                if (acquired) {
                    System.out.println("스레드 2 가 락을 획득했습니다.");
                    try {
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {
                        System.out.println("인터럽트 발생");
                    } finally {
                        reentrantLock.unlock();
                        System.out.println("스레드 2 가 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드 2 가 락을 획득하지 못했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        /**
         * 스레드 1 이 락을 획득했습니다.
         * 스레드 2 가 락을 획득하지 못했습니다.
         * 스레드 2 가 락을 획득하지 못했습니다.
         * 스레드 1 이 락을 해제했습니다.
         * 스레드 2 가 락을 획득했습니다.
         * 스레드 2 가 락을 해제했습니다.
         */
    }
}
