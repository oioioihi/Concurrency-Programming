package chapter03.exam02;

public class InterruptJoinExample {
    /**
     *
     */
    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        Thread longRunningThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("긴 작업 스레드가 계속 실행 중...");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(" 긴 작업 스레드가 interrupt 되었습니다. ");
                mainThread.interrupt(); // join을 기다리고 있는 main thread에 interrupt를 걸면, main thread는 WAITING 상태에서 다시 RUNNABLE 상태가 되어 예외를 처리한다.
            }
        });

        Thread interruptingThread = new Thread(() -> {
            try {
                System.out.println("Interrupting Thread가 2초후에 longRunning Thread를 인터럽트 합니다.");
                Thread.sleep(1000);
                longRunningThread.interrupt();
                System.out.println("Interrupt Complete. 인터럽트 완료.");
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });

        longRunningThread.start();
        interruptingThread.start();
        try {
            System.out.println("main thread가 longRunning thread의 완료를 기다립니다.");
            longRunningThread.join();
            System.out.println("메인 스레드 작업 완료.");
        } catch (InterruptedException e) {
            System.out.println("main thread is interrupted. 메인 스레드 비정상 종료");
        }
    }
}
