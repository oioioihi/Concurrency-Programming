package chapter07.exam01.method;

public class InstanceMethodSynchronizedExamples {

    private int count = 0;

    public static void main(String[] args) {
        InstanceMethodSynchronizedExamples examples = new InstanceMethodSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                examples.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                examples.decrement();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("최종 값 : " + examples.getCount());
    }

    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 증가 시켰습니다. 현재 값 = " + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 감소 시켰습니다. 현재 값 = " + count);
    }

    public int getCount() {
        return count;
    }
}
