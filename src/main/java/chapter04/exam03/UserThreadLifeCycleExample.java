package chapter04.exam03;

public class UserThreadLifeCycleExample {
    /**
     * [ user thread ]
     * 사용자 스레드는 메인 스레드에서 직접 생성한 스레드를 의미한다.
     * 사용자 스레드는 각각 독립적인 생명주기를 가지고 실행하게 되며, 메인스레드를 포함한 모든 사용자 스레드가 종료하게 되면 애플리케이션이 종료된다.
     * 사용자 스레드는 foreground에서 실행되는 높은 우선순위를 가지며, JVM은 사용자 스레드가 스스로 종료될 때까지 애플리케이션을 강제로 종료하지 않고 기다린다.
     * 메인 스레드가 user thread이기 때문에 하위 스레드는 모두 user thread이다.
     */
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("User Thread1 실행 중..");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("User Thread1 종료.");
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("User Thread2 실행 중..");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("User Thread2 종료.");
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("User Thread is Completed. Main Thread will finish soon.");
    }
}
