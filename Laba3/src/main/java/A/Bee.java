package A;

public class Bee extends Thread {
    public Jug jug;

    public Bee(Jug jug) {
        this.jug = jug;
    }

    @Override
    public void run() {
        while (true) {
            jug.put();
        }
    }
}
