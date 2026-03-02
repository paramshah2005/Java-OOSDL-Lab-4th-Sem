package Week7.Q3;

class RoomChargeCalculator<T extends Number> {

    private T price;
    private T discount;

    public RoomChargeCalculator(T price, T discount) {
        this.price = price;
        this.discount = discount;
    }

    public double calculateTotal() {
        return price.doubleValue();
    }

    public double calculateDiscountedPrice() {
        return price.doubleValue() - discount.doubleValue();
    }
}

public class HotelDemo3 {
    public static void main(String[] args) {

        RoomChargeCalculator<Double> room = new RoomChargeCalculator<>(5000.0, 500.0);

        System.out.println("Total Price       : " + room.calculateTotal());
        System.out.println("Discounted Price  : " + room.calculateDiscountedPrice());
    }
}