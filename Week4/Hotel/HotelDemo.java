package Week4.Hotel;

class Hotel {

    private int availableRooms;

    Hotel(int rooms) {
        this.availableRooms = rooms;
    }

    synchronized void bookRoom(String customerName) {

        while (availableRooms == 0) {
            try {
                System.out.println(customerName + " is waiting to book a room...");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Booking interrupted");
            }
        }

        availableRooms--;
        System.out.println(customerName + " booked a room. Rooms left: " + availableRooms);
    }

    synchronized void releaseRoom(String customerName) {

        availableRooms++;
        System.out.println(customerName + " released a room. Rooms available: " + availableRooms);

        notify();
    }
}

class Customer extends Thread {

    private Hotel hotel;
    private boolean booking;

    Customer(Hotel hotel, String name, boolean booking) {
        super(name);
        this.hotel = hotel;
        this.booking = booking;
    }

    public void run() {

        if (booking) {
            hotel.bookRoom(getName());
        } else {
            hotel.releaseRoom(getName());
        }
    }
}

public class HotelDemo {

    public static void main(String[] args) {

        Hotel hotel = new Hotel(2);

        Customer c1 = new Customer(hotel, "Customer-1", true);
        Customer c2 = new Customer(hotel, "Customer-2", true);
        Customer c3 = new Customer(hotel, "Customer-3", true);

        Customer c4 = new Customer(hotel, "Customer-4", false);

        c1.start();
        c2.start();
        c3.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Main interrupted");
        }

        c4.start();
    }
}
