package chapter03.exam03;

public class interruptedExceptionExample {
    /**
     * [ InterruptedException ]
     * 대기나 차단 등 블록킹 상태에 있거나, 블록킹 상태를 만나는 시점에서 해당 스레드에게 interrupt가 발생했을 때 생기는 예외이다.
     * InterruptedException이 발생하면 인터럽트 상태는 자동으로 초기화 된다. 즉 Thread.interrupted() 한 것과 같은 상태로 된다.
     * 발생하는 케이스는 다음과 같다.
     * 1. Thread.sleep()
     * 2. Thread.join()
     * 3. Object.wait()
     * 4. Future.get()
     * 5. BlockingQueue.take()
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) { // sleep()중 interrupt 호출이 들어옴.
                System.out.println("thread is interrupted. 스레드가 인터럽트 되었습니다.");
                System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
        });
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        thread.join();
        System.out.println("인터럽트 상태 3: " + thread.isInterrupted());


    }
}
