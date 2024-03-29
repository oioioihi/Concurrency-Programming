package chapter04.exam05.logger;

public class ThreadLocalLoggerExample {
    /**
     * 스레드는 ThreadLocal에 있는 ThreadLocalMap 객체를 자신의 threadLocals 속성에 저장한다.
     * 스레드 생성 시 threadLocals 기본 값은 null이며, ThreadLocal에 값을 저장할 때 ThreadLocalMap이 생성되고,
     * threadLocals과 연결된다.
     * 스레드가 전역적으로 값을 참조할 수 있는 원리는 스레드가 ThreadLocal의 ThreadLocalMap에 접근해서 여기에 저장된 값을 바로 꺼내어 쓸수 있기 때문이다.
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new LogWorker());
        Thread thread2 = new Thread(new LogWorker());
        Thread thread3 = new Thread(new LogWorker());

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }
}
