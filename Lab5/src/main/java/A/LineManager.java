package A;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

import static A.Direction.*;

public class LineManager extends Thread {
    public static final CyclicBarrier BARRIER = new CyclicBarrier(4, new LineManager());
    private static Line line1;
    private static Line line2;
    private static Line line3;
    private static Line line4;


    @Override
    public void run() {
        checkBorders(line1, line2);
        checkBorders(line2, line3);
        checkBorders(line3, line4);

        System.out.println("Line1 : " + Arrays.toString(line1.getRecruits()));
        System.out.println("Line2 : " + Arrays.toString(line2.getRecruits()));
        System.out.println("Line3 : " + Arrays.toString(line3.getRecruits()));
        System.out.println("Line4 : " + Arrays.toString(line4.getRecruits()));
        System.out.println("-----------------------------------");

        if(checkIfLineLooksAtSameDirection(line1.getRecruits())
                + checkIfLineLooksAtSameDirection(line2.getRecruits())
                + checkIfLineLooksAtSameDirection(line3.getRecruits())
                + checkIfLineLooksAtSameDirection(line4.getRecruits()) <= 1) {
            line1.interrupt();
            line2.interrupt();
            line3.interrupt();
            line4.interrupt();
        }
    }

    private void checkBorders(Line line1, Line line2) {
        if(!line1.isRightBorderChanged() && !line2.isLeftBorderChanged()
                && line1.getRecruits()[49] == RIGHT && line2.getRecruits()[0] == LEFT) {
            line1.getRecruits()[49] = LEFT;
            line2.getRecruits()[0] = RIGHT;
        }
    }

    public static void main(String[] args) {
        line1 = new Line();
        line2 = new Line();
        line3 = new Line();
        line4 = new Line();
        line1.start();
        line2.start();
        line3.start();
        line4.start();
    }
}
