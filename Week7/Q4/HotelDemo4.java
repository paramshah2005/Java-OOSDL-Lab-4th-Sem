package Week7.Q4;

public class HotelDemo4 {

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Integer[] roomNumbers = { 101, 102, 103 };
        String[] roomTypes = { "Single", "Double", "Suite" };
        Double[] roomPrices = { 2000.0, 3000.0, 5000.0 };

        System.out.println("Room Numbers:");
        printArray(roomNumbers);

        System.out.println("Room Types:");
        printArray(roomTypes);

        System.out.println("Room Prices:");
        printArray(roomPrices);
    }
}