package example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class superspreader {

    public static int N, M, curr_candidate;
    public static int answerNumber, answerSumFactor, answerSumFactorTmp;
    public static String sAnswer;
    public static boolean visited[];
    public static ArrayList<Integer> AdjList[];
    public static int[] candidates;

    static void dfs_exec(int curr_vertex, int factor) {
        visited[curr_vertex] = true;
        int nextFactor = factor + 1;
        if (AdjList[curr_vertex] != null) {
            for(int i = 0; i < AdjList[curr_vertex].size(); i++){
                if (!visited[AdjList[curr_vertex].get(i)]) {
                    answerSumFactorTmp += factor;
                    dfs_exec(AdjList[curr_vertex].get(i), nextFactor);

                }

            }

        }

    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        long startTime = System.nanoTime();

        System.setIn(new FileInputStream("C:\\Users\\marc.n\\Documents\\Projects\\Samsung\\TestSW\\TestSW\\src\\main\\java\\org\\example\\sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        //                            T = 19;


        for (int contT = 1 ; contT <= T; contT++) {
            String[] infos = new String[30000];
            infos = br.readLine().split(" ");
            N = Integer.parseInt(infos[0]); // patients
            M = Integer.parseInt(infos[1]); // spreader candidates

            candidates = new int[M+1];
            answerSumFactor = 0;
            answerSumFactorTmp = 0;
            curr_candidate = 0;
            AdjList = new ArrayList[N+1];

            infos = br.readLine().split(" ");
            for(int x = 0; x < N;x++) {
                if (AdjList[Integer.parseInt(infos[x])] == null)
                    AdjList[Integer.parseInt(infos[x])] = new ArrayList<Integer>();
                AdjList[Integer.parseInt(infos[x])].add(x+1);
            }

            infos = br.readLine().split(" ");

            for(int x = 0; x < M;x++) {

                if (x<M) {

                    curr_candidate = Integer.parseInt(infos[x]);
                    answerSumFactorTmp = 0;
                    visited = new boolean[30000];
                    if (x==0)
                        answerNumber = curr_candidate;
                    dfs_exec(Integer.parseInt(infos[x]), 1);

                    if (answerSumFactorTmp > answerSumFactor) {
                        answerSumFactor = answerSumFactorTmp;
                        answerNumber = curr_candidate;
                    }

                    if (answerSumFactorTmp == answerSumFactor) {
                        if (curr_candidate < answerNumber)
                            answerNumber = curr_candidate;
                    }

                }
            }
            System.out.println("#" + contT + " " + answerNumber + " " + answerSumFactor);
        }
        long endTime   = System.nanoTime();
        long totalTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("time: "+ totalTime);
    }
}
