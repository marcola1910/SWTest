import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

 class GrahamScanSolution {
    static int MAX = 8;

    static class Point {

        public final Comparator<Point> POLAR_ORDER = new PolarOrder();
        int x;
        int y;

        private class PolarOrder implements Comparator<Point> {
            @Override
            public int compare(Point o1, Point o2) {
                int o = ccw(Point.this, o1, o2);
                if (o == 0)
                    return (dist(Point.this, o2) >= dist(Point.this, o1)) ? -1 : 1;
                return o;
            }
        }
    }

    static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    static int ccw(Point A, Point B, Point C) {
        int value = (B.x - A.x) * (C.y - A.y) - (B.y - A.y) * (C.x - A.x);

        if (value == 0)
            return 0;
        else if (value > 0)
            return -1; // counter clock wise
        else
            return 1; // clock wise
    }

    static void swap(Point a, Point b) {

        Point temp = new Point();
        temp = a;
        a = b;
        b = temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Point[] points = new Point[MAX + 1];

        for (int i = 0; i <= MAX; i++) {
            points[i] = new Point();
        }

        for (int i = 0; i < MAX; i++) {
            String[] line = br.readLine().split(" ");
            points[i].x = Integer.parseInt(line[0]);
            points[i].y = Integer.parseInt(line[1]);
        }

        int min_index = 0;
        for (int i = 1; i < MAX; i++) {
            if (points[i].y < points[min_index].y) min_index = i;
                else if (points[i].y == points[min_index].y) {
                if (points[i].x < points[min_index].x)min_index = i;
            }
        }

        Point temp = new Point();
        temp = points[min_index];
        points[min_index] = points[0];
        points[0] = temp;

        Arrays.sort(points, 1 , MAX, points[0].POLAR_ORDER);

        Point[] newPoints = new Point[MAX+ 1];
        newPoints[0] = points[MAX - 1];

        for (int i = 1; i < MAX+ 1; i++) {
            newPoints[i] = new Point();
            newPoints[i] = points[i - 1];
        }
        int M = 1;

        for (int i = 2; i <= MAX ; i++) {
            while (ccw(newPoints[M - 1], newPoints[M], newPoints[i]) >= 0) { // clockwise
                if (M > 1) {
                    M -= 1;
                    continue;
                } else if (i == MAX) break;
                else i += 1;
            }
            M += 1;

            temp = new Point();
            temp = newPoints[M];
            newPoints[M] = newPoints[i];
            newPoints[i] = temp;
        }

        for( int i = 1; i <=M ; i++) {
            System.out.println(newPoints[i].x + " " + newPoints[i].y);

        }
    }
}