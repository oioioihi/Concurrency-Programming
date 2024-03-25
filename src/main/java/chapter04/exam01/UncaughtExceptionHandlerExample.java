package chapter04.exam01;

public class UncaughtExceptionHandlerExample {
    /**
     * [ setUncaughtExceptionHandler ]
     * 대상 스레드에서 발행하는 UncaughtException을 처리하는 인스턴스 메서드
     * setDefaultUncaughtExceptionHandler보다 우선순위가 놓다.
     *
     * @param args
     */
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println("스레드1 시작");

            throw new RuntimeException("스레드1에서 예기치 않은 예외 발생!");

        });
        thread1.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("개별적인 예외처리! 스레드 이름 : " + t.getName() + "에러 : " + e.getMessage());
        });


        Thread thread2 = new Thread(() -> {
            System.out.println("스레드2 시작");

            throw new RuntimeException("스레드2에서 예기치 않은 예외 발생!");

        });
        thread2.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("개별적인 예외처리! 스레드 이름 : " + t.getName() + "에러 : " + e.getMessage());
        });

        thread1.start();
        thread2.start();
    }
}
