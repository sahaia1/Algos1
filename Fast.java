import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Fast {
    public static void main(String[] args) {
        int inputs = 0;
        Point[] P = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            inputs = Integer.parseInt(br.readLine());
            P = new Point[inputs];
            for (int i = 0; i < inputs; i++) {
                String points = br.readLine();
                int point_x = Integer.parseInt(points.split(" ")[0]);
                int point_y = Integer.parseInt(points.split(" ")[1]);
                P[i] = new Point(point_x, point_y);
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println(e);
        }
        
        for (int k = 0; k < inputs; k++) {
            Point[] Q = Arrays.copyOf(P, P.length);
            Arrays.sort(Q, Q[k].SLOPE_ORDER); // Sort the array considering that P[0] is the origin
            System.out.print(Q[0]);
            Point x = new Point(10000, 0);
            for (Point y: Q)
                System.out.println(y + "-" + x.slopeTo(y));
            System.out.println();
            
            int j = 1;
            for (int i = 1; i < inputs; i = i + 1) {
                double slope1 = Q[0].slopeTo(Q[i]);
                String print_string = Q[0] + " -> " + Q[i] + " -> ";
                for (j = i + 1; j < inputs; j++) {
                    double slope = Q[0].slopeTo(Q[j]);
                    if (slope == slope1) {
                        System.out.print(print_string + Q[j]);
                        print_string = " -> ";
                    }
                }                    
            }
            System.out.println();
        }
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.point(10000, 0);
//        StdDraw.point(7000, 3000);
//        StdDraw.point(3000, 7000);
//        StdDraw.point(0, 10000);
    }
}
