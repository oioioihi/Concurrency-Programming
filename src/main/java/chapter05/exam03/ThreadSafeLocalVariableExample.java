package chapter05.exam03;

public class ThreadSafeLocalVariableExample {
    // 기본형 지역 변수는 스레드마다 독립적으로 가지고 있는 stack 영역에 저장되기 때문에, 스레드간에 공유될 수 없으므로 스레드에 안전하다.
    public static void main(String[] args) {
        ThreadSafeLocalVariableExample example = new ThreadSafeLocalVariableExample();

        Thread thread1 = new Thread(() -> {
            example.printNumber(50);
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.printNumber(40);
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }

    public void printNumber(int plus) {

        // Local Variable -> Stack Area. 각 스레드는 이 지역 변수의 독립된 복사본을 가지므로 Thread Safe하다.
        int localSum = 0;

        for (int i = 0; i < 5; i++) {
            localSum += i;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        localSum += plus;
        System.out.println(Thread.currentThread().getName() + " - 현재 합계 : " + localSum);
    }
}
