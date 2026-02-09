package Week5.Q1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Q1 {
    public static void main(String[] args) {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream("Week5/Q1/input.txt");
            fos = new FileOutputStream("Week5/Q1/output.txt");

            int data;
            System.out.println("File Contents:\n");

            while ((data = fis.read()) != -1) {
                System.out.print((char) data); 
                fos.write(data);               
            }

            System.out.println("\n\nFile copied successfully.");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
}
