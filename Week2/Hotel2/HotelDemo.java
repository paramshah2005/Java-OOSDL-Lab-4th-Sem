package Week2.Hotel2;

enum RoomType {

    STANDARD(2000),
    DELUXE(3500),
    SUITE(6000);

    private double baseTariff;

    RoomType(double baseTariff) {
        this.baseTariff = baseTariff;
    }

    public double getBaseTariff() {
        return baseTariff;
    }

    public double calculateTotalCost(int days) {
        return baseTariff * days;
    }
}

public class HotelDemo {

    public static void main(String[] args) {

        RoomType selectedRoom = RoomType.DELUXE;
        int daysStayed = 4;

        double totalCost = selectedRoom.calculateTotalCost(daysStayed);

        System.out.println("--- Hotel Room Tariff Details ---");
        System.out.println("Room Type       : " + selectedRoom);
        System.out.println("Base Tariff/day : " + selectedRoom.getBaseTariff());
        System.out.println("Days Stayed     : " + daysStayed);
        System.out.println("Total Cost      : " + totalCost);
    }
}
