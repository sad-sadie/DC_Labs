package A;

import java.util.Scanner;
import java.util.stream.IntStream;

public class TaskA {

    public static void main(String[] args) {
        System.out.println("Enter the number of bees: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfBees = scanner.nextInt();

        Jug jug = new Jug();
        new Bear(jug).start();
        IntStream.range(0, numberOfBees).forEach(i -> new Bee(jug).start());
    }
}
