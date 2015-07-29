/***********************************************************
  * Name - Aditya Sahai
  * Filename - Deque.java
  * Date - 17th July 2015
  * Purpose - To model a double ended queue
  * which supports adding and removing items
  * from both ends of the queue.
  * 
  * Compilation and Execution
  * # javac Deque.java
  * # java Deque
  * 
  * Summary
  * A Deque (pronounced "deck") implemented using a
  * doubly linked list. The list uses sentinal nodes to keep
  * track of the head and the tail of the list. Operations
  * supported are add to the beginning and the end of the list
  * and remove from the beginning and end of the list. Also
  * supports an iterator class to parse the list from head
  * to tail.
  * 
  *********************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private int N; // Number of items
    private Node<Item> head, tail; // Head and Tail sentinal nodes

    // Helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next; // Pointer to the next item in list
        private Node<Item> prev; // Pointer to the prev item in list
    }
    /*--------------------------------------------------------
     *  Deque Class constructor. Initialized the head and tail
     *  objects of the type of the helper class. They keep track
     *  of the head and the tail of the double ended queue. It also
     *  sets the initial positions of the head and tail such that
     *  the head's previous node is tail and tail's next node is
     *  head. It sets the number of items in the list to zero.
     *--------------------------------------------------------*/

    public Deque() {
        head = new Node<Item>();
        tail = new Node<Item>();
        head.prev = tail;
        tail.next = head;
        N = 0;
    }

    /*--------------------------------------------------------
     *  Public method isEmpty. Returns boolean True or False
     *  if the Deque is empty or not.
     *--------------------------------------------------------*/

    public boolean isEmpty() {
        return N == 0;
    }

    /*--------------------------------------------------------
     *  Public method size. Returns the number of items in the
     *  list.
     *--------------------------------------------------------*/
    public int size() {
        return N;
    }

    /*--------------------------------------------------------
     *  Public method addFirst. Adds an item to the front of the
     *  Deque.
     *--------------------------------------------------------*/

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("Cannot add null"
                                                        + "element");
        Node<Item> last = head.prev;
        Node<Item> x = new Node<Item>();
        x.next = head;
        x.prev = last;
        x.item = item;
        last.next = x;
        head.prev = x;
        N++;
    }

    /*--------------------------------------------------------
     *  Public method addLast. Adds an item to the end of the
     *  Deque.
     *--------------------------------------------------------*/
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("Cannot add null"
                                                        + "element");
        Node<Item> last = tail.next;
        Node<Item> x = new Node<Item>();
        x.item = item;
        x.next = last;
        x.prev = tail;
        tail.next = x;
        last.prev = x;    
        N++;
    }

    /*--------------------------------------------------------
     *  Public method removeFirst. Removes an Item from the
     *  front of the Deque.
     *--------------------------------------------------------*/
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Node<Item> x = head.prev;
        head.prev = x.prev;
        x.prev.next = head;
        N--;
        return x.item;
    }

    /*--------------------------------------------------------
     *  Public method removeLast. Removes an item from the end
     *  of the Deque.
     *--------------------------------------------------------*/
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Node<Item> x = tail.next;
        tail.next = x.next;
        x.next.prev = tail;
        N--;
        return x.item;
    }

    /*--------------------------------------------------------
     * Returns an iterator to this Deque which iterates from
     * front to the end.
     *--------------------------------------------------------*/
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(head);
    }

    /*--------------------------------------------------------
     * Iterator class. Does not implement remove.
     *--------------------------------------------------------*/
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first.prev;
        }

        public boolean hasNext() { return current != tail; }
        public void remove() { 
            throw new UnsupportedOperationException("No remove method!");
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No items left!");
            Item item = current.item;
            current = current.prev;
            return item;
        }
    }

    // Main method of unit testing.
    public static void main(String[] args) {
        Deque<String> s = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))  s.addLast(item);
            else if (!s.isEmpty()) StdOut.print(s.removeFirst() + " ");
        }
        Iterator<String> i = s.iterator();
        while (i.hasNext()) {
            StdOut.print(i.next() + " ");
        }  
    }
}