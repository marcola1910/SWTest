import java.util.Scanner;

public class BinaryTree1 {

    static int n,tree[];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        tree = new int[n+1];

        for (int i = 0; i < n; i++) {
            tree[i] = sc.nextInt();
        }

        inorder(1);
        sc.close();
    }

    private static void inorder(int i) {
        if(i*2<=n){
            inorder(i*2);
        }

        System.out.print(tree[i]+" ");

        if(i*2+1<=n){
            inorder(i*2+1);
        }

    }
}