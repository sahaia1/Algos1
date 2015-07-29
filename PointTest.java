public class PointTest {
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