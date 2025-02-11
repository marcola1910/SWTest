package SherlockHolmesTudorLocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class TudorLockerSolution {

    static class LockerNumber{

        private int number;
        private LockerNumber nextNumber;
        public LockerNumber(int number){
            this.number = number;
        }

        public LockerNumber(int number, LockerNumber nextnumber){
            this.number = number;
            this.nextNumber = nextnumber;
        }
        public void setNextNumber(LockerNumber next){
            this.nextNumber = next;
        }
    }

    static class TudorLocker {
        private List<LockerNumber> lockerNumbers = new ArrayList<LockerNumber>();
        private int releaseCondition = 0;
        private int currentPointer = 0;
        private int buttons = 0;

        public TudorLocker(int numbers, int buttons , int releaseCondition) {

            this.releaseCondition = releaseCondition;
            this.buttons = buttons;

            for(int i = 0; i < numbers; i++){
                this.lockerNumbers.add(new LockerNumber(i));

                this.lockerNumbers.get(i).setNextNumber(new LockerNumber(i+1, null));

                if(i == numbers -1)
                    this.lockerNumbers.get(i).setNextNumber(new LockerNumber(0, null));

            }

        }

        public List<List<Integer>> moveToRight(List<Integer> number) {

            List<Integer> tested = new ArrayList<>();
            List<List<Integer>> result = new ArrayList<>();

            for(int e : number){
                boolean moving = true;
                int mv = 0;
                while(moving){
                    LockerNumber lockerNumber = lockerNumbers.get(this.currentPointer);
                    this.currentPointer = lockerNumber.nextNumber.number;
                    mv++;
                    if(e == mv) {
                        moving = false;
//                        System.out.println("Tested locker number " + e);
                        tested.add(e);
                    }
                }

                if(releaseCondition == this.currentPointer) {
//                    System.out.println("Releasing locker number case " + number.toString());
//                    System.out.println("Releasing locker number " + e);
                    result.add(number);
//                    break;
                }

            }

            return result;
        }

    }

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        System.setIn(Files.newInputStream(Paths.get("C:\\Users\\marc.n\\IdeaProjects\\SWTest\\sample_input_tudors_locker.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        String[] infos;
        List<List<Integer>> result = new ArrayList<>();

        for (int contT = 1; contT <= t; contT++) {
            infos = br.readLine().split(" ");
            int N = Integer.parseInt(infos[0]); // N buttons
            int M = Integer.parseInt(infos[1]); // M Dial Graduation
            int K = Integer.parseInt(infos[2]); // K Condition Dial release

            String[] grad = br.readLine().split(" ");

            int[] intgrad = convertGradInt(grad);
            TudorLocker locker = new TudorLocker(N, M, K);

            //Combinations permutation
            List<List<Integer>> permut = gerarPermutacoes(intgrad );

            for ( List<Integer> e : permut ){
                result = locker.moveToRight( e );
            }
            if(result.size() > 0)
                System.out.println("#" + contT + " " + result.get(0).toString() );
            else
                System.out.println("#" + contT + " 0 " );
        }
        long endTime = System.nanoTime();
        long totalTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("time: " + totalTime);


    }

    private static int[] convertGradInt(String[] grad) {
        int[] intgrad = new int[grad.length];
        for (int i = 0; i < grad.length; i++) {
            intgrad[i] = Integer.parseInt(grad[i]);
        }
        return intgrad;
    }

    public static List<List<Integer>> gerarPermutacoes(int[] numeros) {
        List<List<Integer>> resultado = new ArrayList<>();

        permutacaoHelper(numeros, new ArrayList<>(), resultado);

        return resultado;
    }

    private static void permutacaoHelper(int[] numeros, List<Integer> corrente, List<List<Integer>> resultado) {
        if (corrente.size() == numeros.length) {
            resultado.add(new ArrayList<>(corrente));
            return;
        }

        for (int numero : numeros) {
            if (corrente.contains(numero)) {
                continue;
            }

            corrente.add(numero);
            permutacaoHelper(numeros, corrente, resultado);
            corrente.remove(corrente.size() - 1);
        }
    }

}
