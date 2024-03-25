package chapter04.exam02;

import java.util.concurrent.atomic.AtomicBoolean;

public class FlagThreadStopExample2 {

    private AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        new FlagThreadStopExample2().flagTest();
    }

    private void flagTest() {
        new Thread(() -> {
            int count = 0;
            while (running.get()) {
                count++;
            }
            System.out.println("스레드1 종료");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("스레드2 종료");
            running.set(false);
        }).start();
    }
}
