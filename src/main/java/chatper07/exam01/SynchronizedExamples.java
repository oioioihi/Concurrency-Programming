package chatper07.exam01;

/**
 * 모니터 내에서 이미 synchronized 영역에 들어간 스레드가 다시 모니터 영역으로 들어갈 수 있는데, 이를 모니터 재진입이라고 한다.
 * 재진입 가능하다는 것은 락의 획득이 메서드 호출 단위가 아닌, 스레드 단위로 일어난다는 것을 의미하며 이미 락을 획득한 스레든은 같은 락을 얻기 위해,
 * 대기할 필요 없이 synchronized 블록을 만났을때 같은 락을 확보하고 진입 가능하다.
 */
public class SynchronizedExamples {
    private static int staticCount = 0;
    private int instanceCount = 0;

    public static void staticBlock() {
        synchronized (SynchronizedExamples.class) { // 클래스 메서드의 동기화는 해당 클래스 타입으로 한다.
            staticCount++;
            System.out.println("staticCount : " + staticCount);
        }
    }

    public static synchronized void staticMethod() {
        staticCount++;
        System.out.println("staticCount : " + staticCount);

    }

    public static void main(String[] args) {
        SynchronizedExamples synchronizedExamples = new SynchronizedExamples();

        Thread thread1 = new Thread(synchronizedExamples::instanceMethod);
        Thread thread2 = new Thread(synchronizedExamples::instanceBlock);
        Thread thread3 = new Thread(SynchronizedExamples::staticMethod);
        Thread thread4 = new Thread(SynchronizedExamples::staticBlock);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    public synchronized void instanceMethod() {
        instanceCount++;
        System.out.println("instanceCount : " + instanceCount);

    }

    public void instanceBlock() {
        synchronized (this) { // 인스턴스 메서드 동기화와 클래스 메서드 동기화는 다르다.
            instanceCount++;
            System.out.println("instanceCount : " + instanceCount);
        }
    }
}
