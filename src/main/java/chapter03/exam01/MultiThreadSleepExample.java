package chapter03.exam01;

public class MultiThreadSleepExample {
    public static void main(String[] args) {
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

        System.out.println("main thread is finished!");
    }
}
