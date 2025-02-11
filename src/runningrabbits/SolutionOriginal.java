package runningrabbits;

import java.util.Scanner;
import java.util.Stack;

class SolutionOriginal {
	
	private static final int[][] DIRECTIONS = {{1,0},{-1,0},{0,1},{0,-1},{-1,1},{-1,-1},{1,-1},{1,1}};
	
	public static void main(String args[]) throws Exception {
    	SolutionUtils.startTimer();
		System.setIn(SolutionOriginal.class.getClassLoader().getResourceAsStream("runnaway_rabbits_input.txt"));
		Scanner scanner = new Scanner(System.in);
		//////// Your algorithm is implemented here. ////////
		int numberOfTestCases = 10;
		
		for(int testCaseNumber = 1; testCaseNumber <= numberOfTestCases; testCaseNumber++) {
			
			int[][] grid = new int[21][21];
			
			int numberOfRabbits = scanner.nextInt();
			
			int foxX = scanner.nextInt();
			int foxY = scanner.nextInt();
			grid[foxX][foxY] = 2;
			for ( int i = 0 ; i < numberOfRabbits ; i++) {
				int rabbitX = scanner.nextInt();
				int rabbitY = scanner.nextInt();
				grid[rabbitX][rabbitY] = 1;
			}
			// fim da carga de dados 
						
			int coelhosComidos = iterativeDFS(grid, new int[]{foxX,foxY});
			grid[foxX][foxY] = 2;
			SolutionUtils.printGrid(grid);
			System.out.println("#" + testCaseNumber + " " + coelhosComidos);
			
		}
		SolutionUtils.stopTimer();	
	}
	
	public static int iterativeDFS(int[][] grid, int[] foxPosition) {
		Stack<int[]> stack = new Stack<int[]>();
		
		for(int i = 0 ; i < DIRECTIONS.length ; i++ ) {
			int newFoxX = foxPosition[0];
			int newFoxY = foxPosition[1];
			int oneDirection = i;
			if(newFoxX > 0 && newFoxX <= 20 && 
			   newFoxY > 0 && newFoxY <= 20) {
				stack.push(new int[] {newFoxX,newFoxY,oneDirection});
			}
		}
		int coelhosComidos = 0;
		
		while(!stack.isEmpty()) {
			int[] movRaposa = stack.pop();
			int X = movRaposa[0];
			int Y = movRaposa[1];
			int D = movRaposa[2];
			
			if(grid[X][Y] == 1) {
				coelhosComidos++;
			}
			
			int newX = X + DIRECTIONS[D][0];
			int newY = Y + DIRECTIONS[D][1];
			
			if(newX > 0 && newX <= 20 && newY > 0 && newY <= 20) {
				stack.push(new int[] {newX,newY,D});
			}
		}
		return coelhosComidos;
	}
	
}
