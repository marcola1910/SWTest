class QuickSort {
    // [][0] : index , [][1] : value 5.
    static void quicksort(int d[][], int s, int e) {
        if (e - s < 2) return;
        int tmp[] = new int[2];

        // set pivot 10
        int p = (s + e) / 2;
        tmp = d[p];
        d[p] = d[s];
        d[s] = tmp;

        p = s;
        for (int i = s + 1; i < e; i++) {
            if (d[s][1] > d[i][1]) {
                p++;
                tmp = d[p];
                d[p] = d[i];
                d[i] = tmp;
            }
        }

        tmp = d[s];
        d[s] = d[p];
        d[p] = tmp;

        quicksort(d, s, p);
        quicksort(d, p + 1, e);
    }
    public static void main(String args[]) {
        int arr[] = { 5, 26, 0, -1, 5, 2, 7, 4, 26, 13 };
        int myarr[][] = new int[10][2];
        for (int i = 0; i < 10; i++) {
            myarr[i][0] = i;
            myarr[i][1] = arr[i];
        }
        System.out.print("Before :");
        for (int i = 0; i < 10; i++) {
            System.out.print(" [" + myarr[i][0] + "]" + myarr[i][1]);
        }
        System.out.println();

        quicksort(myarr, 0, 10);

        System.out.print("After :");
        for (int i = 0; i < 10; i++) {
            System.out.print(" [" + myarr[i][0] + "]" + myarr[i][1]);
        }
        System.out.println();
    }
}