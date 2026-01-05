class Room {
    int roomNumber;
    String roomType;
    double basePrice;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = 100;
    }

    public Room(int roomNumber, String roomType, double basePrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = basePrice;
    }

    public Room() {
        this.roomNumber = 101;
        this.roomType = "Standard";
        this.basePrice = 100;
    }

    public void displayDetails() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Base Price: " + basePrice);
    }
}

class DeluxeRoom extends Room {
    boolean freeWiFi;
    boolean complimentaryBreakfast;

    public DeluxeRoom(int roomNumber, String roomType, double basePrice, boolean freeWiFi,
            boolean complimentaryBreakfast) {
        super(roomNumber, roomType, basePrice);
        this.freeWiFi = freeWiFi;
        this.complimentaryBreakfast = complimentaryBreakfast;
    }

    public DeluxeRoom(int roomNumber, String roomType, boolean freeWiFi, boolean complimentaryBreakfast) {
        super(roomNumber, roomType);
        this.freeWiFi = freeWiFi;
        this.complimentaryBreakfast = complimentaryBreakfast;
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Free Wi-Fi: " + (freeWiFi ? "Yes" : "No"));
        System.out.println("Complimentary Breakfast: " + (complimentaryBreakfast ? "Yes" : "No"));
    }
}

public class RoomDemo {
    public static void main(String[] args) {
        Room room1 = new Room(101, "Standard");
        room1.displayDetails();

        Room room2 = new Room(102, "Suite", 250.00);
        room2.displayDetails();

        DeluxeRoom deluxeRoom1 = new DeluxeRoom(201, "Deluxe Suite", 500.00, true, true);
        deluxeRoom1.displayDetails();

        DeluxeRoom deluxeRoom2 = new DeluxeRoom(202, "Luxury", true, false);
        deluxeRoom2.displayDetails();
    }
}
