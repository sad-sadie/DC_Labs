package B;

public class Hairdresser extends Thread{
    private final Chair chair;

    public Hairdresser(Chair chair) {
        this.chair = chair;
    }

    @Override
    public void run() {
        while(true) {
            try {
                chair.haircutVisitor();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}