package chapter02.exam01;

public class ExtendThreadExample {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();

        //Implement Runnable
        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();
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