package familyFun;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class hyunMinChoSolution {
    static int [] levels;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            System.setIn(new FileInputStream("familyfun_sample_input.txt"));
            BufferedReader bRead = new BufferedReader(new InputStreamReader(System.in));

            int numTestCases = Integer.parseInt(bRead.readLine());
            int testCase = 1;
            while(numTestCases > 0) {
                String [] spec= bRead.readLine().split(" ");
                int members = Integer.parseInt(spec[0]);
                //story must exceed the funLevel times, to be considered a new story
                int funLevel = Integer.parseInt(spec[1]);

                //story telling levels of each member
                String[] levelsS = bRead.readLine().split(" ");
                levels = new int[members+1];
                for(int i = 1; i < levels.length-1; i++) {
                    levels[i] = Integer.parseInt(levelsS[i-1]);
                }
                levels[levels.length -1] = Integer.parseInt(levelsS[levelsS.length -1]) ;

                String [] parents = bRead.readLine().split(" ");
                //adjList for the graph/tree
                ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
                for(int i = 0; i < members+1; i++) {
                    adjList.add(new ArrayList<>());
                }
                for(int i = 1; i < parents.length; i++) {
                    int parent = Integer.parseInt(parents[i]);
                    adjList.get(parent).add(i+1);
                }

                HashMap<Integer,Integer> storyLevels = new HashMap<>();
                storyLevels.put(1, levels[1]);
                HashMap<Integer,Integer> storyVersions = new HashMap<>();
                storyVersions.put(1, levels[1]);

                int storiesCount = 1;
                ArrayList<Integer> queue = new ArrayList<>();
                queue.add(1);

                //traverse the graph
                while(!queue.isEmpty()) {
                    for(int node : adjList.get(queue.get(0))) {
                        storyLevels.put(node, storyLevels.get(queue.get(0)) + levels[node]);
                        if(storyLevels.get(node) >= storyVersions.get(queue.get(0)) * funLevel) {
                            storyVersions.put(node, storyLevels.get(node));
                            storiesCount++;
                        } else {
                            storyVersions.put(node, storyVersions.get(queue.get(0)));
                        }
                        queue.add(node);
                    }
                    queue.remove(0);
                }

                System.out.println("#"+testCase + " " + storiesCount);
                testCase++;
                numTestCases--;
            }
            long endTime = System.nanoTime();
            System.out.println("Took "+(double)(endTime - startTime) / 1000000000 + " s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



