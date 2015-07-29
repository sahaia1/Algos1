import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point p0, Point p1)
        {
            double s0 = slopeTo(p0);
            double s1 = slopeTo(p1);
            return Double.compare(s0, s1);
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        else if (this.y == that.y) return 0;
        else {
            if (this.x == that.x) return Double.POSITIVE_INFINITY;
            else return ((that.y - this.y)*1.0/(that.x - this.x));
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y > that.y)
            return 10;
        else if (this.y < that.y)
            return -10;
        else
            if (this.x > that.x)
                return 10;
            else if (this.x < that.x)
                return -10;
            else
                return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point[] P = new Point[4];
        P[0] = new Point(1234, 5678);
        P[1] = new Point(19000, 10000);
        P[2] = new Point(21000, 10000);
        P[3] = new Point(32000, 10000);
        
        System.out.println("Slope 1 - " + P[0].slopeTo(P[1]));
        System.out.println("Slope 1 - " + P[0].slopeTo(P[2]));
        System.out.println("Slope 1 - " + P[0].slopeTo(P[3]));
        System.out.println("Slope 1 - " + P[0].slopeTo(P[0]));
    }
}
