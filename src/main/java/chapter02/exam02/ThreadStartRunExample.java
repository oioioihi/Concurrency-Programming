package chapter02.exam02;

public class ThreadStartRunExample {
    /**
     * Thread의 실행
     * 1. main Thread가 새로운 스레드를 생성한다.
     * 2. main Thread가 start()메서드를 호출해서 스레드 실행을 시작한다.
     * 3. 내부적으로 native메서드인 start0()를 호출해서 kernel에게 kernel level thread를 생성하도록 system call을 요청한다. -> private native void start()
     * 4. kernel thread가 생성되고 java thread와 1:1 매핑이 이루어진다.
     * 5. kernel thread는 OS 스케줄러로부터 CPU할당을 받기까지 실행대기 상태에 있따.
     * 6. kernel thread가 스케줄러에 의해 실행상태가 되면 JVM에서 매핑된 자바 스레드의 run() 메서드를 호출한다.
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중 ..");
            }
        });
        thread.start(); // 새로운 스레드가 생성됨
        // thread.run(); // 기존의 스레드에 스택이 추가되어 실행됨
    }
}
