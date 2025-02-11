package runningrabbits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.*;

public class MazeSolution {

    public final static char Hall = 'C';

    private static final int[][] DIRECTIONS = {{0,1},{0,-1},{1,0},{-1,0}};
    public static int[] Sposition;
    public static String[][] Maze;

    public static void main(String[] args) throws IOException {

        long startTime = System.nanoTime();
        int N ;
        int M ;
        int Sx;
        int Sy;

        System.setIn(new FileInputStream("C:\\Users\\marc.n\\IdeaProjects\\SWTest\\sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        Sposition = new int[2];

        for (int contT = 1 ; contT <= T; contT++) {

            String[] infos = new String[30000];
            infos = br.readLine().split(" ");
            Sx = Integer.parseInt(infos[0]); // Sx
            Sy = Integer.parseInt(infos[1]); // Sy

            infos = br.readLine().split(" ");
            N = Integer.parseInt(infos[0]);
            M = Integer.parseInt(infos[1]);


            infos = br.readLine().split(" ");


            Sposition[0] = N;
            Sposition[1] = M;

            char[][] grid = new char[N][M];


        }

        System.out.println("Hello world!");
    }
}



class SUtils {
    private static long inicio;

    public static void startTimer() {
        inicio = System.currentTimeMillis();
    }

    public static void stopTimer() {
        System.out.println("Time: " + (System.currentTimeMillis()-inicio));
    }

    public static void printAdjacencyMap(Map<Integer, Set<Integer>> adjacentARegions) {
        System.out.println("Adjacency of B regions to A regions:");
        for (Map.Entry<Integer, Set<Integer>> entry : adjacentARegions.entrySet()) {
            System.out.print("B region " + entry.getKey() + " is adjacent to A regions: ");
            if (entry.getValue().isEmpty()) {
                System.out.println("None");
            } else {
                System.out.println(entry.getValue());
            }
        }
    }

    public static void printGrid(int[][] grid) {
        for(int i=0;i<grid.length;i++) {
            System.out.print("| ");
            for(int j=0;j<grid[0].length;j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("");
    }

    public static void printGrid(int[] grid) {
        System.out.print("| ");
        for(int i=0;i<grid.length;i++) {
            System.out.print(grid[i] + " ");
        }
        System.out.println("|\n");
    }
    public static void printResults(String fileName) {
        InputStream inputStreamOut = SolutionUtils.class.getClassLoader().getResourceAsStream(fileName);
        Scanner scanner = new Scanner(inputStreamOut);
        while(scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();

    }
    public static void printGrid(char[][] grid) {
        for(int i=0;i<grid.length;i++) {
            System.out.print("| ");
            for(int j=0;j<grid[0].length;j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("");
    }

    public static void printGrid(byte[][] grid) {
        for(int i=0;i<grid.length;i++) {
            System.out.print("| ");
            for(int j=0;j<grid[0].length;j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("");

    }
    // Method to check if the sum of a 3x3 subgrid is 45
    public static boolean isSubgridSumValid(int[][] grid, int row, int col) {
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        int sum = 0;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                sum += grid[i][j];
            }
        }

        return sum == 45;  // Return true if the sum of the subgrid is 45 (valid)
    }

    // Deduplicate invalid positions
    public static List<int[]> deduplicatePositions(List<int[]> invalidPositions) {
        Set<String> uniquePositions = new HashSet<>();
        List<int[]> deduplicatedList = new ArrayList<>();

        for (int[] pos : invalidPositions) {
            // Create a unique string for each position (row-col combination)
            String key = pos[0] + "-" + pos[1];

            if (!uniquePositions.contains(key)) {
                uniquePositions.add(key);
                deduplicatedList.add(pos); // Add to deduplicated list
            }
        }

        return deduplicatedList;
    }
}

