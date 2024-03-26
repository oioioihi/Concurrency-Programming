package chapter04.exam04;

public class ThreadGroupExample {
    /**
     * [ ThreadGroup ]
     * 자바는 ThreadGroup 이라는 객체를 통해서 여러 스레드를 그룹화하는 편리한 방법을 제공한다.
     * 스레드는 반드시 하나의 스레드 그룹에 포함되어야 하며, 명시적으로 스레드 그룹에 포함시키지 않으면 자신을 생성한 스레드가 속해 있는 스레드 그룹에 포함되어 진다.
     * ThreadGroup은 스레드 집합을 나타내며 스레드 그룹에는 다른 스레드 그룹도 포함될 수 있고, 그룹 내의 모든 스레드는 한 번에 종료하거나 중단할 수 있다.
     */
    public static void main(String[] args) {
        // 그룹 생성
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup customThreadGroup = new ThreadGroup("Custom Thread Group");

        // 스레드 생성
        Thread defaultGroupThread = new Thread(new GroupRunnable(), "DefaultGroupThread");
        Thread mainGroupThread = new Thread(mainThreadGroup, new GroupRunnable(), "MainGroupThread");
        Thread customGroupThread = new Thread(customThreadGroup, new GroupRunnable(), "CustomGroupThread");

        defaultGroupThread.start();
        mainGroupThread.start();
        customGroupThread.start();
    }

    static class GroupRunnable implements Runnable {

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + "는 " + currentThread.getThreadGroup().getName() + "에 속한다.");
        }
    }
}
