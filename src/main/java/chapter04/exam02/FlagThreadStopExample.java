package chapter04.exam02;

public class FlagThreadStopExample {
    /**
     * [ flag variable을 이용하여 스레드 종료하기 ]
     * 플래그 변수의 값이 어떤 조건에 만족할 경우 스레드의 실행을 중지하는 방식
     * 플래그 변수는 동시성 문제로 가능한 atomic변수나 volatile 키워드를 사용하도록한다.
     * <p>
     * [ volatile ]
     * java 변수를 Main Memory에 저장하겠다라는 것을 명시하는 것입니다.
     * 매번 변수의 값을 Read할 때마다 CPU cache에 저장된 값이 아닌 Main Memory에서 읽는 것이다.
     * 또한 변수의 값을 Write할 때마다 Main Memory에 까지 작성하는 것이다.
     * <p>
     * 왜?
     * => volatile 변수를 사용하고 있지 않은 MultiThread 어플리케이션에서는 Task를 수행하는 동안 성능향상을 위해 Main Memory에서 읽은 변수 값을 CPU cache에 저장한다.
     * 만약에 Multi Thread환경에서 Thread가 변수 값을 읽어올 때 각각의 CPU cahce에 저장된 값이 다르기 때문에 '변수값 불일치 문제'가 발생한다.
     * 하나의 스레드만 값을 수정하고(write) 나머지 스레드들은 read만 할때 사용하기 적절하다.
     */
    private volatile static boolean running = true;

    public static void main(String[] args) {

        new Thread(() -> {
            int count = 0;
            while (running) {
                count++;
            }
            System.out.println("스레드1 종료");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("스레드2 종료");
            running = false;
        }).start();
    }
}
