package AStartMazeTunnel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyHandler implements KeyListener {

    DemoPanel dp;

    public KeyHandler(DemoPanel dp) {
        this.dp = dp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_ENTER) {
            System.out.println("Entered!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

//        try {
//            dp.autoSearch();
//            dp.search();
//        } catch (InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }

        ArrayList<Node> path = dp.search();

//        try {
//            dp.autoSearch();
//        } catch (InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }

        //if(path.size() > 0)
          //  System.out.println(path.size());

    }
}
