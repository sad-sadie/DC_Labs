package B;

import java.util.concurrent.Semaphore;

public class Chair {
    private static final Semaphore SEMAPHORE = new Semaphore(1);
    private int visitorsInQueue;

    public Chair() {
        visitorsInQueue = 0;
    }

    public synchronized void haircutVisitor() throws InterruptedException {
        if(visitorsInQueue == 0 && SEMAPHORE.availablePermits() == 1) {
            System.out.println("The hairdresser is sleeping now");
            wait();
        }
        System.out.println("The hairdresser is working with visitor now");
        Thread.sleep(1000 * 3);
        if(visitorsInQueue != 0) {
            System.out.println("The hairdresser wakes up another visitor");
            notify();
            --visitorsInQueue;
        } else {
            System.out.println("The hairdresser is sleeping due to visitors absence");
            SEMAPHORE.release();
            wait();
        }
    }

    public synchronized void joinQueue() throws InterruptedException {
        if(SEMAPHORE.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + " is waking the hairdresser up and getting his hair cut");
            notify();
        } else {
            ++visitorsInQueue;
            System.out.println(Thread.currentThread().getName() + " is sleeping in the queue");
            wait();
            System.out.println(Thread.currentThread().getName() + " is waken up and going to get a haircut");
        }
    }
}