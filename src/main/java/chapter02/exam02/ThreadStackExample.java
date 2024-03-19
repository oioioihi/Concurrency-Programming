package chapter02.exam02;

public class ThreadStackExample {
    /**
     * Thread Stack
     * 1. 스레드가 생성되면 해당 스레드를 위한 스택이 같이 만들어진다.
     * 2. 스택은 각 스레드마다 독립적으로 할당되어 작동하기 때문에 스레드간 접근하거나, 공유할 수 없고 이는 스레드로붙터 안전하다 할 수 있다.
     * 3. 스택은 OS에 따라 크기가 주어지고 주어진 크기를 넘기게 되면 StackOverFlowError가 발생하게 된다.
     * 4. 스택은 frame으로 구성 되어 있고, 로컬변수(지역변수, 파라미터) 및 객체 참조 변수 등을 저장하고 있다.
     */
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            thread.start();
        }
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

