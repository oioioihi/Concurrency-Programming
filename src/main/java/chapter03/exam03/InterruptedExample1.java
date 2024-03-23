package chapter03.exam03;

public class InterruptedExample1 {
    /**
     * [ interrupted() ]
     * 스레드의 인터럽트 상태를 반환하는 정적 메소드이다.
     * 만약 현재 스레드의 인터럽트 상태가 true이면 true를 반환하고, 해당 스레드의 인터럽트 상태를 다시 false로 초기화 하므로 인터럽트를 해제하는 역할을 한다.
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) { // thread의 인터럽트 상태를 true이면, 반환 후 다시 false로 초기화 한다.
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
