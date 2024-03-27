package chapter04.exam05;

public class ThreadLocalExample {
    private static ThreadLocal<String> threadLocal = new ThreadLocal();
//    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Hello World!"); // 초기값 지정이 가능하다.

    /**
     * [ ThreadLocal ]
     * 자바에서 스레드는 오직 자신만이 접근해서 읽고 쓸수 있는 로컬 변수 저장소를 제공하는데 이를 ThreadLocal 이라고한다.
     * 각 스레드는 고유한 ThreadLocal 객체를 속성으로 가지고 있으며, ThreadLocal은 스레드 간 격리되어 있다.
     * 스레드는 ThreadLocal에 저장된 값을 특정한 위치나, 시점에 상관없이 어디에서나 전역변수처럼 접근해서 사용할 수 있다. 변수 값을 전달하지 않아도 된다.
     * 모든 스레드가 공통적으로 처리해야하는 기능이나, 객체를 제어해야 하는 상황에서 스레드마다 다른 값을 적용해야하는 경우에 사용하면 편리하다.(인증 주체 보관, 트랜잭션 전파, 로그 추적기 등)
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.set("스레드 1의 값");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
        }, "Thread-1").start();

        Thread.sleep(1000);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.set("스레드 2의 값");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
        }, "Thread-2").start();
    }
}
