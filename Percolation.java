/*--------------------------------------------------------
 *  Name - Aditya Sahai
 *  Date - 6th July 2015
 *  File name - Percolation.java
 *  Purpose - To model a percolation system. Creates a
 *      grid of the specified number of rows will all
 *      blocked initial sites.
 *  Usage - 
 *  Compilation - # javac Percolation.java
 *
 *--------------------------------------------------------*/
public class Percolation {
    private int gridSize = 0; // int to store the number of rows in the gird
    private int gridDimension = 0; // int to store the size of the grid
    private WeightedQuickUnionUF elements; // WeightedQuickUnionUF object
    private int[] array; // array to store which sites are open
    private int openCounter; // to track number of open sites
    
    /*--------------------------------------------------------
     * Percolation class constructor. Accepts the number of
     * rows in the grid (N). Creates a grid of the size NxN.
     * Creates an object of the WeighedQuickUnionFindUF class.
     * Throws an IllegalArgumentException if N is less than 0. 
     *--------------------------------------------------------*/
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Value of N is less"
                                                + " than or equal to zero");
        }
        gridSize = N; // number of rows in the grid.
        gridDimension = N*N; // total size of the grid.
        elements = new WeightedQuickUnionUF(gridDimension);
        // WeightedQuickUnionUF object.
        openCounter = 0; // Counter for number of open sites.
        initialize();
    }
    /*--------------------------------------------------------
     * Private method isValidIJ which checks if a set of passed
     * coordinates are valid or not given the grid. Returns
     * boolean and accepts two int coordinates.
     *--------------------------------------------------------*/
    private boolean isValidIJ(int i, int j) {
        if ((i <= 0 || i > gridSize) || (j <= 0 || j > gridSize)) {
            return false;
        }
        return true;
    }
    
    /*--------------------------------------------------------
     * Private method ijToX to convert a given site of i, j
     * coordinates into a single dimensional x to access the
     * grid. Accepts two int coordinates and returns an int
     * array index.
     *--------------------------------------------------------*/
    private int ijToX(int i, int j) {
        return gridSize*(i-1) + (j-1);
    }
    
    /*--------------------------------------------------------
     * Private method initialize to initialise an array where
     * all indices are -1 to keep count of blocked spots in the
     * grid. Accepts no values and returns no values.
     *--------------------------------------------------------*/
    private void initialize() {
        int i;
        int size = gridDimension;
        array = new int[size];
        for (i = 0; i < size; i++) {
            array[i] = 0;
        }
    }
    
    /*--------------------------------------------------------
     * Public method open to open a site in the gird. Accepts
     * two int coordinates in the grid and returns no values.
     * Throws an IndexOutOfBoundsException exception if the 
     * passed coordinates are not proper.
     *--------------------------------------------------------*/
    public void open(int i, int j) {
        if (!isValidIJ(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        int n = ijToX(i, j);
        if (array[n] == 0) {
            array[n] = 1;
            openCounter++;
// Since the site is now open we need to connect it to
// open adjacent sites.
            // Left
            if (isValidIJ(i-1, j) && isOpen(i-1, j)) {
                    elements.union(ijToX(i, j), ijToX(i-1, j));
            }
            
            // Right
            if (isValidIJ(i+1, j) && isOpen(i+1, j)) {
                    elements.union(ijToX(i, j), ijToX(i+1, j));
            }

            // Up
            if (isValidIJ(i, j-1) && isOpen(i, j-1)) {
                    elements.union(ijToX(i, j), ijToX(i, j-1));
            }

            // Down
            if (isValidIJ(i, j+1) && isOpen(i, j+1)) {
                    elements.union(ijToX(i, j), ijToX(i, j+1));
            }
        }
    }
    /*--------------------------------------------------------
     * Public method isOpen to check if a given site is open or
     * not. Accepts two int coordinates and returns a boolean
     * value.
     * Throws an IndexOutOfBoundsException exception if the
     * passed coordinates are not proper.
     *--------------------------------------------------------*/
    public boolean isOpen(int i, int j) {
        if (!isValidIJ(i, j)) {
            throw new IndexOutOfBoundsException("index out of bounds "
                                                 + i + " " + j);
        }
        int n = ijToX(i, j);
        if (array[n] == 0) {
            return false;
        }
        return true;
    }
    
    /*--------------------------------------------------------
     * Public method isFull to check if a given site is full
     * or not. Accepts two int coordinates and returns a boolean
     * value.
     * Throws an IndexOutOfBoundsException exception if the
     * passed coordinates are not proper.
     *--------------------------------------------------------*/
    public boolean isFull(int i, int j) {
        if (!isValidIJ(i, j)) {
            throw new IndexOutOfBoundsException();
        }
// Check if the said element is connected to an element in the first row
        int p = 1;
        int q;
        for (q = 1; q <= gridSize; q++) {
            if (isOpen(i, j) && isOpen(p, q)) {
                int element1 = ijToX(i, j);
                int element2 = ijToX(p, q);
                if (elements.connected(element1, element2)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /*--------------------------------------------------------
     * Public method percolates to check if the given system
     * percolates or not. Accepts no value and returns a
     * boolean value.
     *--------------------------------------------------------*/
    public boolean percolates() {
// Check if any open element in the bottom row is Full.
        int i = gridSize;
        int j;
        for (j = 1; j <= gridSize; j++) {
            if (isFull(i, j)) {
                return true;
            }
        }
        return false;
    }
}