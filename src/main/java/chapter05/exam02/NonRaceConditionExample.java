package chapter05.exam02;

public class NonRaceConditionExample {
    /**
     * [ Race Condition ]
     * 여러 스레드가 동시에 공유 자원에 액세스하고 조작할 때 스레드 간 액세스하는 순서나 시점에 따라 실행 결과가 달라질 수 있는데, 이것을 경쟁 상태라고 한다.
     * 경쟁상태는 Critical Section Problem 이 해결되지 않은 상태에서 여러 스레드가 동시에 임계영역에 접근해서 공유 데이터를 조작함 으로써 발생하는 상태라 할 수 있다.
     */
    private static int sharedResource = 0;

    public static void main(String[] args) throws InterruptedException {

        // 스레드 30개를 생성하여 공유 리소스를 동시에 증가시킴
        Thread[] incrementThreads = new Thread[30];

        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    synchronized (NonRaceConditionExample.class) { // Monitor를 사용하여 동기화 진행
                        sharedResource++; // 각 스레드가 공유 데이터에 동시에 접근할 수 없다.
                    }
                }
            });
            incrementThreads[i].start();
        }

        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i].join();
        }
        System.out.println("Expected Value = " + (30 * 100));
        System.out.println("Actual Value = " + sharedResource);

    }

}
