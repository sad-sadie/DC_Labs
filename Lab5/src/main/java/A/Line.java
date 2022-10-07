package A;

import java.util.concurrent.BrokenBarrierException;

import static A.Direction.*;
import static A.LineManager.*;

public class Line extends Thread {
    private final Direction[] recruits;

    public boolean leftBorderChanged;
    public boolean rightBorderChanged;


    public Line() {
        recruits = generateRecruits();
    }

    public Direction[] getRecruits() {
        return recruits;
    }

    public boolean isLeftBorderChanged() {
        return leftBorderChanged;
    }

    public boolean isRightBorderChanged() {
        return rightBorderChanged;
    }

    @Override
    public void run() {
        while(true) {
            try {
                BARRIER.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                break;
            }
            changeDirectionIfNeeded();
        }
    }

    public void changeDirectionIfNeeded() {
        leftBorderChanged = false;
        rightBorderChanged = false;
        for(int i = 0; i < recruits.length - 1; ++i) {
            if(recruits[i] == RIGHT && recruits[i + 1] == LEFT) {
                recruits[i] = LEFT;
                recruits[i + 1] = RIGHT;
                if(i == 0) {
                    leftBorderChanged = true;
                }
                if(i == 48) {
                    rightBorderChanged = true;
                }
                ++i;
            }
        }
    }

}
