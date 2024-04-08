package chapter05.exam02;

public class CriticalSectionExample {
    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(sharedResource::increment);
        Thread thread2 = new Thread(sharedResource::increment);

        thread1.start();
        thread2.start();


    }
}

class SharedResource {
    private int counter = 0; // 공유 데이터

    public void increment() {
        for (int i = 0; i < 100; i++) {

            synchronized (this) { // Entry Section : critical section에 전입하기 위해 진입허가를 요청하는 영역

                //Critical Section : 하나의 스레드만 접근해야하는 영역
                counter++;
                System.out.println(Thread.currentThread().getName() + " : " + counter);

            }// Exit Section : 임계영역에서 빠져나올 때 신호를 알리는 영역.
        }

        // Remainder Section : entry section, critical section, exit section 을 제외한 나머지 영역.
        System.out.println(Thread.currentThread().getName() + " 는 critical section 외부에서 작업을 수행하고 있다.");
    }
}
