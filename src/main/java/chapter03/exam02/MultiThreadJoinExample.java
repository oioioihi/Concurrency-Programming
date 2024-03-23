package chapter03.exam02;

public class MultiThreadJoinExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("1초 후에 메세지가 출력됩니다.");
                Thread.sleep(1000);
                System.out.println("'thread 1' wake up!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("2초 후에 메세지가 출력됩니다.");
                Thread.sleep(2000);
                System.out.println("'thread 2' wake up!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        System.out.println("스레드1 수행 완료.");
        thread2.join();
        System.out.println("스레드2 수행 완료.");
        System.out.println("main thread is finished!");
    }
}
