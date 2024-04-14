package chatper07.exam01;

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
