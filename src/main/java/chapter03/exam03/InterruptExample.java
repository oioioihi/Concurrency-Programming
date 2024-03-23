package chapter03.exam03;

public class InterruptExample {
    /**
     * [ Interrupt() ]
     * interrupt()는 스레드에게 인터럽트가 발생했다는 신호를 보내는 메서드이다.
     * 한 스레드가 다른 스레드를 인터럽트 할수 있고 자기 자신을 인터럽트 할 수도 있다.
     * interrupt()하는 횟수는 제한이 없으며, 인터럽트 할 때 마다 스레드의 인터럽트 상태를 true로 변경한다.
     */
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 start 스레드 1 작업 시작..");
            System.out.println("thread1 state 스레드 1 인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 start 스레드 2 작업 시작..");
            System.out.println("thread2 interrupt thread1 스레드2가 스레드1을 인터럽트 합니다.");
            thread1.interrupt(); // thread1의 인터럽트 상태를 false에서 true로 변경한다.
            System.out.println("thread2 state 스레드 2 인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        });

        thread2.start();
        Thread.sleep(1000);
        thread1.start();

        thread1.join();
        thread2.join();

        System.out.println(" All Thread is completed. 모든 스레드 작업 완료.");

    }
}
