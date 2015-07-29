public class RunPercolationStats {
    public static void main(String[] args) {
        double[] time = new double[10];
        int testCases = 7;
        int N = 2;
        int T = 10;
        int i;
        for (i = 0; i < testCases; i++) {
            Stopwatch s = new Stopwatch();
            PercolationStats p = new PercolationStats(N, T);
            System.out.println(N + " \t " + s.elapsedTime());
            N = N*2;
        }
    }
}
            
                                          