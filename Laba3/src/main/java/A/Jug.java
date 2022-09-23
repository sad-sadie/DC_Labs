package A;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Jug {

    private static final int MAX_CAPACITY = 100;

    public AtomicInteger capacity;
    public AtomicBoolean semaphore;

    public Jug() {
        capacity = new AtomicInteger(0);
        semaphore = new AtomicBoolean(true);
    }

    public synchronized void put() {
        if(semaphore.get()) {
            System.out.println("Put => " + capacity.get() + " " + Thread.currentThread().getName());

            if (capacity.get() >= MAX_CAPACITY) {
                try {
                    notify();
                    semaphore.set(false);
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            capacity.incrementAndGet();
        }
    }

    public synchronized void get() {
        System.out.println("Get => " + capacity.get());
        if(capacity.get() < MAX_CAPACITY) {
            try {
                semaphore.set(true);
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Get, before while => " + capacity.get());
        while (capacity.get() != 0) {
            capacity.decrementAndGet();
        }
        System.out.println("Get, after while => " + capacity.get());
        notify();
    }
}
