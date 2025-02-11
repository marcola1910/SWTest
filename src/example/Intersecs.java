import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class IntersecsSolution {

    static class Point {
        int x;
        int y;
    }

    static int direction(Point A,Point B,Point C){
        int dxAB = B.x - A.x;
        int dyAB = B.y - A.y;
        int dxAC = C.x - A.x;
        int dyAC = C.y - A.y;

        if (dxAB * dyAC < dyAB * dxAC) {
            return -1; //clockwise
        }
        else if (dxAB * dyAC > dyAB * dxAC) {
            return 1; //counter clockwise
        }
        else{
            if (B.x - A.x == 0 && B.y - A.y == 0) return 0;
            if ((dxAB * dxAC < 0) || (dyAB * dyAC < 0)) return -1; //A is in the  middle
            else if ((dxAB * dxAB + dyAB * dyAB) >= (dxAC * dxAC + dyAC * dyAC)) return  0; //C is in the middle
            else return 1; // B is in the middle
        }
    }

    static int cross_check(Point A, Point B, Point C, Point D) {
        int get_dir1 = direction(A, B, C);
        int get_dir2 = direction(A, B, D);
        int get_dir3 = direction(C, D, A);
        int get_dir4 = direction(C, D, B);

        if (get_dir1 * get_dir2 <= 0 && get_dir3 * get_dir4 <= 0) return 1;
        else return 0;
    }

    public static void main(String[] args) throws IOException {
        Point A = new Point();
        Point B = new Point();
        Point C = new Point();
        Point D = new Point();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        A.x = Integer.valueOf(line[0]);
        A.y = Integer.valueOf(line[1]);
        line = br.readLine().split(" ");
        B.x = Integer.valueOf(line[0]);
        B.y = Integer.valueOf(line[1]);
        line = br.readLine().split(" ");
        C.x = Integer.valueOf(line[0]);
        C.y = Integer.valueOf(line[1]);
        line = br.readLine().split(" ");
        D.x = Integer.valueOf(line[0]);
        D.y = Integer.valueOf(line[1]);

        System.out.println(cross_check(A, B, C, D));
    }

}
