package familyFun;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class leoLeeSolution {

    static String infos[],infos2[];
    static long result;
    static int N,K;
    static ArrayList<Integer[]>[] members;// 0 id, SA

    public static void main(String args[]) throws Exception {

        long inicio = System.currentTimeMillis();

        System.setIn(new FileInputStream("familyfun_sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int tCnt=1; tCnt<=T; tCnt++) {
            infos = br.readLine().split(" ");
            N= Integer.parseInt(infos[0]);
            K= Integer.parseInt(infos[1]);
            result =1;
            members = new ArrayList[N+1];
            infos = br.readLine().split(" ");
            infos2 =  br.readLine().split(" ");
            for(int i =0; i<N; i++) {
                int antecessor = Integer.parseInt(infos2[i]);
                int SA = Integer.parseInt(infos[i]);
                if(members[antecessor]==null)members[antecessor] = new ArrayList<Integer[]>();
                members[antecessor].add(new Integer[] {i+1,SA});
            }
            for(Integer[] person : members[0]) {
                goDeep(person[0],person[1],person[1]);
            }
            System.out.println("#" + tCnt + " " +result);
        }System.out.println("Time:" + (System.currentTimeMillis()- inicio));
    }

    static void goDeep(int id,int sum,int original) {

        if(members[id] == null) return;
        for(Integer[] person : members[id]) {
            int newSum = sum+person[1];
            int newOriginal = original;
            if(original*K <= newSum) {
                result++;
                newOriginal = newSum;
            }
            goDeep(person[0],newSum,newOriginal);
        }
    }


}
