package scaperoom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ivanSolution {

    static int T, answer;

    static String pathFile = "scaperoom4.txt";

    static int H, W;

    static int sj_R, sj_C;

    static char[][] roomMap;

    static String[] inputLine;

    static int[] row = {-1, 1, 0, 0};

    static int[] col = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        long startTime = System.nanoTime();

        try {
            System.setIn(Files.newInputStream(Paths.get(pathFile)));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            T = Integer.parseInt(br.readLine());
            for (int t = 1; t <= T; t++) {
                answer = -1;

                inputLine = br.readLine().split(" ");
                H = Integer.parseInt(inputLine[0]);
                W = Integer.parseInt(inputLine[1]);

                inputLine = br.readLine().split(" ");
                sj_R = Integer.parseInt(inputLine[0]) - 1;
                sj_C = Integer.parseInt(inputLine[1]) - 1;

                roomMap = new char[H][W];

                for(int i=0; i<H; i++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    String str = st.nextToken();
                    for(int j=0; j<W; j++) {
                        roomMap[i][j] = str.charAt(j);
                    }
                }

                /*
                for (char[] row : roomMap)
                    System.out.println(Arrays.toString(row));
                */

                Queue<int[]> qu = new LinkedList<>();
                qu.add(new int[] {sj_R, sj_C, 0, 0});
                roomMap[sj_R][sj_C] = '-';

                while(!qu.isEmpty()) {
                    int[] tmp = qu.poll();

                    int r = tmp[0];
                    int c = tmp[1];

                    int time = tmp[2];

                    int hasUsedPower = tmp[3];

                    for (int i=0; i < 4; i++) {

                        int r1 = r + row[i];
                        int c1 = c + col[i];
                        if (r1 < 1 || r1 >= H-1 || c1 < 1 || c1 >= W-1) continue;
                        if (roomMap[r1][c1] == '-') continue;
                        if (roomMap[r1][c1] == '_') {
                            qu.add(new int[]{r1, c1, time + 1, hasUsedPower});
                            roomMap[r1][c1] = '-';
                        } else if (roomMap[r1][c1] == 'X') {
                            if (hasUsedPower == 0) {
                                while(true) {
                                    r1 = r1 + row[i];
                                    c1 = c1 + col[i];
                                    if(roomMap[r1][c1] == '-') break;
                                    if(((r1 < 1 || r1 >= H-1 || c1 < 1 || c1 >= W-1)) ||
                                            (roomMap[r1][c1] == '_' || roomMap[r1][c1] == 'E')) {
                                        qu.add(new int[]{r1 - row[i], c1 - col[i], time + 1, 1});
                                        break;
                                    }
                                }
                            }
                        } else if(roomMap[r1][c1] == 'E') {
                            if (answer == -1 || answer > time + 1) {
                                answer = time + 1;
                            }
                        }
                    }
                }

                System.out.printf("#%d %d%n", t, answer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Time "+(double)(System.nanoTime() - startTime)/1000000000 + " s");

    }

}
