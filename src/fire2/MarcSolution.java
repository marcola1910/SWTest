package fire2;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class MarcSolution {

    static String filepath = "fire2_input.txt";
    static Integer T, N, M, K = 0;
    static String[] inputLine;
    static int[][] map;
    static String result;
    static String[] ignite;

    public static void main(String[] args) throws IOException {

        long startTime = System.nanoTime();
        System.setIn(new FileInputStream(filepath));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt( br.readLine() );

        for(int i=1; i <= T; i++){

            inputLine = br.readLine().split(" ");

            N = Integer.parseInt(inputLine[0]);
            M = Integer.parseInt(inputLine[1]);
            K = Integer.parseInt(inputLine[2]);

            map = new int[N][M];

            for(int l=0; l < N; l++){
                inputLine = br.readLine().split(" ");

                for(int m=0; m < M; m++){
                    map[l][m] = Integer.parseInt( inputLine[m] );
                }
            }

            ignite = new String[K];

            for(int k=0; k < K; k++){
               ignite[k] = br.readLine() ;
            }

            result = startFire(map, ignite );

        }
    }

    private static String startFire(int[][] map, String[] ignite) {

        int maxFireCells, maxFireTime = 0;
        int[] row = {-1, 1, 0, 0};
        int[] col = {0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i< ignite.length ; i++){
            int[] initpos = new int[3];

            String[] iniposvalues = ignite[i].split(" ");

            initpos[0] = Integer.parseInt( iniposvalues[0]);
            initpos[1] = Integer.parseInt( iniposvalues[1] );
            initpos[2] = 0;
            q.add( initpos );

        }

        while(!q.isEmpty()){
            int currentfire = 0;
            int[] temp = q.poll();

            int r = temp[0];
            int c = temp[1];

            int time = temp[2];

            for(int i=0; i < 4; i++) {

                int newrow = r + row[i];
                int newcol = c + col[i];

                if(newcol >= M || newrow >= N || newrow < 0 || newcol < 0 ) break;
                if(map[newrow][newcol] == 0) break;
                if(map[newrow][newcol] > 0){
                    map[newrow][newcol] --;
                    if(map[newrow][newcol] > 0) currentfire++;
                    q.add( new int[]{newrow, newcol, time++} );
                }


            }


        }


        return null;
    }
}
