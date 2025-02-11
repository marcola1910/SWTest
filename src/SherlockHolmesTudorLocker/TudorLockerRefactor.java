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

public class TudorLockerRefactor {

    static class TudorLocker{
        private int iDialGraduations;
        private int iReleaseLockerNumber;
        private int iNuttons;
        private List<List<Integer>> roller;
        private List<Integer> buttons;

        public TudorLocker(int iNbuttons, int iDialGraduations, int iReleaseLockNumber, String[] numbers ){

            this.iNuttons = iNbuttons;
            this.iDialGraduations = iDialGraduations;
            this.iReleaseLockerNumber = iReleaseLockNumber;

            this.roller = createLockerRoller(iDialGraduations);
            this.buttons = createLockerButtons(iNbuttons, numbers);

        }

        private List<Integer> createLockerButtons(int iNbuttons, String[] numbers) {
            this.buttons = new ArrayList<>();

            for(int i = 0 ; i < iNbuttons; i++){
                this.buttons.add(Integer.parseInt(numbers[i]));
            }
            return this.buttons;
        }

        private List<List<Integer>> createLockerRoller(int iDialGraduations) {
            this.roller = new ArrayList<>();
            for(int i = 0 ; i < iDialGraduations; i++){
                this.roller.add(new ArrayList<>());
                if(i==iDialGraduations-1)
                    this.roller.get(i).add(0);
                else
                    this.roller.get(i).add(i+1);
            }
            return this.roller;
        }

        public List<List<Integer>> getUnlockPossibities(Integer[] numbers){

            this.getPermutations( 0 , numbers );

            return this.roller;
        }

        private List<Integer> getPermutations(int currentindex, Integer[] numbers){
            List<Integer> permutations = new ArrayList<>();

            if(currentindex == numbers.length-1)
                return permutations;

            for(int i = currentindex ; i < numbers.length; i++){

                int temp = numbers[i];
                //swap
                numbers[i] = numbers[currentindex+1];
                numbers[currentindex+1] = temp;
                //register
                permutations.add(numbers[i]);
                System.out.println("combination: " + numbers[i] + " \n" );
                getPermutations(currentindex+1, numbers);
                //unswap
                numbers[i] = numbers[currentindex+1];
                numbers[currentindex+1] = temp;

            }


            return permutations;
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
            infos =  br.readLine().split(" ");

            int iNbuttons = Integer.parseInt(infos[0]);
            int iDialGraduations = Integer.parseInt(infos[1]);
            int iReleaseLockNumber = Integer.parseInt(infos[2]);

            String[] numbers = br.readLine().split(" ");
            Integer[] inumbers = new Integer[numbers.length];

            //Create locker roll based on dial graduations
            TudorLocker tudorsLocker = new TudorLocker(iNbuttons, iDialGraduations, iReleaseLockNumber, numbers);

            //convert
            for(int i=0; i < numbers.length; i++){
                inumbers[i] = Integer.parseInt(numbers[i]);
            }

            //Create permutations
            tudorsLocker.getUnlockPossibities(inumbers);

            System.out.println("");

        }

        long endTime = System.nanoTime();
        long totalTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("time: " + totalTime);

    }
}
