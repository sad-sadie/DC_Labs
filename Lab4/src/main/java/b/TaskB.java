package b;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TaskB {
    private static final String FILE_NAME = "garden.txt";
    private final boolean[][] garden = new boolean[10][10];

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();


    private void start (){
        new Thread(this::lookAfterGarden).start();
        new Thread(this::changeNature).start();
        new Thread(this::writeToFile).start();
        new Thread(this::printFromFile).start();
    }

    private void printFromFile() {
        while (true) {
            StringBuffer sb = new StringBuffer();
            readLock.lock();
            try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    if(line.isBlank()){
                        sb = new StringBuffer();
                    }
                    line = br.readLine();
                }
                System.out.println(sb);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            readLock.unlock();
            proceed(1000);
        }
    }


    private void writeToFile() {
        writeLock.lock();
        try (Writer output = new BufferedWriter(new FileWriter(FILE_NAME))) {
            output.append("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writeLock.unlock();
        while (true) {
            writeLock.lock();
            try (Writer output = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                StringBuffer sb = new StringBuffer();
                sb.append(System.lineSeparator());
                for (boolean[] gardenRow : garden) {
                    for (boolean gardenBed : gardenRow) {
                        sb.append(gardenBed ? "1" : "0");
                    }
                    sb.append(System.lineSeparator());
                }
                System.out.println("Garden was written into the file");
                output.append(sb);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writeLock.unlock();
            proceed(1000);
        }
    }


    private void changeNature() {
        while(true) {
            for (int i = 0; i < garden.length; ++i) {
                for (int j = 0; j < garden[0].length; ++j) {
                    if (Math.random() * garden.length <= garden.length / 20.0) {
                        System.out.println("[" + i + " " + j + "] was changed by nature");
                        garden[i][j] = !garden[i][j];
                    }
                    proceed(30);
                }
            }
        }
    }


    private void lookAfterGarden(){
        while(true) {
            for (int i = 0; i < garden.length; ++i) {
                for (int j = 0; j < garden[0].length; ++j) {
                    if (!garden[i][j]) {
                        System.out.println("Gardener looked after [" + i + " " + j + "]");
                        garden[i][j] = true;
                        proceed(500);
                    }
                }
            }
        }
    }

    private void proceed(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new TaskB().start();
    }
}
