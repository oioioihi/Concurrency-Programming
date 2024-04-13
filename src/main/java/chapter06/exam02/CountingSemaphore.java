package chapter06.exam02;

public class CountingSemaphore implements CommonSemaphore {

    private int signal;
    private int permits;

    public CountingSemaphore(int permits) {
        this.signal = permits;
        this.permits = permits;
    }

    @Override
    public synchronized void acquired() {
        while (signal == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.signal--;

        System.out.println(Thread.currentThread().getName() + " acquire lock, 세마포어 사용 가능 갯수 : " + this.signal);
    }

    @Override
    public synchronized void release() {
        if (this.signal < this.permits) {
            this.signal++;
            System.out.println(Thread.currentThread().getName() + " release lock, 세마포어 사용 가능 갯수 : " + this.signal);
            this.notify();
        }
    }
}
