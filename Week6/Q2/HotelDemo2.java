package Q2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    
    int roomNumber;
    String roomType;
    double pricePerNight;
    boolean bookingStatus;
    String guestName;

    public Room(int roomNumber, String roomType, double pricePerNight, boolean bookingStatus, String guestName) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.bookingStatus = bookingStatus;
        this.guestName = guestName;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + roomType + "] | Price: " + pricePerNight + 
               " | Booked: " + bookingStatus + (bookingStatus ? " | Guest: " + guestName : "");
    }
}

public class HotelDemo2 {
    private static final String FILE_NAME = "serialized_rooms.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Room> rooms = loadRooms();

        while (true) {
            System.out.println("\n--- Serialization Hotel Menu ---");
            System.out.println("1. Add Room");
            System.out.println("2. Display All Rooms");
            System.out.println("3. Search by Room Number");
            System.out.println("4. Update Booking Status");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Room Number: ");
                    int rNo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Room Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Price per Night: ");
                    double price = scanner.nextDouble();
                    
                    rooms.add(new Room(rNo, type, price, false, "None"));
                    saveRooms(rooms);
                    System.out.println("Room added and serialized!");
                    break;

                case 2:
                    System.out.println("\nAll Rooms:");
                    for (Room r : rooms) System.out.println(r);
                    break;

                case 3:
                    System.out.print("Enter Room Number to search: ");
                    int searchNo = scanner.nextInt();
                    rooms.stream()
                         .filter(r -> r.roomNumber == searchNo)
                         .findFirst()
                         .ifPresentOrElse(
                             r -> System.out.println("Found: " + r),
                             () -> System.out.println("Room not found.")
                         );
                    break;

                case 4:
                    System.out.print("Enter Room Number to update: ");
                    int updateNo = scanner.nextInt();
                    for (Room r : rooms) {
                        if (r.roomNumber == updateNo) {
                            System.out.print("Is it Booked? (true/false): ");
                            r.bookingStatus = scanner.nextBoolean();
                            scanner.nextLine(); // Consume newline
                            if (r.bookingStatus) {
                                System.out.print("Enter Guest Name: ");
                                r.guestName = scanner.nextLine();
                            } else {
                                r.guestName = "None";
                            }
                            saveRooms(rooms);
                            System.out.println("Booking status updated and re-serialized!");
                            break;
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
            }
        }
    }

    // Deserialization
    @SuppressWarnings("unchecked")
    private static List<Room> loadRooms() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Room>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading rooms: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Serialization
    private static void saveRooms(List<Room> rooms) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(rooms);
        } catch (IOException e) {
            System.out.println("Error saving rooms: " + e.getMessage());
        }
    }
}