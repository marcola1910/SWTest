
import java.util.*;
class SortAPI{
    static class MyComp implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1); //Descending sort
        }
    }

    public static void main(String args[]) {
        int myints[] = {32, 71, 12, 45, 26, 80, 53, 33};
        Integer[] myIntegers = new Integer[myints.length];
        for (int i = 0; i < myints.length; i++) {
            myIntegers[i] = myints[i];
        }

        Arrays.sort(myints, 0, 4); //(12 32 45 71)26 80 53 33
        Arrays.sort(myints, 4, 8); //  12 32 45 71(26 33 53 80)
        Arrays.sort(myints); // (12 26 32 33 45 53 71 80)

        Arrays.sort(myIntegers, 0, 4); // (12 32 45 71)26 80 53 33
        Arrays.sort(myIntegers, 4, 8); // 12 32 45 71(26 33 53 80)
        Arrays.sort(myIntegers); //(12 26 32 33 45 53 71 80)
        Arrays.sort(myIntegers, new MyComp()); //(80 71 53 45 33 32 26 12)
        System.out.print("myints contains : ");
        for (int i = 0; i < myints.length; i++) {
            System.out.print(myints[i] + ",");
        }

        System.out.print("\n myIntegers contains :");

        for (int i = 0; i < myIntegers.length; i++) {
            System.out.print(myIntegers[i] + ",");
        }
        System.out.println();
    }
}