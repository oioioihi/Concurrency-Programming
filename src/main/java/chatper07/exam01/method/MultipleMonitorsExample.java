package chatper07.exam01.method;

class BankAccount {
    private final Object lock = new Object();
    private double balance;


    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        synchronized (lock) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        synchronized (lock) {
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    /**
     * 보내는 계좌와 받는 계좌를 동시에 lock을 걸고 로직이 수행되어야한다.
     * synchronized의 단점은 여러개의 monitor을 컨트롤 하면서 사용해야하는 경우,lock객체를 파익하기 어렵다.(가독성)
     */
    public boolean transfer(BankAccount to, double amount) {
        synchronized (this.lock) { // 보내는 계좌를 lock을 건다.
            if (this.withdraw(amount)) { // 보내는 계좌에서 출금이 성공적으로 끝나면
                synchronized (to.lock) { // 받는 계좌도 lock을 건다. (받는 계좌 잔고 = 임계영역)
                    to.deposit(amount); // 송금한다.
                    return true;
                }
            }
            return false;
        }
    }

    public double getBalance() {
        synchronized (lock) {
            return balance;
        }
    }

}

public class MultipleMonitorsExample {
    public static void main(String[] args) {
        BankAccount accountA = new BankAccount(1000);
        BankAccount accountB = new BankAccount(1000);


        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountA.transfer(accountB, 10);
                if (result) {
                    System.out.println("accountA에서 accountB로 10원 송금 성공");
                } else {
                    System.out.println("accountA에서 accountB로 10원 송금 실패");

                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountB.transfer(accountA, 10);
                if (result) {
                    System.out.println("accountB에서 accountA로 10원 송금 성공");
                } else {
                    System.out.println("accountB에서 accountA로 10원 송금 실패");
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" accountA 최종 값 : " + accountA.getBalance() + " accountB 최종 값 : " + accountB.getBalance());
    }

}
