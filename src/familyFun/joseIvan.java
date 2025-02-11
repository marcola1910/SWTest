package familyFun;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class joseIvan {

    static int T, answer;

    static int members, K;

    static String[] inputLine;

    static int[] sa, parents;

    static String pathFile = "familyfun_sample_input.txt";

    public static void main(String[] args) throws Exception {

        long startTime = System.nanoTime();


        try {
            System.setIn(new FileInputStream(pathFile));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            T = Integer.parseInt(br.readLine());
            for (int t = 1; t <= T; t++) {
                answer = 0;

                inputLine = br.readLine().split(" ");
                members = Integer.parseInt(inputLine[0]);
                K = Integer.parseInt(inputLine[1]);

                sa = new int[members];
                inputLine = br.readLine().split(" ");
                for(int i=0;i<members;i++) {
                    sa[i] = Integer.parseInt(inputLine[i]);
                }

                parents = new int[members];
                inputLine = br.readLine().split(" ");
                for(int i=0;i<members;i++) {
                    parents[i] = Integer.parseInt(inputLine[i]);
                }

                recursiveCalc(0, 1, sa[0]);

                System.out.printf("#%d %d%n", t, answer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Time "+(double)(System.nanoTime() - startTime)/1000000000 + " s");

    }

    private static void recursiveCalc(int idx, int originalStoryLevel, int acumulatedStoryLevel) {
        if(idx == 0 || originalStoryLevel*K <= acumulatedStoryLevel) {
            answer++;
            originalStoryLevel = acumulatedStoryLevel;
        }
        for(int i=0;i<members;i++) {
            if(idx+1==parents[i]) {
                recursiveCalc(i,originalStoryLevel,(acumulatedStoryLevel+sa[i]));
            }
        }
    }
}
