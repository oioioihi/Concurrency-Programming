package chatper07.exam03;

public class VolatileExample {

    volatile boolean running = true;

    public static void main(String[] args) {
        new VolatileExample().volatileTest();
    }

    public void volatileTest() {

        new Thread(() -> {
            int count = 0;
            while (running) {
                count++;
            }
            System.out.println("Thread 1 종료. Count = " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.println("Thread2 종료 중 ..");
            running = false;
        }).start();
    }
}
