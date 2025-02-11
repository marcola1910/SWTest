package runningrabbits;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SolutionUtils {
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
