package Week6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class FactorizerUser1 {
    public static void main (String[] args) {
        CachedFactorizerThreadSafe factorizer = new CachedFactorizerThreadSafe ();
        Thread tr1 = new Thread (new MyRunnable1(factorizer));
        Thread tr2 = new Thread (new MyRunnable1(factorizer));
        tr1.start();
        tr2.start();

        try {
            tr1.join();
            tr2.join();
        }
        catch (Exception e) {

        }
    }
}

class MyRunnable1 implements Runnable {
    private CachedFactorizerThreadSafe factorizer;

    public MyRunnable1 (CachedFactorizerThreadSafe factorizer) {
        this.factorizer = factorizer;
    }

    public void run () {
        Random random = new Random ();
        synchronized (this){
            factorizer.factor(random.nextInt(100));
        }
    }
}

public class CachedFactorizerThreadSafe {
    private int lastNumber;
    private List<Integer> lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits () {
            return hits;
    }

    public synchronized double getCacheHitRatio () {
            return (double) cacheHits / (double) hits;
    }

    public List<Integer> service (int input) {
        List<Integer> factors = null;
        synchronized (this) {
            ++hits;
            if (input == lastNumber) {
                ++cacheHits;
                factors = new ArrayList<Integer>(lastFactors);
            }
        }

            if (factors == null) {
                factors = factor(input);
                synchronized (this) {
                    lastNumber = input;
                    lastFactors = new ArrayList<Integer>(factors);
                }
            }

            return factors;
    }

    public List<Integer> factor(int n) {
            List<Integer> factors = new ArrayList<Integer>();
            for (int i = 2; i <= n; i++) {
                while (n % i == 0) {
                    factors.add(i);
                    n /= i;
                }
            }

            return factors;
    }
}