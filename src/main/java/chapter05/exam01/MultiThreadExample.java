package chapter05.exam01;

public class MultiThreadExample {
    /**
     * Multi Thread와 동시성
     * CPU의 동시적 적업 처리는 CPU의 코어 개수보다 스레드의 개수가 더 많을 때(멀티스레딩 환경), 자원을 효율적으로 배분하고 사용하기 위해 설계된 방식이다.
     * 같은 프로그램 안에서 실행되는 여러 스레드가 read 및 write 작업을 같은 메모리 영역에서 동시에 실행할 경우 동시성 문제가 발생할 수 있다.
     * 동시성 문제는 하나의 스레드가 어떤 메모리의 영역에 데이터를 쓰고있는데, 또 다른 스레드가 같은 메모리 영역의 데이터를 읽거나 쓸 경우 발생할 수 있는 문제이다.
     * 동시성 문제는 싱글스레드에서는 절대 발생 하지 않으며, 멀티 스레드를 운용하는 애플리케이션에서 나타나는 현상이다.
     */
    private static final Object lock = new Object();
    private static int sum = 0;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                synchronized (lock) {
                    sum += i;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 501; i < 1000; i++) {
                synchronized (lock) {
                    sum += i;
                }
                try {
                    Thread.sleep(1);
                    throw new RuntimeException("error");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

        System.out.println("합계 : " + sum);
        System.out.println("멀티 스레드 처리 시간 : " + (System.currentTimeMillis() - start) + "ms");

    }
}
