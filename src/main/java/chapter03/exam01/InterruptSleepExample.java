package chapter03.exam01;

public class InterruptSleepExample {
    /**
     * [ Sleep() 작동 방식 정리 ]
     * 1. sleep()이 되면 OS 스케줄러는 현재 스레드를 지정된 시간 동안 '대기 상태'로 전환하고 다른 스레드 혹은 프로세스에게 CPU를 사용하도록 한다.
     * 2. 대기 시간이 끝나면 스레드 상태는 바로 'Runnable'가 아닌 'Time Waiting'로 전환되고 CPU가 실행을 재개할 때 까지 기다린ㄷ.
     * 3. 'Runnable' 상태가 되면 스레드는 다음 로직을 실행한다.
     * 4. 동기화 메서드 영역에서 수면 중인 스레드는 획득한 monitor lock을 잃지 않고 계속 유지한다.
     * 5. sleep()중인 스레드에게 인터럽트가 발생할 경우 현재 스레드는 대기에서 해제되고 'Runnable'상태로 전환되어 예외를 처리하게 된다.
     */
    public static void main(String[] args) throws InterruptedException {

        Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("will sleep for 20s. if not interrupted. 20초 동안 잘 예정입니다.");
                Thread.sleep(2000);
                System.out.println("wake up! 인터럽트 없이 잠에서 깨었습니다.");
            } catch (InterruptedException e) {
                System.out.println(" Interrupted while sleeping! 자는 중에 인터럽트가 발생했습니다. ");
            }
        });

        sleepingThread.start();
        Thread.sleep(1000);
        sleepingThread.interrupt();
    }
}
