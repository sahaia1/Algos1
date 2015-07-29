/*--------------------------------------------------------
 *  Name - Aditya Sahai
 *  Date - 6th July 2015
 *  File name - PercolationStats.java
 *  Purpose - To perform a series of experiments on
 *    Percolation java class. This file will run T 
 *    test cases for an N*N grid on the Percolation
 *    class
 *  Usage - 
 *  Compilation - # javac PercolationStats.java
 *  Execution   - # java PercolationStats N T
 *     Example  - # java PercolationStats 20 100
 *
 *--------------------------------------------------------*/
public class PercolationStats {
    private int numExperiments; // number of test cases
    private int gridSize; // number of rows in the grid
    private double mean; // mean calculated over all test cases
    private double stdDev; 
    // standard deviation calculated over all test cases
    private double confHI, confLW; // confidence values over all test cases
    private double[] testCases; // fraction of open sites per test case.
    
    /*--------------------------------------------------------
     * PercolationStats constructor- Is initiated when a new
     * object is created on the PercolationStats class. 
     * It accepts two int arguments, namely, number of rows in
     * the grid (N) and the number of test cases to run (T).
     * It runs the test cases using a random number generator
     * on the Percolation class. 
     * Throws a IllegalArgumentException in case of invalid N or
     * T values.
     *--------------------------------------------------------*/ 
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Invalid input value."
                                                + "Please check!");
        }
        numExperiments = T;
        gridSize = N;
        testCases = new double[T];
        int i;
        for (i = 0; i < numExperiments; i++) {
            Percolation P = new Percolation(N);
            int random1, random2;
            int[] openSites = initialize();
            int opencount = 0;
            while (!P.percolates()) {
                int index;
                random1 = StdRandom.uniform(1, gridSize+1);
                random2 = StdRandom.uniform(1, gridSize+1);
                index = ijToX(random1, random2);
                if (openSites[index] == -1) {
                    openSites[index] = 0;
                    P.open(random1, random2);
                    opencount++;
                }
            }
            // System Percolates
            testCases[i] = opencount*1.0/(gridSize*gridSize);
        }
    }
    
    /*--------------------------------------------------------
     * Private method ijToX to convert two dimensional
     * coordinates into one dimensional array index. Accepts
     * two int values and returns one int value.
     *--------------------------------------------------------*/
    private int ijToX(int i, int j) {
        return gridSize*(i-1) + (j-1);
    }
    
    /*--------------------------------------------------------
     * Private method initialize to initialise an array where
     * all indices are -1 to keep count of blocked spots in the
     * grid. Accepts no values and returns an integer array.
     *--------------------------------------------------------*/
    private int[] initialize() {
        int dimensions = gridSize*gridSize;
        int[] array = new int[dimensions];
        int i;
        for (i = 0; i < dimensions; i++) {
            array[i] = -1;
        }
        return array;
    }
    
    /*--------------------------------------------------------
     * Public method to calculate the mean of a particular test
     * case. Accepts no value and returns a double value.
     *--------------------------------------------------------*/
    public double mean() {
        int i;
        double sum = 0.0;
        for (i = 0; i < numExperiments; i++) {
            sum = sum + testCases[i];
        }
        mean = (sum*1.0)/numExperiments;
        return mean;
    }
    
    /*--------------------------------------------------------
     * Public method stddev to calculate the standard deviation
     * of a particular test case. Accepts no values and returns
     * a double value.
     *--------------------------------------------------------*/
    public double stddev() {
        int i;
        double sum = 0.0;
        for (i = 0; i < numExperiments; i++) {
            double k = testCases[i]*1.0 - mean;
            sum += k*k;
        }
        double coVar = sum/(numExperiments -1);
        stdDev = Math.sqrt(coVar);
        return stdDev;
    }
    
    /*--------------------------------------------------------
     * Public method confidenceLo to calculate the low endpoint
     * of the 95% confidence interval. Accepts no value and
     * returns a double value.
     *--------------------------------------------------------*/
    public double confidenceLo() {
        double k = Math.sqrt(numExperiments*1.0);
        confLW = mean - (1.96*stdDev)/(k);
        return confLW;
    }
    
    /*--------------------------------------------------------
     * Public method confidenceHi to calculate the high endpoint
     * of the 95% confidence interval. Accepts no value and
     * returns a double value.
     *--------------------------------------------------------*/    
    public double confidenceHi() {
        double k = Math.sqrt(numExperiments*1.0);
        confHI = mean + (1.96*stdDev)/(k);
        return confHI;
    }
    
    /*--------------------------------------------------------
     * Public method main to parse command line arguments
     * and initiate a PercolationStats object. It also
     * calls the mean, stddev, confidenceHi and confidenceLo
     * methods to output respective calculated values of the
     * run over all the test cases.
     * Accepts two command line arguments N and T.
     * Prints the mean, standard deviation and the confidence
     * interval.
     *--------------------------------------------------------*/
    public static void main(String[] args) {
        int N, T; // number of rows and the number of test cases.
        N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);
        
        PercolationStats pStats = new PercolationStats(N, T);
        // Object of the class PercolationStats
        System.out.printf("mean \t\t\t= %f\n", pStats.mean());
        System.out.printf("stddev \t\t\t= %f\n", pStats.stddev());
        System.out.println("95% confidence interval \t= "
                               + pStats.confidenceLo() + ", "
                               + pStats.confidenceHi()); 
        // System.out.println("Time elapsed = " + watch.elapsedTime());
        
    }
    
}
