package AStartMazeTunnel;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        frame.add(new DemoPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

}
