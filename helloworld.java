public class helloworld {
    private static int a = 44;
    private WeightedQuickUnionUF element = new WeightedQuickUnionUF(45);
    
    private static int printA() {
        return a*4;
    }
    
    public static void printingPress() {
        System.out.println("a = " + printA());
    }
    
    public static void main(String[] args) {
    System.out.println("Hello world");
    
//    System.out.println("variable 1 - " + args[0] + " Varialbe 2 - " +  args[1]);
//    System.out.println("Interger - " + (Integer.parseInt(args[2]) + 2));
//    printingPress();
    //throw new IllegalArgumentException();
    int i;
    for (i = 1; i <= 3; i++) {
        System.out.println("i = " + i);
        int n = StdRandom.uniform(1, 3);
        System.out.println("n = " + n);
        System.out.println(1/13);
    }
    }
}