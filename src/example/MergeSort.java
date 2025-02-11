class MergeSort {
    static final int _M_size_ = 1000000; // max data size
    // [][ index [][ value
    static int d_tmp[][] = new int[_M_size_][2];

    static void mergesort(int d[][], int s, int e) {
        if (e - s < 2) return;

        //part
        int m = (s + e) / 2;
        mergesort(d, s, m);
        mergesort(d, m, e);

        //merge
        int i;
        int i1 = s, i2 = m;
        for (i = s; i < e; i++) {
            d_tmp[i][0] = d[i][0];
            d_tmp[i][1] = d[i][1];

        }
        i = s;
        while (i1 < m && i2 < e) {
            if (d_tmp[i1][1] > d_tmp[i2][1]) {
                d[i][0] = d_tmp[i2][0];
                d[i][1] = d_tmp[i2][1];
                i++;
                i2++;
            } else {
                d[i][0] = d_tmp[i1][0];
                d[i][1] = d_tmp[i1][1];
                i++;
                i1++;
            }
            while (i1 < m) {
                d[i][0] = d_tmp[i1][0];
                d[i][1] = d_tmp[i1][1];
                i++;
                i1++;
            }

            while (i2 < e) {
                d[i][0] = d_tmp[i2][0];
                d[i][1] = d_tmp[i2][1];
                i++;
                i2++;
            }
        }
    }

    public static void main(String args[]) {
        int arr[] = {5, 26, 0, 1, 5, 2, 7, 4, 26, 13}; //length 10
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


        mergesort(myarr, 0, 10);
        System.out.print("After :");
        for (int i = 0; i < 10; i++) {
            System.out.print("[ " + myarr[i][0] + "]" + myarr[i][1]);
        }

        System.out.println();
    }
}