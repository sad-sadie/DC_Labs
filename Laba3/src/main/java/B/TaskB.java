package B;

import java.util.Scanner;

public class TaskB {

    private void run(int numberOfVisitors) {
        Chair chair = new Chair();
        Hairdresser hairdresser = new Hairdresser(chair);
        hairdresser.setName("hairdresser");
        hairdresser.start();
        for(int i = 0; i < numberOfVisitors; ++i) {
            try {
                Thread.sleep(1000 * (int) (Math.random() * 3));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Visitor visitor = new Visitor(chair);
            visitor.setName("visitor-" + i);
            visitor.start();
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of visitors: ");
        new TaskB().run(new Scanner(System.in).nextInt());
    }
}
