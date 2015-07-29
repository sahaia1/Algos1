import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        int inputs = Integer.parseInt(StdIn.readLine());
        Point[] P = new Point[inputs];
        for (int i = 0; i < inputs; i++) {
            String points = StdIn.readLine();
            int point_x = Integer.parseInt(points.split(" ")[0]);
            int point_y = Integer.parseInt(points.split(" ")[1]);
            Point p = new Point(point_x, point_y);
            P[i] = p;
        }
        for (int i = 0; i < inputs; i++) {
            for (int j = i + 1; j < inputs; j++) {
                double slope = P[i].slopeTo(P[j]);
                for (int k = j + 1; k < inputs; k++) {
                    double slope2 = P[i].slopeTo(P[k]);
                    if (slope != slope2)
                        continue;
                    else{
                        for(int l = k + 1; l < inputs; l++) {
                            double slope3 = P[i].slopeTo(P[l]);
                            if (slope3 != slope)
                                continue;
                            else {
                                Point[] Ans = new Point[4];
                                Ans[0] = P[i];
                                Ans[1] = P[j];
                                Ans[2] = P[k];
                                Ans[3] = P[l];
                                Arrays.sort(Ans);
                                for (int g = 0; g < 4; g++) {
                                    System.out.print(Ans[g].toString());
                                    if (g < 3)
                                        System.out.print(" -> ");
                                }
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
    }
}
