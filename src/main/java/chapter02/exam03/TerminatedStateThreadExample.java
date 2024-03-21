package chapter02.exam03;

public class TerminatedStateThreadExample {
    /**
     * Thread State
     * TERMINATED : 실행이 완료된 스레드 싱태
     * 실행이 완료되었거나, 오류나 예외를 만나 비정상적으로 종료된 스레드 상태
     */
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("스레드 실행 중");
            }
        });

        thread.start();
        thread.join(); // main thread는 'thread'객체가 종료될때 까지 기다림.
        System.out.println("thread 스레드 상태 : " + thread.getState());
    }
}
