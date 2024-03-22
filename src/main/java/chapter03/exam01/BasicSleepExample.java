package chapter03.exam01;

public class BasicSleepExample {
    /**
     * Sleep() 이란?
     * 1. sleep() 메서드는 지정된 시간 동안 현재 스레드의 실행을 일시 정지하고, time waiting(대기상태)로 바쪗다가 시간이 지나면 runnable(실행대기 상태)로 전환한다.
     * 2. native 메서드로 연결되며, system call을 통해 kernel level 모드에서 수행 후 user level 모드로 전환한다.
     * <p>
     * InterruptedException
     * 1. 스레드가 수면 중에 인터럽트 될 경우 InterruptedException 예외를 발생시긴다.
     * 2. 다른 스레드는 잠자고 있는 스레드에게 인터럽트, 즉 중단(멈춤) 신호를 보낼 수 있다.
     * 3. InterruptedException 예외가 발생하면 스레드는 '수면상태'에서 깨어나고 '실행대기 상태'로 전환되어 실행상태를 기다린다.
     */
    public static void main(String[] args) {
        try {
            System.out.println("2초 후에 메세지가 출력됩니다.");
            Thread.sleep(2000);
            System.out.println("Hello");
        } catch (InterruptedException e) {
            // 스레드가 sleep 상태일 때, 다른 스레드가 interrupt 을 걸수 있어서 예외처리가 필수이다.
            throw new RuntimeException(e);
        }
    }
}
