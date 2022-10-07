package B;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LineManager {
    private static Line thread1;
    private static Line thread2;
    private static Line thread3;
    private static Line thread4;
    public static final CustomCyclicBarrier BARRIER = new CustomCyclicBarrier(4, getBarrierRunnable());

    private static Runnable getBarrierRunnable(){
        return () -> {
            List<Integer> count = new ArrayList<>();
            count.add(numberOfAAndB(thread1.getLine()));
            count.add(numberOfAAndB(thread2.getLine()));
            count.add(numberOfAAndB(thread3.getLine()));
            count.add(numberOfAAndB(thread4.getLine()));
            System.out.println(thread1.getLine() + " " + count.get(0));
            System.out.println(thread2.getLine() + " " + count.get(1));
            System.out.println(thread3.getLine() + " " + count.get(2));
            System.out.println(thread4.getLine() + " " + count.get(3));
            System.out.println();
            count = count.stream().sorted().collect(Collectors.toList());
            if(count.get(0).equals(count.get(2)) || count.get(1).equals(count.get(3))){
                thread1.interrupt();
                thread2.interrupt();
                thread3.interrupt();
                thread4.interrupt();
            }
        };
    }

    private static int numberOfAAndB(String line) {
        int number = 0;
        for(int i = 0; i < line.length(); ++i){
            if(line.charAt(i) == 'A' || line.charAt(i) == 'B') {
                ++number;
            }
        }
        return number;
    }


    public static void main(String[] args) {
        thread1 = new Line();
        thread2 = new Line();
        thread3 = new Line();
        thread4 = new Line();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
