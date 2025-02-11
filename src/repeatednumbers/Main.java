package repeatednumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static long numberIt = 0;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = Files.newBufferedReader(Paths.get("C:\\Users\\marc.n\\IdeaProjects\\SWTest\\sample_repeated_numbers_input.txt"));

        String line;
        int count = 1;
        br.readLine();
        while ((line = br.readLine()) != null) {
            System.out.println("#" + count + " " + findNearestNumberWithoutRepeats(Long.parseLong(line.split(" ")[1])));
            count++;
        }

        System.out.println("Tempo de execução: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public static boolean hasRepeatedDigits(String ct) {
        String rs = "";
        boolean result = false;
        String numStr = String.valueOf(numberIt);
        Set<Character> digits = new HashSet<>();
        for (char digit : numStr.toCharArray()) {
            rs = rs.concat(!result ? String.valueOf(digit) : ct);
            if (!digits.add(digit) && !result) {
                result = true;
            }
        }
        numberIt = Long.parseLong(rs);
        return result;
    }

    public static boolean hasRepeatedDigits(long number) {
        int mask = 0;
        number = Math.abs(number);

        while (number > 0) {
            long digit = number % 10;
            int bit = 1 << digit;

            if ((mask & bit) != 0) {
                return true; // Duplicate digit found
            }

            // Set the bit in the mask
            mask |= bit;
            number /= 10;
        }
        return false;
    }

    public static long findNearestNumberWithoutRepeats(long number) {
        if (!hasRepeatedDigits(number)) {
            return number;
        }
        long lower = number - 1;
        long upper = number + 1;

        numberIt = number - 1;
        if (!hasRepeatedDigits("0")) return lower; else numberIt++;
        long lowerAux = numberIt;
        numberIt = number + 1;
        if (!hasRepeatedDigits("9")) return upper; else numberIt++;
        long upperAux = numberIt;

        boolean itl = false;
        boolean itu = false;
        while (true) {
            if (hasRepeatedDigits(lowerAux) && !itl) {
                lowerAux--;
            } else {
                itl = true;
                lower = lowerAux;
            }
            if (hasRepeatedDigits(upperAux) && !itu) {
                upperAux++;
            } else {
                itu = true;
                upper = upperAux;
            }

            if(itl && itu) return number - lower <= upper - number ? lower : upper;
        }
    }
}