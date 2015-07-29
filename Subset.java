/***********************************************************
  * Name - Aditya Sahai
  * Filename - Subset.java
  * Date - 17th July 2015
  * 
  * Compilation - # javac Subset.java
  * Execution - # echo A B C D | java Subset x 
  * where x is a number, less than the number of strings entered.
  * 
  * Purpose - To implement a subset class which prints a randomly
  * selected subset of given string entries.
***********************************************************/

import java.util.NoSuchElementException;

public class Subset {
    /*
     * Main method.
     */
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> Q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
            Q.enqueue(StdIn.readString());
        int j = 0;
        if (k > Q.size())
            throw new NoSuchElementException("Underflow!");
        for (String s : Q) {
            if (j == k)
                break;
            StdOut.println(s);
            j++;
        }
    }
}