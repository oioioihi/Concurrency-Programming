package chapter02.exam03;

public class TimedWaitingStateThreadExample {
    /**
     * Thread State
     * TIEMD_WAITING : 대기 중인 스레드 상태로서, 대기시간이 지정되어 있다.
     */
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        Thread.sleep(3000);
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
