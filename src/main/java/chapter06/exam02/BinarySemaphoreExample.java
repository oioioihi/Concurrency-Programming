package chapter06.exam02;

public class BinarySemaphoreExample {
    public static void main(String[] args) throws InterruptedException {

        SharedResource sharedData = new SharedResource(new BinarySemaphore());

        Thread thread1 = new Thread(sharedData::sum);
        Thread thread2 = new Thread(sharedData::sum);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 합계 : " + sharedData.getSharedValue());
    }
}

class SharedResource {

    private int sharedValue = 0;
    private CommonSemaphore commonSemaphore;

    public SharedResource(CommonSemaphore commonSemaphore) {
        this.commonSemaphore = commonSemaphore;
    }

    public void sum() {
        try {
            commonSemaphore.acquired();

            // Critical Section
            for (int i = 0; i < 100; i++) {
                sharedValue++;
            }
        } finally {
            commonSemaphore.release(); // 임계영역의 로직에서 에러가 나도 락을 해제하기 위해서 finally 구문 안에서 락을 해제한다.
        }
    }

    public int getSharedValue() {
        return sharedValue;
    }
}

