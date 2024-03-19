package chapter02.exam02;

public class MultiThreadAppTerminateExample {
    /**
     * 1. Multi Thread 인 경우 JVM에서 실행되고 있는 모든 스레드가 종료되어야 애플리케이션이 종료된다.
     * 2. 동일한 코드를 실행하는 각 스레드의 종료 시점은 처리 시간 및 OS의 스케줄링에 의해 결정되므로 매번 다르게 나올 수 있다.
     */
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            thread.start();
        }

        System.out.println("Main Thread 종료");
    }

    static class MyRunnable implements Runnable {
        private final int threadId;

        public MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중...");
            firstMethod(threadId);

        }

        private void firstMethod(int threadId) {
            int localValue = threadId + 100;
            secondMethod(localValue);
        }

        private void secondMethod(int localValue) {
            System.out.println(Thread.currentThread().getName() + " : 스레드 ID : " + threadId + ", Value : " + localValue);
        }
    }
}

