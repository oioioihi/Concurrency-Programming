package chapter04.exam05;

public class InheritableThreadLocalExample {
    /**
     * [ InheritableThreadLocal ]
     * Inheritable ThreadLocal은 ThreadLocal의 확장 버전으로서 부모 스레드로부터 자식 스레드로 값을 전달하고 싶을 경우 사용할 수 있다.
     * 값의 상속 : 부모 스레드가 inheritable ThreadLocal 변수에 값을 설정하면, 해당 부모 스레드로부터 생성된 자식 스레드들은 부모의 값을 상속받게 된다.
     * 독립성 : 자식 스레드가 상속받은 값을 변경하더라도, 부모 스레드의 값에는 영향을 주지 않는다.
     */

    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        inheritableThreadLocal.set("부모 스레드의 값");

        Thread childThread = new Thread(() -> {
            System.out.println("자식 스레드에서 부모로부터 상속받은 값 : " + inheritableThreadLocal.get());

            inheritableThreadLocal.set("자식 스레드의 새로운 값");
            System.out.println("자식 스레드에서 설정한 후의 값 : " + inheritableThreadLocal.get());
        });

        childThread.start();
        try {
            childThread.join();
        } catch (InterruptedException e) {


        }

        System.out.println("부모 스레드의 값 : " + inheritableThreadLocal.get());


    }
}
