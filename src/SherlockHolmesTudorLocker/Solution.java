package SherlockHolmesTudorLocker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

class Solution {
	
	public static void main(String args[]) throws Exception {


		System.setIn(Files.newInputStream(Paths.get("C:\\Users\\marc.n\\IdeaProjects\\SWTest\\sample_input_tudors_locker.txt")));
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));

		String readLine = file.readLine();
		long startTime = System.nanoTime();

		int t = Integer.parseInt(readLine);
		for (int cnt = 1; cnt <= t; cnt++) {
			
			int result = 0;
			
			String[] inputLine = file.readLine().split(" ");
			
			int tamanhoSenha = Integer.parseInt(inputLine[0])-1; // Senha igual o numero de botões -1 no máximo 
			int tamanhoDial = Integer.parseInt(inputLine[1]); 
			int dialFinal = Integer.parseInt(inputLine[2]); 	
			
			inputLine =file.readLine().split(" ");
			int[] botoes = new int[inputLine.length];
			for(int i = 0 ; i< inputLine.length ; i++) {
				botoes[i] = Integer.parseInt(inputLine[i]);
			}
			result = processarTentativas(tamanhoSenha,tamanhoDial, dialFinal, botoes);

			System.out.println("#" + cnt + " " + result);
			
		}
		file.close();

		long endTime = System.nanoTime();
		long totalTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
		System.out.println("time: " + totalTime);

	}

	private static int processarTentativas(int tamanhoSenha, int tamanhoDial, int dialFinal, int[] botoes) {
		int result = 0;
		
		Stack<int[]> stack = new Stack<int[]>();
		// Inicia a processo adicionando o que "recursionar"
		for( int botao : botoes ) {
			stack.push(
				new int[] {
						1,  // Quantidade de botoes pressionados
						botao  // acumulado do pressionamento dos botoes
				}
			);
		}
        
		while(!stack.isEmpty()) {
			int[] passo = stack.pop();
			
			int posicaoNoDial = passo[1] % tamanhoDial;
			
			if(posicaoNoDial == dialFinal) result++;
			
			if(passo[0] < tamanhoSenha) {
				for( int botao : botoes ) {
					stack.push(
						new int[] {
								passo[0]+1,  // Quantidade de botoes pressionados
								(passo[1] % tamanhoDial) + botao  // acumulado do pressionamento dos botoes
						}
					);
				}
			}
			
		}
		return result;
	}
}