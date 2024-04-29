package chapter10.exam01;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleThreadPool {

    private int numThreads;
    private Queue<Runnable> taskQueue;
    private Thread[] threads;
    private volatile boolean isShutdown;

    public SimpleThreadPool(int numThreads) {
        this.numThreads = numThreads;
        this.taskQueue = new LinkedList<>();
        this.threads = new Thread[numThreads];
        this.isShutdown = false;

        for (int i = 0; i < numThreads; i++) {

            threads[i] = new WorkerThread();
            threads[i].start();

        }
    }

    // Queue에 작업을 추가
    public void submit(Runnable task) {
        if (!isShutdown) {
            synchronized (taskQueue) {
                taskQueue.offer(task); // 작업을 추가 한 후
                taskQueue.notifyAll(); // 대기하는 스레드가 있다면 깨워준다.
            }
        }
    }


    // ThreadPool 종료
    public void shutdown() {
        isShutdown = true;
        synchronized (taskQueue) {
            taskQueue.notifyAll(); // 대기하고 있는 스레드가 있다면 깨어준다.
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // 모든 스레드가 작업을 완료할때 까지 기다림.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class WorkerThread extends Thread {

        @Override
        public void run() {
            while (!isShutdown) {
                Runnable task;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutdown) { // 남아있는 task가 더이상 없다면,
                        try {
                            taskQueue.wait(); // 해당 스레드를 wait 시킴
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (!taskQueue.isEmpty()) { // taskQueue가 비어 있지 않다면, (남아있는 task가 존재한다면)
                        task = taskQueue.poll();
                    } else {
                        continue;
                    }
                    task.run(); // task를 수행한다.
                }
            }
        }
    }

}
