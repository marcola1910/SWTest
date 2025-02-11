package AStartMazeTunnel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    Node parent;
    int col;
    int row;
    int gcost;
    int hcost;
    int fcost;

    boolean start;
    boolean goal;
    boolean stop;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;

        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.orange);
    }

    public void setAsStart(){
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("Start");
        start = true;

    }

    public void setAsGoal(){
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("Goal");
        goal = true;

    }

    public void setAsSolid(){
        setBackground(Color.black);
        setForeground(Color.white);
        setText("Solid");
        solid = true;

    }

    public void setAsOpen(){
        open = true;
    }

    public void setAsChecked(){
        if(!start && !goal){
            setBackground(Color.orange);
            setForeground(Color.black);
        }
        checked = true;

    }

    public void setAsPath(){
        setBackground(Color.green);
        setForeground(Color.black);

    }


}
