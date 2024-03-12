package chapter02.exam01;

public class ExtendThreadExample {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();

        //Implement Runnable
        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();

        //Anonymous Thread
        Thread anonymousThread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중 ...");
            }
        };
        anonymousThread.start();

        // Anonymous Runnable Thread -> 로직의 재활용 X
        Thread anonymousRunnableThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중 ...");

            }
        });
        anonymousRunnableThread.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중 ...");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중 ...");
    }
}