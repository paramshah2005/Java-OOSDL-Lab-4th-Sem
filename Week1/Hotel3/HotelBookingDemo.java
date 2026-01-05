package Hotel3;

interface Amenities {
    void provideWifi();
    void provideBreakfast();
}

abstract class Room {
    protected int roomNumber;
    protected double basePrice;

    public Room(int roomNumber, double basePrice) {
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
    }

    public abstract double calculateTariff();

    public void displayRoomDetails() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Base Price: " + basePrice);
    }
}

class StandardRoom extends Room implements Amenities {
    public StandardRoom(int roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    public double calculateTariff() {
        return basePrice * 1.1;
    }

    public void provideWifi() {
        System.out.println("Standard Room: Free WiFi is not available.");
    }

    public void provideBreakfast() {
        System.out.println("Standard Room: Breakfast is not included.");
    }
}

class LuxuryRoom extends Room implements Amenities {
    public LuxuryRoom(int roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    public double calculateTariff() {
        return basePrice * 1.25;
    }

    public void provideWifi() {
        System.out.println("Luxury Room: Free high-speed WiFi is available.");
    }

    public void provideBreakfast() {
        System.out.println("Luxury Room: Complimentary breakfast is included.");
    }
}

public class HotelBookingDemo {
    public static void main(String[] args) {
        StandardRoom standardRoom = new StandardRoom(101, 5000);
        LuxuryRoom luxuryRoom = new LuxuryRoom(201, 10000);

        standardRoom.displayRoomDetails();
        System.out.println("Total Tariff: " + standardRoom.calculateTariff());
        standardRoom.provideWifi();
        standardRoom.provideBreakfast();

        System.out.println();

        luxuryRoom.displayRoomDetails();
        System.out.println("Total Tariff: " + luxuryRoom.calculateTariff());
        luxuryRoom.provideWifi();
        luxuryRoom.provideBreakfast();
    }
}
