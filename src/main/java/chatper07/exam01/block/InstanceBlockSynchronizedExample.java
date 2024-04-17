package chatper07.exam01.block;

public class InstanceBlockSynchronizedExample {
    private final Object lockObject = new Object();
    private int count = 0;

    public static void main(String[] args) {
        InstanceBlockSynchronizedExample example = new InstanceBlockSynchronizedExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {

                example.incrementBlockThis();
            }
        }, "스레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                example.incrementBlockThis();
            }
        }, "스레드 2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                example.incrementBlockLockObject();
            }
        }, "스레드 3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                example.incrementBlockLockObject();
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
        System.out.println("최종 값 : " + example.count);
    }

    public void incrementBlockThis() {
        synchronized (this) { // this객체가 모니터 대상이다.
            count++;
            System.out.println(Thread.currentThread().getName() + "가 This에 의해 블록 동기화 함 :" + count);
        }
    }

    public void incrementBlockLockObject() {
        synchronized (lockObject) {// lockObject 객체가 모니터 대상이다.
            count++;
            System.out.println(Thread.currentThread().getName() + "가 lockObject에 의해 블록 동기화 함 :" + count);
        }
    }
}
