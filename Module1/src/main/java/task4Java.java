import java.util.concurrent.Semaphore;

public class task4Java {
    public static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            new Client(i).start();
            Thread.sleep(1000);
        }
    }
}