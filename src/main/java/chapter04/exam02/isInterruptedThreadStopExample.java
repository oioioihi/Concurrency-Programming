package chapter04.exam02;

public class isInterruptedThreadStopExample {

    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("worker 스레드가 작동 중입니다.");
                System.out.println("worker 스레드 인터럽트 상태 : " + Thread.currentThread().isInterrupted());
            }
            System.out.println("worker is interrupted. 스레드가 인터럽트 되었습니다.");
            System.out.println("worker 스레드 인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
            System.out.println("stopper thread finish. ");

        });


        worker.start();
        stopper.start();
    }
}
