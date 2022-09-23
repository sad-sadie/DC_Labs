package A;

public class Bear extends Thread {

    public Jug jug;

    public Bear(Jug jug) {
        this.jug = jug;
    }

    @Override
    public void run() {
        while (true) {
            jug.get();
        }
    }
}
