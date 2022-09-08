import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Lab1B {
    private final static int FRAME_WIDTH = 600;
    private final static int FRAME_HEIGHT = 800;
    private final static int SLIDER_MIN = 0;
    private final static int SLIDER_MAX = 100;
    private final static int SLIDER_INIT = 50;
    private final static JSlider slider;
    private static JSpinner spinner1;
    private static JSpinner spinner2;
    private static Thread th1;
    private static Thread th2;
    private static final AtomicInteger semaphore;


    static {
        slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
        semaphore = new AtomicInteger(0);
    }
    private static void swingSetUp(){
        JFrame frame = new JFrame("thread management");
        GridBagConstraints constraints = new GridBagConstraints();
        JPanel panel = new JPanel(new GridBagLayout());

        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        SpinnerModel sm1 = new SpinnerNumberModel(1, 1, 10, 1);
        SpinnerModel sm2 = new SpinnerNumberModel(1, 1, 10, 1);
        spinner1 = new JSpinner(sm1);
        spinner2 = new JSpinner(sm2);


        JLabel label = new JLabel("Is not blocked yet");
        //label.setFont(new Font());

        JButton buttonStart1 = new JButton("Start 1");
        buttonStart1.addActionListener(e -> {
            if(semaphore.get() == 0) {
                semaphore.set(1);
                label.setText("Blocked by first thread");
            }
        });
        JButton buttonStart2 = new JButton("Start 2");
        buttonStart2.addActionListener(e -> {
            if(semaphore.get() == 0) {
                semaphore.set(2);
                label.setText("Blocked by second thread");
            }
        });
        JButton buttonStop1 = new JButton("Stop 1");
        buttonStop1.addActionListener(e -> {
            if(semaphore.get() == 1) {
                semaphore.set(0);
                label.setText("Not blocked");
            }
        });
        JButton buttonStop2 = new JButton("Stop 2");
        buttonStop2.addActionListener(e -> {
            if(semaphore.get() == 2) {
                semaphore.set(0);
                label.setText("Not blocked");
            }
        });

        setCorrectPositions(constraints, 50, 50, 0, 1);
        constraints.insets = new Insets(0,20,0,20);
        panel.add(spinner1, constraints);
        setCorrectPositions(constraints, 50, 50, 1, 1);
        constraints.insets = new Insets(0,20,0,20);
        panel.add(spinner2, constraints);
        setCorrectPositions(constraints, 0, 0, 0, 0);
        constraints.gridwidth = 2;
        panel.add(slider, constraints);
        setCorrectPositions(constraints, 50, 25, 0, 3);
        constraints.insets = new Insets(25,0,0,150);
        panel.add(buttonStart1, constraints);
        setCorrectPositions(constraints, 50, 25, 1, 3);
        constraints.insets = new Insets(25,0,0,0);
        panel.add(buttonStart2, constraints);
        setCorrectPositions(constraints, 50, 25, 0, 4);
        constraints.insets = new Insets(10,0,0,150);
        panel.add(buttonStop1, constraints);
        setCorrectPositions(constraints, 50, 25, 1, 4);
        constraints.insets = new Insets(10,0,0,0);
        panel.add(buttonStop2, constraints);
        setCorrectPositions(constraints, 0, 0, 0, 5);
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10,0,FRAME_HEIGHT - 350,0);
        panel.add(label, constraints);

        frame.setContentPane(panel);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private static void setCorrectPositions(GridBagConstraints constraints, int ipadx, int ipady, int gridx, int gridy){
        constraints.ipadx = ipadx;
        constraints.ipady = ipady;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
    }

    public static void main(String[] args) {
        swingSetUp();

        th1 = new Thread(() -> {
            while (true) {
                synchronized (slider) {
                   if(semaphore.get() == 1) {
                       if(slider.getValue() > 10) {
                           slider.setValue(slider.getValue() - 1);
                       }
                       try {
                           Thread.sleep(10);
                       } catch (InterruptedException e) {
                           break;
                       }
                       th1.setPriority((int) spinner1.getValue());
                   }
                }
            }
        });

        th2 = new Thread(() -> {
            while (true) {
                synchronized (slider) {
                    if(semaphore.get() == 2) {
                        if(slider.getValue() < 90) {
                            slider.setValue(slider.getValue() + 1);
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            break;
                        }
                        th2.setPriority((int) spinner2.getValue());
                    }
                }
            }
        });

        th1.start();
        th2.start();
    }
}