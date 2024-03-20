package chapter02.exam03;

public class NewStateThreadExample {
    /**
     * Thread State
     * New : 스레드 객체는 생성되었지만, 아직 시작되지 않은 상태
     * JVM에는 객체가 존재하지만, 아직 커널 스레드와 매핑되지는 않은 상태
     */
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("스레드 실행 중");
            }
        });

        System.out.println("스레드 상태 : " + thread.getState());
        // thread 객체를 생성하고 start()를 호출하지 않았으므로 New 상태이다.
    }
}
