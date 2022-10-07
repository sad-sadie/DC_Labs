package B;

public class CustomCyclicBarrier {
    int initialParties;
    int partiesAwait;
    Runnable cyclicBarrierEvent;

    public CustomCyclicBarrier(int parties, Runnable cyclicBarrierEvent) {
        initialParties = parties;
        partiesAwait = parties;
        this.cyclicBarrierEvent = cyclicBarrierEvent;
    }

    public synchronized void await() throws InterruptedException {
        partiesAwait--;
        if(partiesAwait > 0) {
            this.wait();
        } else {
            partiesAwait = initialParties;
            cyclicBarrierEvent.run();
            notifyAll();
        }
    }
}
