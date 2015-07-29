public class ArrayQueue {
 private int head, tail;
 private String[] q;
 private int numItems;
 
 
 public ArrayQueue() {
  head = 0;
  tail = 0;
  numItems = 0;
  q = new String[1];
  q[0] = null;
 }

 private boolean isEmpty() {
     return numItems == 0;
 }
 private void resize(int size) {
  String[] copy = new String[size];
  int i, j;
  i = head;
  j = 0;
  do {
   copy[j++] = q[i];
   i = (i+1)%q.length;
  } while(i != tail);
  head = 0;
  tail = j;
  q = copy;
// System.out.println("resize called, new capacity = " + size);
// System.out.println("head = " + head + " tail = " + tail);
 }

 public void enqueue(String item) {
  q[tail] = item;
  tail = (tail+1)%q.length;
  if (tail == head) {
   // queue is full. we need to resize
   resize(2*q.length);
  }
  numItems++;
 }

 public String dequeue() {
  if (isEmpty())
      return "Queue is empty";
  String out = q[head];
  q[head] = null;
  head = (head+1)%q.length;
  numItems--;
  if ((numItems != 0) && (numItems == q.length/4)) {
   resize(q.length/2);
  }
  return out;
 }

 public void printQ() {
  int i = head;
  do {
   System.out.println(q[i]);
   i = (i+1)%q.length;
  } while (i != tail);
 }

 public static void main(String[] args) {
  ArrayQueue queue = new ArrayQueue();
  while(!StdIn.isEmpty())
  {
   String s = StdIn.readString();
   if (s.equals("-")) {
    StdOut.print(queue.dequeue());
    queue.printQ();
   }
   else if (s.equals("end")) {
    queue.printQ();
    break;
   }
   else {
    queue.enqueue(s);
    queue.printQ();
   }
  }
 }
}
 
 