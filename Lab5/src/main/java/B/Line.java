package B;

import java.util.List;

import static B.LineManager.BARRIER;

public class Line extends Thread {
    private String line;
    private static final int LINE_SIZE = 100;

    public String getLine() {
        return line;
    }

    public Line() {
        line = randomLine();
    }

    private String randomLine(){
        StringBuilder line = new StringBuilder();
        String[] letters = {"A", "B", "C", "D"};
        for(int i = 0; i < LINE_SIZE; ++i){
            line.append(letters[(int) (Math.random() * 4)]);
        }
        return line.toString();
    }

    private void changeLine(){
        StringBuilder sb = new StringBuilder(line);
        List<Character> letters = List.of('A', 'B', 'C', 'D');
        for(int i = 0; i < LINE_SIZE; ++i){
            if(Math.random() <= 0.25){
                sb.setCharAt(i, letters.get((letters.indexOf(sb.charAt(i)) + 2) % 4));
            }
        }
        line = sb.toString();
    }

    @Override
    public void run() {
        while (true){
            try {
                BARRIER.await();
            } catch (InterruptedException e) {
                break;
            }
            changeLine();
        }
    }
}
