import util.Thing;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class TaskB {
     private static BlockingQueue<Thing> buffer1;
     private static BlockingQueue<Thing> buffer2;
     private static List<Thing> things;

     public static void main(String[] args) {

          buffer1 = new LinkedBlockingQueue<>();
          buffer2 = new LinkedBlockingQueue<>();

          things = IntStream.range(0, 100).boxed()
                  .map(i -> new Thing(i, (int)(Math.random() * 100)))
                  .collect(Collectors.toList());

          int totalThingsSumAtTheStart = things.stream()
                  .mapToInt(Thing::getPrice)
                  .sum();

          System.out.println("Total price at the start: " + totalThingsSumAtTheStart);

          AtomicInteger totalThingsSumAtTheEnd = new AtomicInteger(0);

          Thread ivanov = new Thread(() -> {
               while (things.size() > 0) {
                    int randomNumber = (int) (Math.random() * things.size());
                    try {
                         buffer1.put(things.remove(randomNumber));
                    } catch (InterruptedException e) {
                         throw new RuntimeException(e);
                    }
               }
          });


          Thread petrov = new Thread(() -> {
               while (ivanov.isAlive() || !buffer1.isEmpty()) {
                    try {
                         buffer2.put(buffer1.take());
                         //System.out.println(buffer2);
                    } catch (InterruptedException e) {
                         throw new RuntimeException(e);
                    }
               }
          });

          Thread nechiporchuk = new Thread(() -> {
               while (petrov.isAlive() || !buffer2.isEmpty()) {
                    try {
                         totalThingsSumAtTheEnd.addAndGet(buffer2.take().getPrice());
                         Thread.sleep(1);
                    } catch (InterruptedException e) {
                         throw new RuntimeException(e);
                    }
               }
               System.out.println("Total price at the end: " + totalThingsSumAtTheEnd);
          });

          ivanov.start();
          petrov.start();
          nechiporchuk.start();
     }
}
