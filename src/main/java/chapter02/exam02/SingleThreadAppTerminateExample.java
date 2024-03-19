package chapter02.exam02;

public class SingleThreadAppTerminateExample {
    /**
     * 1. 스레드는 run() 메서드의 코드가 모두 실행되면 스레드는 자동으로 종료한다.
     * 2. 스레드는 예외가 발생할 경우 종료되며 다른 스레드에 영향을 미치지 않는다.
     * 3. Single Thread는 사용자 스레드(user thread)가 없는 기본 main thread 만 있는 상태이다.
     * => main thread 만 종료되면 애플리케이션이 종료된다.
     */
    public static void main(String[] args) {

        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        System.out.println("sum : " + sum);
        System.out.println("메인 스레드 종료");
    }

}