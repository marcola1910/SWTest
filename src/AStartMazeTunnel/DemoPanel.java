package AStartMazeTunnel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DemoPanel extends JPanel {

    final int maxCol = 8;
    final int maxRow = 8;
    final int nodeSize = 35;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    Node[][] node = new Node[maxRow][maxCol];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    boolean goalReached = false;
    int step = 0;
    int stamina = 1;

    public DemoPanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxRow, maxRow));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);
            col++;
            if (col >= maxCol){
                col = 0;
                row++;
            }
        }

        setStartNode(7,1);

        setGoalNode(7,7);

        setSolidNode(7,3);
        setSolidNode(6,3);
        setSolidNode(5,3);
        setSolidNode(4,3);
        setSolidNode(3,3);
        setSolidNode(2,3);
        setSolidNode(2,4);
        setSolidNode(2,5);
        setSolidNode(2,6);
        setSolidNode(6,6);
        setSolidNode(6,7);
        setSolidNode(6,5);
        setSolidNode(1,4);
        setSolidNode(1,6);
        setSolidNode(6,1);
        setSolidNode(6,2);

        setCostOnNodes();

    }

    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];

    }

    private void setSolidNode(int col, int row) {
        node[col][row].setAsSolid();

    }

    private void getCost(Node node){
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gcost = xDistance + yDistance;

        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hcost = xDistance + yDistance;

        node.fcost = node.gcost  + node.hcost;

        if(node != startNode ){ // && node != goalNode){
            node.setText("<HTML>[" + node.col +"," + node.row +  "]<BR>F:" + node.fcost + "<BR>G:" + node.gcost + "<BR>H:" + node.hcost);
        }

    }

    private void setCostOnNodes(){
        int col = 0;
        int row = 0;

        while(col < maxCol && row < maxRow){
            getCost(node[col][row]);
            col++;
            if(col >= maxCol){
                col = 0;
                row++;
            }
        }

    }

    public ArrayList<Node> search(){

        ArrayList<Node> path = new ArrayList<>();

        if(!goalReached) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            if ( (row - 1) >= 0)
                openNode(node[col][row - 1]);

            if ( (col - 1) >= 0)
                openNode(node[col - 1][row]);

            if (col + 1 > 0 && (col + 1) < maxCol)
                openNode(node[col + 1][row]);

            if (row + 1 > 0 && (row + 1) < maxRow)
                openNode(node[col][row + 1]);

            int bestNodeIndex = 0;
            int bestNodefCost = 9999;

            for (int i = 0; i < openList.size(); i++) {

                if (openList.get(i).fcost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fcost;
                } else if (openList.get(i).fcost == bestNodefCost) {
                    if (openList.get(i).gcost < openList.get(bestNodeIndex).gcost)
                        bestNodeIndex = i;
                }
            }

            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                path = trackThePath();
            }
            step++;
        }

        return path;
    }

    public void autoSearch() throws InterruptedException {
        while(!goalReached && step < 500){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            if(row -1 > 0 )
                openNode(node[col][row-1]);

            if(col -1 > 0)
                openNode(node[col-1][row]);

            if(col + 1 > 0 && (col + 1) < maxCol )
                openNode(node[col+1][row]);

            if(row +1 > 0 && (row + 1) < maxRow )
                openNode(node[col][row+1]);

            int bestNodeIndex = 0;
            int bestNodefCost = 9999;
            for(int i=0; i < openList.size(); i++){
                if(openList.get(i).fcost < bestNodefCost  ){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fcost;
                }
                else if(openList.get(i).fcost == bestNodefCost){
                    if(openList.get(i).gcost < openList.get(bestNodeIndex).gcost )
                        bestNodeIndex = i;
                }
            }

            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;

        }

    }

    public void openNode(Node node){
        if(stamina > 0 && node.solid)
            tunnelling(node);
        else if(!node.open && !node.checked && !node.solid){
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private void tunnelling(Node node) {
        stamina--;
        node.setAsOpen();
        node.parent = currentNode;
        openList.add(node);

    }

    private ArrayList<Node> trackThePath(){
        Node current = goalNode;
        ArrayList<Node> path = new ArrayList<>();

        while(current != startNode){
            current = current.parent;

            if(current != startNode){
                current.setAsPath();
                path.add(current);
            }
        }
        return path;
    }
}
