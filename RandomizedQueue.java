/***********************************************************
  * Name - Aditya Sahai
  * Filename - RandomizedQueue.java
  * Date - 17th July 2015
  * 
  * Compilation - # javac RandomizedQueue.java
  * Execution - # java RandomizedQueue
  * 
  * Purpose - Implementing a Randomized Queue which supports
  * the addition of items and uniformally random removal of
  * items. It also supports an iterator which returns items
  * uniformaly randomly at each call to next once initialized
  * and the function sample which returns a random element
  * from the list.
 ***********************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N; // Number of items in the queue
    private Item[] rq; // randomized queue

 /**
  * Public constructor. Initializes the number of items in the list to
  * zero and creates the randomized queue array.
  */
    public RandomizedQueue() {
        // Constructor
        N = 0;
        rq = (Item[]) new Object[2];
    }
    
/**
 * Private method resize. Resizes the array when resizing of the array is
 * required. Typically, the when number of items exceeds the capacity of
 * the array and when the number of items are a quarted of the capacity.
 * In these cases the array is extenced and shrunk respectively.
 */
    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            copy[i] = rq[i];
        }
        rq = copy;
    }
    
/**
 * Public method isEmpty. Returns boolean weather the array is empty
 * or not.
 */
    public boolean isEmpty() {
        return N == 0;
    }

/**
 * Public method size. Returns the number of items in the list.
 */
    public int size() {
        return N;
    }

 /**
  * Public method enqueue. Adds an item to the array at the front.
  */
    public void enqueue(Item item) {
        if (N == rq.length)
            resize(2*rq.length);
        rq[N++] = item;
    }

/**
 * Public method dequeue. Removes a uniformaly 
 * random item from the list.
 */
    public Item dequeue() {
        StdRandom.shuffle(rq, 0, N-1);
        Item item = rq[--N];
        if (N == rq.length/4) 
            resize(rq.length/2);
        return item;
    }
/**
 * Returns an iterator to the array which iterates
 * randomly through the list.
 */
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

/**
 * Iterator helper class.
 */
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private int[] array = new int[N];
        
        /**
         * public array constructor. Creates an array with i to
         * N indices and shuffles it.
         */
        public ArrayIterator() {
            for (int k = 0; k < N; k++) {
                array[k] = k;
            }
            StdRandom.shuffle(array);
        }
        
        public boolean hasNext() { return i < N; }
        public void remove()    { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return rq[array[i++]];
        }
    }
    
/**
 * Public method sample. Returns a random item from the list.
 */
    public Item sample() {
        int i = StdRandom.uniform(0, N);
        Item rand = rq[i];
        return rand;
    }
    
/** 
 * Main method for unit testing.
 */
    public static void main(String[] args) {
//        RandomizedQueue<Integer> R = new RandomizedQueue<Integer>();
//        for (int i = 0; i < 10; i++) {
//            R.enqueue(i);
//        }
//        for (Integer k : R) {
//            StdOut.print(k + " ");
//        }
//        for (int j = 0; j < 6; j++) {
//            System.out.println(R.dequeue());
//        }
//        for (Integer k : R) {
//            StdOut.print(k + " ");
//        }
    }
}