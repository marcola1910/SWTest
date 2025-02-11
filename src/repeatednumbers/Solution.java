package repeatednumbers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class Solution {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        System.setIn(Files.newInputStream(Paths.get("C:\\Users\\marc.n\\IdeaProjects\\SWTest\\sample_repeated_numbers_input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        long resultplus = 0L;
        long resultminus = 0L;
        String[] infos;
        for (int contT = 1; contT <= t; contT++) {
            infos = br.readLine().split(" ");
            long m = Long.parseLong(infos[1]);
            long mplus = Long.parseLong(infos[1]);
            long minus = Long.parseLong(infos[1]);

            while (hasRepeatedDigits(minus))
                minus--;
            resultminus = minus;

            while (hasRepeatedDigits(mplus))
                mplus++;
            resultplus = mplus;

            System.out.println("#" + contT + " " + closestNum(resultminus, resultplus, m));
        }
        long endTime = System.nanoTime();
        long totalTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("time: " + totalTime);

    }

    public static boolean hasRepeatedDigits(long number) {
        int mask = 0;

        while (number > 0) {
            long digit = number % 10;
            int bit = 1 << digit;

            if ((mask & bit) != 0) {
                return true; // Duplicate digit found
            }

            mask |= bit;
            number /= 10;
        }
        return false;
    }

    private static long closestNum(Long resultminus, long resultplus, long m) {
        if (m - resultminus <= resultplus - m)
            return resultminus;
        else
            return resultplus;
    }
}
