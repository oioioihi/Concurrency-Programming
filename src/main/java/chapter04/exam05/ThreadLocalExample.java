package chapter04.exam05;

public class ThreadLocalExample {
    private static ThreadLocal<String> threadLocal = new ThreadLocal();
//    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Hello World!"); // 초기값 지정이 가능하다.

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
