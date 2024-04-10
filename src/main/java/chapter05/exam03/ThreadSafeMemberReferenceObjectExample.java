package chapter05.exam03;

public class ThreadSafeMemberReferenceObjectExample {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new MyRunnalbe(new Company("User"))).start(); // thread 마다 Company 객체를 생성하기 때문에 스레드에 안전한 상태가 된다.
        new Thread(new MyRunnalbe(new Company("User"))).start(); // thread 마다 Company 객체를 생성하기 때문에 스레드에 안전한 상태가 된다.

        Thread.sleep(1000);
        System.out.println("--------------------------------------------------");

        Company company = new Company("User"); // 두 스레드가 하나의 Company 객체를 공유하고 있고, Company 객체의 멤버 변수를 동시에 접근해서 변경이 가능하기 때문에 스레드에 안전하지 못한 상태가 된다.
        new Thread(new MyRunnalbe(company)).start(); // 멤버 변수를 공유 하고 있으므로, 스레드에 안전하지 못함.
        new Thread(new MyRunnalbe(company)).start();

    }
}

class MyRunnalbe implements Runnable {

    private Company company;

    public MyRunnalbe(Company company) {
        this.company = company;
    }

    @Override
    public void run() {
        company.changeName(Thread.currentThread().getName());
    }
}

class Company {
    private Member member;

    public Company(String name) {
        this.member = new Member(name);
    }

    public synchronized void changeName(String name) {
        String oldName = member.getName();
        member.setName(name);
        System.out.println(oldName + " : " + member.getName());
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
