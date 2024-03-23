package chapter03.exam02;

public class BasicJoinExample {
    /**
     * Join의 개념
     * <br>
     * join() 메서드는 한 스레드가 다른 스레드가 종료될 때까지 실행을 중지하고 대기상태에 들어갔다가 다른스레드가 종료되면 Runnable 상태로 전환된다.
     * 스레드의 순서를 제어하거나 다른 스레드의 작업을 기다려야 하거나 순차적인 흐름을 구성하고자 할 때 사용할 수 있다.
     * Object 클래스의 wait() native method로 연결되며 system call을 통해 kernel 모드로 수행한다.
     * 내부적으로 wait() & notify() 흐름을 가지고 제어한다.
     * <p>
     * <br>
     * Join() 작동 방식
     * <br>
     * 1. join()을 실행하면 OS 스케줄러는 join()을 호출한 스레드를 WAITING 상태로 전환하고, 호출 대상 스레드에게 CPU 를 할당한다.
     * 2. 호출 대상 스레드의 작업이 종료되면 join()을 호출한 스레드는 WAITING -> RUNNABLE 로 전환되고 CPU가 실행을 재개할 때 까지 기다린다.
     * 3. join()을 호출할 스레드가 CPU를 할당 받으면 다음 로직을 수행한다.
     * 4. 호출 대상 스레드가 여러 개일 경우 각 스레드의 작업이 종료될 때 까지 join()을 호출한 스레드는 대기와 실행을 재개하는 흐름이 반복된다.
     * 5. join()을 호출한 스레드가 interrupt 되면 해당 스레드는 대기에서 해제되고 실행상태로 전환되어 예외를 처리하게 된다.
     */
    public static void main(String[] args) {

        Thread helloThread = new Thread(() -> {
            try {
                System.out.println("스레드가 3초 동안 작동합니다.");
                Thread.sleep(3000);
                System.out.println("스레드 작동 완료.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // helloThread가 모든 실행을 종료하게 되면 WAITING 상태에 있는 메인스레드(호출자)에 notify()을 날리고, 다시 RUNNABLE 상태로 만든다.
        });

        System.out.println("메인 스레드가 다른 스레드의 완료를 기다립니다. main thread will wait until 'thread' is finished. ");
        helloThread.start();
        try {
            helloThread.join(); // helloThread.join()호출 후 호출자인 main thread는 바로 wait()함수를 호출당하여 WAITING상태로 전환된다.
            // helloThread가 종료되고, main thread는 이후 로직을 실행한다.
        } catch (InterruptedException e) {
            System.out.println("main thread got interrupted while waiting 'thread' terminate.");
            throw new RuntimeException();
        }

        System.out.println("main thread finish. 메인스레드가 정상 종료되었습니다.");
    }
}
