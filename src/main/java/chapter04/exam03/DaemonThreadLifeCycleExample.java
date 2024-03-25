package chapter04.exam03;

public class DaemonThreadLifeCycleExample {
    /**
     * [ daemon thread ]
     * 데몬 스레드는 JVM에서 생성한 스레드이거나, 직접 데몬 스레드로 생성한 경우를 말한다.
     * 모든 user thread가 작업을 완료하면, daemon thread의 실행 여부에 관계없이 JVM이 데몬 스레드를 강제로 종료하고 애플리케이션을 종료한다.
     * 데몬 스레드는 background에서 실행되며 낮은 우선순위를 가진다.
     * 데몬 스레드는 사용자 스레드를 보조 및 지원하는 성격을 가진 스레드로서, 보통 사용자 작업을 방해하지 않으면서 백그라운드에서 자동으로 작동되는 기능을 가진 스레드이다.
     */
    public static void main(String[] args) throws InterruptedException {

        Thread userThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("User Thread 실행 중..");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("User Thread 종료.");
        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Daemon Thread 실행 중...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

        userThread.join();

        System.out.println("All User Thread is Completed. Main Thread will finish soon.");
    }
}
