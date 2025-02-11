import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Point{

    int x;
    int y;
}

class Solution {

    public static int ccw(Point A, Point B, Point C) {
        int value = (B.x - A.x) * (C.y - A.y) - (B.y - A.y) * (C.x - A.x);
        if (value == 0) return 0;
        else if (value > 0) return 1; //counter clock wise
        else return -1; //clockwise
    }


    public static void main(String[] args) throws IOException {

        Point A = new Point();
        Point B = new Point();
        Point C = new Point();
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

        System.out.println(ccw(A, B, C));

    }

}
