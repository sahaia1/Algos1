import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class fileread {
 public static void main(String[] args) {
     try {
         BufferedReader br = new BufferedReader(new FileReader(args[0]));
         String line;
         while ((line = br.readLine()) != null) {
             System.out.println(line);
         }
     }catch (FileNotFoundException e) {
             System.out.println("File not found");
         }
     catch (IOException e) {
         System.out.println(e);
     }
 }
}