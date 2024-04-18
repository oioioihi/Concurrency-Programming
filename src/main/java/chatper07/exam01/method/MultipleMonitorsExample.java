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

    public boolean transfer(BankAccount to, double amount) {
        synchronized (this.lock) {
            if (this.withdraw(amount)) {
                synchronized (to.lock) {
                    to.deposit(amount);
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
