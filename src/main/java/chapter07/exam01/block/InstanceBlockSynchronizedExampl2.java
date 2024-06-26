package chapter07.exam01.block;

public class InstanceBlockSynchronizedExampl2 {
    private final Object lockObject = new Object();
    private int count = 0;

    public static void main(String[] args) {
        InstanceBlockSynchronizedExampl2 example1 = new InstanceBlockSynchronizedExampl2(); // this, lockObject 2개의 모니터가 존재
        InstanceBlockSynchronizedExampl2 example2 = new InstanceBlockSynchronizedExampl2(); // this, lockObject 2개의 모니터가 존재

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example1.incrementBlockThis();
            }
        }, "스레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example2.incrementBlockThis();
            }
        }, "스레드 2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example1.incrementBlockLockObject();
            }
        }, "스레드 3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example2.incrementBlockLockObject();
            }
        }, "스레드 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


        try {
            thread1.join();
            thread2.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("최종 값 : " + example1.count);
        System.out.println("최종 값 : " + example2.count);
    }

    public void incrementBlockThis() {
        synchronized (this) { // this객체가 모니터 대상이다.
            count++; // 모니터 객체가 달라도 count 라는 변수에 동시접근이 가능해서 문제가 발생할수 있다.
            System.out.println(Thread.currentThread().getName() + "가 This에 의해 블록 동기화 함 :" + count);
        }
    }

    public void incrementBlockLockObject() {
        synchronized (lockObject) {// lockObject 객체가 모니터 대상이다.
            count++; // 모니터 객체가 달라도 count 라는 변수에 동시접근이 가능해서 문제가 발생할수 있다.
            System.out.println(Thread.currentThread().getName() + "가 lockObject에 의해 블록 동기화 함 :" + count);
        }
    }
}
