package Week7.Q5;

class Pair<T, U> {

    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public void displayBooking() {
        System.out.println("Room Number: " + first);
        System.out.println("Guest Name : " + second);
        System.out.println("----------------------");
    }
}

public class HotelDemo5 {
    public static void main(String[] args) {

        Pair<Integer, String> booking1 = new Pair<>(101, "Rahul");

        Pair<Integer, String> booking2 = new Pair<>(102, "Ananya");

        booking1.displayBooking();
        booking2.displayBooking();
    }
}