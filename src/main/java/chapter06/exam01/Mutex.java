package chapter06.exam01;

/**
 * [ Mutex ]
 * <p>
 * 뮤택스는 락과 락해제를 통해 자원을 보호하는 락체계 동기화 도구이다.
 * 뮤택스 또는 상호 배제는 공유 자원에 대한 경쟁 상태를 방지하고 동시성 제어를 위한 락 메커니즘이다.
 * 스레드가 임계영역에서 Mutex 객체의 플래그를 소유하고 있으면, 다른 스레드가 접근 할수 없으며, 해당 임계영역에 접근하려고 시도하는 모든 스레드는 차단된다.
 */
public class Mutex {

    private boolean lock = false;

    public synchronized void acquired() {
        while (lock) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.lock = true;
        }
    }

    public synchronized void release() {
        this.lock = false;
        this.notify();
    }


}
