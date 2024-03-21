package chapter02.exam03;

public class WaitingStateThreadExample {
    /**
     * Thread State
     * WAITING : 대기 중인 스레드 상태로서, 다른스레드가 특정작업을 수행하기를 기다리는 상태
     */
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait(); // 해당 스레드를 wait 상태로 만들어 버림
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("스레드 상태 : " + thread.getState());
    }
}
