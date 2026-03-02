package Week7.Q2;

class RoomManagement {
    public static <T> void display(T data) {
        System.out.println("Data: " + data);
    }

    public static void main(String[] args) {

        Integer roomNumber = 105;
        String roomType = "Suite";
        Double price = 5000.75;
        Boolean bookingStatus = true;

        display(roomNumber);
        display(roomType);
        display(price);
        display(bookingStatus);
    }
}