package chapter05.exam03;

public class ThreadSafeLocalReferenceObjectExample {
    /**
     * 지역 객체 참조
     * 지역 변수라 할지라도 객체 참조 변수는 기본형과 다르게 stack영역에 저장되지 않고 메모리의 Heap 영역에 저장된다.
     * 지역적으로 생성된 객체 (ex. 메서드 안에서 객체 생성)가 해당 메서드를 벗어나지 않고 사용 된다면, 스레드는 자신만의 객체를 참조할 수 있게 되어 스레드에 안전하다.
     */

    public static void main(String[] args) {
        ThreadSafeLocalReferenceObjectExample example = new ThreadSafeLocalReferenceObjectExample();

        Thread thread1 = new Thread(example::useLocalObject, "Thread-1");
        Thread thread2 = new Thread(example::useLocalObject, "Thread-2");

        thread1.start();
        thread2.start();
    }

    public void useLocalObject() {

        // 지역 객체 참조. 각 스레드는 이 객체의 독립된 인스턴스의 참조값을 Stack에 가지게 되므로 Thread Safe하다.

        LocalObject localObject = new LocalObject();

        for (int i = 0; i < 5; i++) {
            localObject.increment();
            System.out.println(Thread.currentThread().getName() + " -  " + localObject);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class LocalObject {

        private int value;

        public void increment() {
            value++;
        }

        @Override
        public String toString() {
            return "LocalObject { value = " + value + "}";
        }
    }
}
