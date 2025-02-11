import java.io.*;
import java.util.*;

public class MathPermutCombine {
    static int N, R;
    static final long MOD = 1000000007;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        R = sc.nextInt();
        System.out.println(permu(N, R));
    }

    public static long permu( int n, int r){
        long Ans= 1;
        for ( int i=n; i>=n - r+ 1 ;i--){
            Ans*=i;
            Ans%=MOD;
        }
        return Ans;
    }
}