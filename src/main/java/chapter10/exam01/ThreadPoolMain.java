package chapter10.exam01;

public class ThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {

        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(3); // 크기가 3개인 스레드풀 생성

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            simpleThreadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " : 작업 " + taskId + "수행 중..");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " : 작업 " + taskId + " 완료 ...");
            });
        }

        Thread.sleep(3000); //main thread 3초 대기 후
        simpleThreadPool.shutdown();
    }
}
