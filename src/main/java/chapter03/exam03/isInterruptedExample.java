package chapter03.exam03;

public class isInterruptedExample {
    /**
     * [ isInterrupted() ]
     * 스레드의 인터럽트 상태를 반환하는 인스턴스 메서드이다.
     * 이 메서드는 스레드의 인터럽트 상태를 변경하지 않고 계속 유지한다.
     * 인터럽트 상태를 확인하는 용도로만 사용할 경우 interrupted()보다 이 메서드를 사용하는 것이 좋다.
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("스레드가 작동 중입니다.");
                System.out.println("인터럽트 상태 : " + Thread.currentThread().isInterrupted());
            }
            System.out.println("thread is interrupted. 스레드가 인터럽트 되었습니다.");
            System.out.println("인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        });
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
