import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class SolutionRabbitGroups {
	
	public final static char COELHO = 'C'; 
	
	private static final int[][] DIRECTIONS = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static void main(String args[]) throws Exception {
    	
		//System.setIn(new FileInputStream("sample_input.txt"));
		SolutionUtils.startTimer();
		System.setIn(Solution.class.getClassLoader().getResourceAsStream("rabbit_group_input.txt"));
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int numTestCases = Integer.parseInt(reader.readLine());
		
		int N = 17;
		int M = 11;
		
		char[][] grid = new char[N][M];
		int[][] visited = new int[N][M];
		
		for(int testcase = 1 ; testcase <=numTestCases ; testcase++) {
			
			for(int i = 0 ; i < N ; i++) {
				String[] row =  reader.readLine().split(" ");
				for(int j = 0 ; j < M ; j++) {
					grid[i][j] = row[j].charAt(0);
				}
			}
		}
		
		SolutionUtils.printGrid(grid);
		
		List<Integer> detectContinuousAreas = detectContinuousAreas(grid, visited,COELHO);
		
		int maior = 0;
		int idMaior = 0;
		
		for(int i = 0; i < detectContinuousAreas.size(); i++) {
			if(detectContinuousAreas.get(i) > maior) {
				maior = detectContinuousAreas.get(i);
				idMaior = i+1;
			}
			
		}
		SolutionUtils.printGrid(visited);
		System.out.println("#1 " + idMaior + " " + maior);

		SolutionUtils.stopTimer();
	}
	
	public static List<Integer> detectContinuousAreas(char[][] grid,int[][] visited, char target ) {
		List<Integer> retorno = new ArrayList<Integer>();
		int idAtual = 0;
		for(int i = 0 ; i < grid.length ; i++) {
			for(int j = 0 ; j < grid[0].length ; j++) {
				if(grid[i][j] == COELHO && visited[i][j] == 0) {
					int size = tamanhoDaAdjacencia(grid, visited,i, j, ++idAtual, target);
					retorno.add(size);
				}
			}
		}
		return retorno;
	}

	private static int tamanhoDaAdjacencia(char[][] grid, int[][] visited, int startRow, int startCol, int idAtual, char target) {
		
		Stack<int[]> stack = new Stack<>();
		
		stack.push(new int[]{startRow, startCol});
		
		visited[startRow][startCol] = idAtual;
		
		int size = 0;
		
        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int row = cell[0];
            int col = cell[1];
            size++;
            
            for(int[] directions : DIRECTIONS) {
            	int newRow = row + directions[0]; 
            	int newCol = col + directions[1];
            	if(newRow >=0 && newRow < grid.length && newCol >=0 
            		&& newCol < grid[0].length && grid[newRow][newCol] == COELHO 
            		&& visited[newRow][newCol] == 0) {
            		stack.push(new int[] {newRow,newCol});
            		visited[newRow][newCol] = idAtual;
            	}
            	
            }
            
            
       }
       return size;
	}
	
}
