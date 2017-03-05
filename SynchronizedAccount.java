package Week6;

/**
 * Created by zhouxuexuan on 6/3/17.
 */

public class SynchronizedAccount {
    private int total = 0;
    private final Object write = new Object();
    private final Object acess = new Object();

    public void deposit (int amount) {
        synchronized (write) {
            total += amount;
            System.out.println ("Deposite " + amount);
        }
    }

    public void withdraw (int amount) {
        synchronized (write) {
            if (total >= amount) {
                total -= amount;
                System.out.println ("Withdraw " + amount);
            }
        }
    }

    public int check_balance () {
        synchronized (acess) {
            System.out.println ("Check balance");
            return total;
        }
    }
}
