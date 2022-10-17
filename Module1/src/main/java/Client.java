import java.util.concurrent.TimeUnit;

public class Client extends Thread {
    private final int id;

    public Client(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        boolean notServed = true;
        while(notServed) {
            System.out.println("Client with id " + id + " is now waiting.");
            try {
                if (task4Java.SEMAPHORE.tryAcquire((int) (Math.random() * 3 + 3), TimeUnit.SECONDS)) {
                    notServed = false;

                    System.out.println("Client with id " + id + " is now with operator.");

                    Thread.sleep((int) (Math.random() * 5000 + 5000));

                    task4Java.SEMAPHORE.release();
                    System.out.println("Client with id " + id + " has been served.");
                } else {
                    System.out.println("Client with id " + id + " is done waiting and will try later.");
                    Thread.sleep(5000);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
