package chapter04.exam04;

public class ThreadGroupScopeExample {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup topGroup = new ThreadGroup("최상위 스레드 그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 스레드 그룹");

        Thread topGroupThread = new Thread(topGroup, () -> {
            System.out.println("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 전 : " + subGroup.getMaxPriority());
            subGroup.setMaxPriority(7);
            System.out.println("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 후 : " + subGroup.getMaxPriority());
        }, "TopGroupThread");

        Thread subGroupThread = new Thread(subGroup, () -> {
            System.out.println("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 전 : " + subGroup.getMaxPriority());
            topGroup.setMaxPriority(4);
            System.out.println("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 후 : " + subGroup.getMaxPriority());
        }, "SubGroupThread");

        topGroupThread.start();
        subGroupThread.start();

        topGroupThread.join();
        subGroupThread.join();

        System.out.println(topGroupThread.getName() + " : " + topGroupThread.getPriority()); // 런타임 후에 변경되는 우선순위는 반영되지 않음.
        System.out.println(subGroupThread.getName() + " : " + subGroupThread.getPriority());

    }

}
