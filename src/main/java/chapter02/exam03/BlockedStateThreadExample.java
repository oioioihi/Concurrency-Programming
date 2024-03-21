package chapter02.exam03;

public class BlockedStateThreadExample {
    /**
     * Thread State
     * BLOCKED : 모니터 락(Lock)이 해제될때 까지 기다리며 차단된 스레드 상태.
     * 스레드가 동기화된 임계영역에 접근을 시도하다, Lock을 얻지 못해서 차단된 상태.
     */
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (true) {

                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("락을 얻으려고 도전하지만, 얻지 못함.");
                }
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        Thread.sleep(1000);
        System.out.println("스레드 상태 : " + thread2.getState());
    }
}
