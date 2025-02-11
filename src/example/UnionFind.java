
class UnionFind {
//max number of elements

    static final int _M_size_ = 1000;
    //starts with 1, index 0 is not used.
    static int Set[] = new int[_M_size_ + 1];

    static int group(int n) {
        if (Set[n] == 0) return n;
        return Set[n] = group(Set[n]); // path compression


    }

    static void join ( int a, int b){
        int A = group(a), B = group(b);
        if (A != B) Set[A] = B;
    }
}