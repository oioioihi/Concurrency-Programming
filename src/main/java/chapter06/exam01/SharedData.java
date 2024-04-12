package chapter06.exam01;

public class SharedData {

    private int sharedValue = 0;
    private Mutex mutex;

    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        try {
            mutex.acquired();

            // Critical Section
            for (int i = 0; i < 100; i++) {
                sharedValue++;
            }
        } finally {
            mutex.release(); // 임계영역의 로직에서 에러가 나도 락을 해제하기 위해서 finally 구문 안에서 락을 해제한다.
        }
    }

    public int getSharedValue() {
        return sharedValue;
    }
}
