package chapter04.exam01;

public class DefaultExceptionHandlerExample {
    /**
     * [ UncaughtExceptionHandler ]
     * 모든 스레드에서 발새하는 uncaughtException을 처리하는 정적 메서드
     * catch되지 않은 예외에 의해 Thread가 갑자기 종료했을 때에 호출되는 핸들러 인터페이스이다.
     * 예외가 발생하면 uncaughtException이 호출되고 대상 스레드 t와 예외 e가 인자로 전달된다.
     */
    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                System.out.println("전역적인 예외처리! 스레드 이름 : " + thread.getName() + "에러 : " + throwable.getMessage());
            }
        });

        Thread thread1 = new Thread(() -> {
            throw new RuntimeException("스레드1 예외 발생");
        });

        Thread thread2 = new Thread(() -> {
            throw new RuntimeException("스레드2 예외 발생");
        });

        thread1.start();
        thread2.start();
    }
}
