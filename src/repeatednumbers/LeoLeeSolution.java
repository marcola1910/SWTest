package repeatednumbers;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class LeoLeeSolution {

    static int N,duplicatedI;
    static boolean checkMap[], uniqueFound;
    static String infos[];
    static long numLongOg,numLongLow,numLongHigh, result;

    public static void main(String args[]) throws Exception {

        long inicio = System.currentTimeMillis();

        System.setIn(new FileInputStream("sample_repeated_numbers_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int tCnt=1; tCnt<=T; tCnt++) {

            infos = br.readLine().split(" ");

            numLongOg = Long.valueOf(infos[1]);
            numLongLow = findNumber(infos[1],-1);
            numLongHigh = findNumber(infos[1],+1);
            result = -1;
            if(numLongOg - numLongLow <= numLongHigh -numLongOg)result = numLongLow;
            else result = numLongHigh;

            System.out.println("#" + tCnt + " " +result);
        }System.out.println("Time:" + (System.currentTimeMillis()- inicio));

    }
    static long findNumber(String OgNo,int operator) {

        uniqueFound = false;
        String inVaLue = new String(OgNo);
        long retValue =  Long.valueOf(inVaLue);

        while(!uniqueFound) {
            checkMap = new boolean[10];
            uniqueFound = true;
            String temp = "";
            for(int i =0; i<inVaLue.length(); i++){
                int no = Integer.parseInt(String.valueOf(inVaLue.charAt(i)));
                temp = temp+no;
                if(!checkMap[no]) {
                    checkMap[no] = true;
                }else{
                    uniqueFound =false;
                    duplicatedI=i;
                    break;
                }
            }
            if (uniqueFound) break;
            retValue = Long.valueOf(temp);
            long calcNo = (long) Math.pow(10,inVaLue.length()- (duplicatedI+1));
            retValue*= calcNo;
            retValue+= (calcNo/2)+(calcNo/2*operator);
            retValue+= operator;
            inVaLue = String.valueOf(retValue);
        }
        return retValue;
    }
}
