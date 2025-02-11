package scaperoom;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class ScapeRoomSolution {
    private static int C = 0;
    private static int R = 0;
    private static int W = 0; // col
    private static int H = 0; //row
    private static int T;
    static int[][] possibleResults;
    static int step = 0;

    static ScapeNode startNode, goalNode, currentNode;
    static ArrayList<ScapeNode> openList = new ArrayList<>();
    static ArrayList<ScapeNode> checkedList = new ArrayList<>();
    static ArrayList<ScapeNode> closedList = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        ScapeNode[][] node;

        long startTime = System.nanoTime();

        System.setIn(new FileInputStream("C:\\Users\\marc.n\\IdeaProjects\\SWTest\\sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        possibleResults = new int[T][4];
        String[] infos;
        String nodevalue = "";
        for (int contT = 1 ; contT <= T; contT++) {
            infos = br.readLine().split(" ");
            H = Integer.parseInt(infos[0]); // row
            W = Integer.parseInt(infos[1]); // col
            node = new ScapeNode[W][H];
            infos = br.readLine().split(" ");
            R = Integer.parseInt(infos[0]); // Scol
            C = Integer.parseInt(infos[1]); // Srow
            ScapeNode allocatednode = new ScapeNode();
            for (int i = 0; i < H; i++) {
                infos = br.readLine().split("");
                for(int j = 0; j < W; j++) {

                    if((C-1) == i && (R-1) == j) {
                        startNode = new ScapeNode( "S", j, i, H-1, W-1);
                        node[j][i] = startNode;
                        currentNode = startNode;
                    }
                    else
                        node[j][i] = new ScapeNode(infos[j], j, i, H - 1, W - 1);

                    if(node[j][i].isGoal())
                        goalNode = node[j][i];
                }
            }
            setOnCosts(node);

            autoSearch(node, contT);

            System.out.println("");

        }
        long endTime   = System.nanoTime();
        long totalTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("time: "+ totalTime);
    }

    public static void getCost(ScapeNode node){
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gcost = xDistance + yDistance;

        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hcost = xDistance + yDistance;

        node.fcost = node.gcost + node.hcost;

    }

    public static void setOnCosts(ScapeNode[][] node){
        for(int i=0;i<H;i++)
            for(int j=0;j<W;j++)
                getCost(node[j][i]);
    }

    public static void autoSearch(ScapeNode[][] node, int contT){

        boolean goalReached = false;
        ArrayList<ScapeNode> track = new ArrayList<>();
        openList = new ArrayList<ScapeNode>();

        while(!goalReached && step < (H  * W) ){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setChecked(true);
            checkedList.add(currentNode);
            openList.remove(currentNode);

            if( row -1 > 0)
                openNode(node[col][row-1]);

            if( col -1 > 0)
                openNode(node[col-1][row]);

            if( row + 1 < H)
                openNode(node[col][row+1]);

            if(col + 1 < W)
                openNode(node[col+1][row]);

            int bestNodeIndex = 0;
            int bestNodeCost = 9999999;

            for(int i = 0 ; i < openList.size() ; i++){
                if( openList.get(i).fcost < bestNodeCost){ //testa se dos abertos, encontrou um nó com custo menor
                    bestNodeIndex = i;
                    bestNodeCost = openList.get(i).fcost;

                } else if (openList.get(i).fcost == bestNodeCost) { //se não encontrou, checa se tem o mesmo fcost
                    if(openList.get(i).gcost < openList.get(bestNodeIndex).gcost) { //testa se tem um menor custo agora comparando o gcost, que é o custo a partir do ponto inicial
                        bestNodeIndex = i;
                    }
                }
            }

            if(!openList.isEmpty())
                currentNode = openList.get(bestNodeIndex);


            if(currentNode == goalNode){
                goalReached = true;
                track = trackThePath();
                System.out.println("T:" + contT + " Track Lenght:" + track.size() + " Steps:" + step);
            }

            step++;

        }
        if(!goalReached)
            System.out.println("T:" + contT + " No way out. Steps:" + step);



    }

    private static ArrayList<ScapeNode> trackThePath() {
        ArrayList<ScapeNode> reverseTrack = new ArrayList<>();
        ScapeNode tnode = currentNode;
        while(tnode.getParent() != null){
            reverseTrack.add(tnode);
            tnode = tnode.getParent();
        }

        return reverseTrack;
    }

    public static void openNode(ScapeNode node){
        if(!node.isSolid() && !node.isOpen() && !node.isChecked()){
            node.setOpen(true);
            node.setParent(currentNode);
            openList.add(node);
        }
    }

}
