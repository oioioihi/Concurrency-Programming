package chapter04.exam01;

public class ThreadExceptionExample {
    /**
     * 기본적으로 스레드의 run()은 예외를 던질 수 없기 때문에, 예외가 발생할 경우 run() 안에서만 예외를 처리할 수 있다.
     * 즉, 스레드 밖에서 예외를 catch할 수 없고 사라진다.
     */
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            throw new RuntimeException("스레드 예외 발생");
        });

        try {
            thread.start();
        } catch (Exception e) {
            notify(e);
        }
    }

    private static void notify(Exception e) {
        // 실행되지 않음
        System.out.println("관리자에게 알림 : " + e);
    }
}
