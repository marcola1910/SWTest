package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class SpreaderSolution {

    public static int[] findMaxValueAndIndex(int[] acand) {
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;

        for (int i = 0; i < acand.length; i++) {
            if (acand[i] > maxValue) {
                maxValue = acand[i];
                maxIndex = i;
            }
        }

        return new int[]{maxValue, maxIndex};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        int testCases= Integer.parseInt(line[0]);
        line = br.readLine().split(" ");
        int NP= Integer.parseInt(line[0]);

        DFSGraph graph = new DFSGraph(NP+1);

        int NC= Integer.parseInt(line[1]);

        String[] candidates = br.readLine().split(" ");

        String[] mcand = br.readLine().split(" ");

        int[] acand = new int[NC];
        for(int i = 0; i < NC; i++){
            acand[i] = Integer.parseInt(mcand[i]);
        }

        for(int i = 0; i < testCases; i++){
            for(int j = 0; j < candidates.length  ; j++){
                graph.addEdge(Integer.parseInt(candidates[j]), j+1);
            }

            int[] rcand = new int[NC];

            for ( int k = 0 ; k <  NC ; k++ ) {
                rcand[k] = graph.DFS(acand[k]);

            }
            int result[] = findMaxValueAndIndex(rcand);
            System.out.println("#" + (i+1) + " " + acand[result[1]] + " " + result[0] );
        }


    }


    static class DFSGraph {
        private int vertex;
        private LinkedList<Integer>[] adjList;
        private int factor = 0;
        private int score;

        public DFSGraph(int nodes) {
            this.vertex = nodes;
            this.adjList = new LinkedList[nodes];
            for (int i = 0; i < nodes; i++) {
                this.adjList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int from, int to) {
            adjList[from].add(to);
        }

        public int DFS(int start) {
            boolean[] visited = new boolean[vertex];
            this.score = 0;
            this.factor = 0;
            DFSTravel(start, visited);
            return this.score;
        }

        private void DFSTravel(int current, boolean[] visited) {
            visited[current] = true;
            //System.out.println("position: " + current );
            if(adjList[current].size() > 0 )
                this.factor++;


            for (int neighbor : adjList[current]) {
                score += 1 * this.factor;

                if (!visited[neighbor]) {
                    DFSTravel(neighbor, visited);

                }
            }
        }

    }
}