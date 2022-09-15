import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;

public class TaskA {

    private static final int[][] field;
    private static final AtomicInteger counter;
    private static final AtomicBoolean found;

    static {
        field = new int[100][100];
        counter = new AtomicInteger(0);
        found = new AtomicBoolean(false);
    }

    private static Runnable getRunnable() {
       return () -> {
           while (!Thread.interrupted()) {
               //int x = getNextRowFromBackPack();
               //System.out.println(Thread.currentThread().getName() + " is now operating with number " + x);
               iterateThroughFieldRow(getNextRowFromBackPack());
           }
       };
    }


    public static void main(String[] args) {
        int x = (int)(Math.random() * 100);
        int y = (int)(Math.random() * 100);

        field[x][y] = 1;

        Thread bee1 = new Thread(getRunnable());
        Thread bee2 = new Thread(getRunnable());
        Thread bee3 = new Thread(getRunnable());

        bee1.start();
        bee2.start();
        bee3.start();
    }

    private static synchronized int getNextRowFromBackPack() {
        if(!found.get()) {
            return counter.getAndIncrement();
        } else {
            currentThread().interrupt();
            return -1;
        }
    }

    private static void iterateThroughFieldRow(int row) {
        if(row == -1) {
            return;
        }
        for(int i = 0; i < 100; ++i) {
            if(field[row][i] == 1) {
                found.set(true);
                System.out.println("Y:" + row + "; X:" + i);
                break;
            }
        }
    }
}
