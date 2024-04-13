package chapter06.exam02;

public class CountingSemaphoreExample {
    public static void main(String[] args) throws InterruptedException {

        CommonSemaphore countingSemaphore = new CountingSemaphore(10);
        SharedResource sharedResource = new SharedResource(countingSemaphore);
        int threadCount = 5;
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                sharedResource.sum();
            });
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.println("최종 값 : " + sharedResource.getSharedValue());
    }
}
