public class Test {

   public static void main(String args[]) {
      String str1 = "Strings are immutable";
   String str2 = "Strings are immutable";
      String str3 = "Integers are not immutable";

      int result = str1.compareTo( str2 );
      System.out.println(result);
   
      result = str2.compareTo( str3 );
      System.out.println(result);
  
      result = str3.compareTo( str1 );
      System.out.println(result);
      System.out.println(Double.NEGATIVE_INFINITY);
      System.out.println(Double.compare(Double.NEGATIVE_INFINITY, 0));
      String s = "my name is aditya";
      System.out.println(s.split(" ")[1]);
   }
}