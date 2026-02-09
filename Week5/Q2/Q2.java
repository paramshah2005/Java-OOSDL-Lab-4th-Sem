package Week5.Q2;

import java.io.*;

public class Q2 {
    public static void main(String[] args) {

        FileReader fr = null;
        FileWriter fw = null;

        try {
            fr = new FileReader("Week5/Q2/input.txt");
            fw = new FileWriter("Week5/Q2/output.txt");

            int ch;
            System.out.println("File Contents:\n");

            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch); 
                fw.write(ch);         
            }

            System.out.println("\n\nFile copied successfully.");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            try {
                if (fr != null) fr.close();
                if (fw != null) fw.close();
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
}
