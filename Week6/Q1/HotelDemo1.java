import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class HotelDemo1 {
    private static final String FILE_NAME = "hotel_rooms.dat";
    private static final int RECORD_SIZE = 53; // 4 + 40 + 8 + 1

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Hotel Random Access Menu ---");
            System.out.println("1. Add/Update Room Record");
            System.out.println("2. Display Specific Room");
            System.out.println("3. Update Booking Status");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        addRoom(scanner);
                        break;
                    case 2:
                        displayRoom(scanner);
                        break;
                    case 3:
                        updateStatus(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (IOException e) {
                System.out.println("File Error: " + e.getMessage());
            }
        }
    }

    private static void addRoom(Scanner scanner) throws IOException {
        System.out.print("Enter Room Number (e.g., 1, 2, 3...): ");
        int roomNo = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Room Type (max 20 chars): ");
        String type = scanner.nextLine();
        System.out.print("Enter Price per Night: ");
        double price = scanner.nextDouble();
        System.out.print("Is it Booked? (true/false): ");
        boolean status = scanner.nextBoolean();

        try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "rw")) {
            raf.seek((roomNo - 1) * RECORD_SIZE);
            raf.writeInt(roomNo);
            writeFixedString(raf, type, 20);
            raf.writeDouble(price);
            raf.writeBoolean(status);
            System.out.println("Room saved successfully.");
        }
    }

    private static void displayRoom(Scanner scanner) throws IOException {
        System.out.print("Enter Room Number to view: ");
        int roomNo = scanner.nextInt();

        try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "r")) {
            long position = (roomNo - 1) * RECORD_SIZE;
            if (position >= raf.length()) {
                System.out.println("Room record does not exist.");
                return;
            }

            raf.seek(position);
            int id = raf.readInt();
            if (id == 0) { 
                System.out.println("Room record is empty.");
                return;
            }

            String type = readFixedString(raf, 20);
            double price = raf.readDouble();
            boolean status = raf.readBoolean();

            System.out.println("\nRoom Details:");
            System.out.println("Number: " + id);
            System.out.println("Type: " + type);
            System.out.println("Price: " + price);
            System.out.println("Booked: " + status);
        }
    }

    private static void updateStatus(Scanner scanner) throws IOException {
        System.out.print("Enter Room Number to update: ");
        int roomNo = scanner.nextInt();
        System.out.print("Enter new status (true for booked, false for vacant): ");
        boolean newStatus = scanner.nextBoolean();

        try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "rw")) {
            long position = (roomNo - 1) * RECORD_SIZE;
            if (position >= raf.length()) {
                System.out.println("Room record does not exist.");
                return;
            }
            raf.seek(position + 52);
            raf.writeBoolean(newStatus);
            System.out.println("Status updated successfully.");
        }
    }

    private static void writeFixedString(RandomAccessFile raf, String str, int length) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        sb.setLength(length); 
        raf.writeChars(sb.toString());
    }

    private static String readFixedString(RandomAccessFile raf, int length) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();
    }
}