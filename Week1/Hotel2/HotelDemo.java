class Room {
    private int roomNumber;
    private double baseTariff;

    public Room(int roomNumber, double baseTariff) {
        this.roomNumber = roomNumber;
        this.baseTariff = baseTariff;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getBaseTariff() {
        return baseTariff;
    }

    public double calculateTariff() {
        return baseTariff;
    }
}

class StandardRoom extends Room {
    private boolean airConditioning;

    public StandardRoom(int roomNumber, double baseTariff, boolean airConditioning) {
        super(roomNumber, baseTariff);
        this.airConditioning = airConditioning;
    }

    @Override
    public double calculateTariff() {
        double tariff = getBaseTariff();
        if (airConditioning) {
            tariff += 20.0; 
        }
        return tariff;
    }

    public boolean hasAirConditioning() {
        return airConditioning;
    }
}

class LuxuryRoom extends Room {
    private boolean hasPremiumServices;

    public LuxuryRoom(int roomNumber, double baseTariff, boolean hasPremiumServices) {
        super(roomNumber, baseTariff);
        this.hasPremiumServices = hasPremiumServices;
    }

    @Override
    public double calculateTariff() {
        double tariff = getBaseTariff();
        if (hasPremiumServices) {
            tariff += 50.0; 
        }
        return tariff;
    }

    public boolean hasPremiumServices() {
        return hasPremiumServices;
    }
}

public class HotelDemo {
    public static void main(String[] args) {
        Room room1 = new StandardRoom(101, 100.0, true);  // StandardRoom with air conditioning
        Room room2 = new LuxuryRoom(102, 200.0, true);    // LuxuryRoom with premium services

        System.out.println("Room Number: " + room1.getRoomNumber() + ", Total Tariff: " + room1.calculateTariff());
        System.out.println("Room Number: " + room2.getRoomNumber() + ", Total Tariff: " + room2.calculateTariff());
    }
}
