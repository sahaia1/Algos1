public class Josephus {
 int[] a;
 int head;
 int M;
 int numPeeps;

 public Josephus(int N, int step) {
  a = new int[N];
  head = 0;
  M = step;
  numPeeps = N;
  for (int i = 0; i < N; i++) {
   a[i] = i;
  }
 }

 public int kill() {
  if (numPeeps == 0) {
   return -1;
  }
  int i = 0;
  while (i < M) {
   head = (head+1)%a.length;
   if (a[head] != -1)
    i++;
  }
  int killed = a[head];
  numPeeps--;
  a[head] = -1;
  return killed;
 }

 public int peopleLeft() {
  return numPeeps;
 }

 public static void main(String[] args) {
  int N, M;
  N = Integer.valueOf(args[1]);
  M = Integer.valueOf(args[0]);
  Josephus J = new Josephus(N, M);
  while(J.peopleLeft() > 1) {
   System.out.print(J.kill() + " ");
  }
  System.out.println();
  System.out.println("Josephus should sit at - " + J.kill());
 }
}