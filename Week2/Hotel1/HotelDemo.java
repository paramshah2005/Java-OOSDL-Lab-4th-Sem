package Week2.Hotel1;

public class HotelDemo {
    public static void main(String[] args) {

        int roomTariff = 2500;
        int days = 7;
        double serviceCharges = 1000.1;

        Integer tariffWrapper = roomTariff;
        Double serviceChargeWrapper = serviceCharges;
        Integer daysWrapper = days;

        double roomCharge = tariffWrapper.intValue() * daysWrapper.intValue();
        double totalBill = roomCharge + serviceChargeWrapper.doubleValue();

        System.out.println("------Hotel Bill------");
        System.out.println("Room Tariff: " + roomTariff);
        System.out.println("Number of Days: " + days);
        System.out.println("Service Charge: " + serviceCharges);
        System.out.println("Total Bill: " + totalBill);

    }
}
