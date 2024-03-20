package chapter02.exam03;

public class RunnableStateThreadExample {
    /**
     * Thread State
     * RUNNABLE : 실행 중이거나 실행 가능한 스레드 상태
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < 100; i++) {
                        if (i % 100 == 0) {
                            System.out.println("스레드 상태 : " + i + Thread.currentThread().getState());
                        }
                    }
                }
            }
        });
        thread.start();
    }
}
