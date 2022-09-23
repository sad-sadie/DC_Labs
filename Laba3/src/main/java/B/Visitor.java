package B;

public class Visitor extends Thread{
    private final Chair chair;

    public Visitor(Chair chair){
        this.chair = chair;
    }

    @Override
    public void run() {
        try {
            chair.joinQueue();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}