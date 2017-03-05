package Week6;

import java.math.BigInteger;

public class FactorThread {
    static BigInteger StartingValue = new BigInteger("0");

    public static void main(String[] args) throws Exception {
        BigInteger n = new BigInteger("21");
        int numberOfThreads = 4;
        Factor[] factors = new Factor[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            factors[i] = new Factor (n, i+2, numberOfThreads);
            factors[i].start();
        }
        try {
            while (StartingValue.intValue() == 0) {
                Thread.sleep(1000);
            }
            for (int i = 0; i < numberOfThreads; i++) {
                factors[i].interrupt();
            }
            BigInteger theother = n.divide(FactorThread.StartingValue);
            System.out.println("First:");
            System.out.println(StartingValue);
            System.out.println("Another:");
            System.out.println(theother);
        }
        catch (InterruptedException e) {
            System.out.println("A thread didn't finish!");
        }
    }
}

class Factor extends Thread {
    BigInteger n;
    BigInteger threadid;
    BigInteger gap;

    public Factor (BigInteger n, int threadid, int gap) {
        this.n = n;
        this.threadid = BigInteger.valueOf(threadid);
        this.gap = BigInteger.valueOf(gap);
    }


    public void run() {
        BigInteger zero = new BigInteger("0");

        while (threadid.compareTo(n) < 0) {
            if (isInterrupted()) {
                break;
            }

            if (n.mod(threadid).compareTo(zero) == 0) {
                FactorThread.StartingValue = threadid;
                break;
            }

            threadid = threadid.add(gap);
        }
    }

    public BigInteger getResult() {
        return FactorThread.StartingValue;
    }
}