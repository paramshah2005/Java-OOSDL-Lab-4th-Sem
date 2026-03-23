package Week8.Q1;

import java.util.*;

class Room {
    int roomNumber;
    String roomType;
    double price;
    boolean isAvailable;

    Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true;
    }
}

class Customer {
    int customerId;
    String name;
    String contact;
    int roomNumber;

    Customer(int customerId, String name, String contact, int roomNumber) {
        this.customerId = customerId;
        this.name = name;
        this.contact = contact;
        this.roomNumber = roomNumber;
    }
}

public class HotelDemo {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static HashMap<Integer, Customer> roomBookings = new HashMap<>();

    static Scanner sc = new Scanner(System.in);

    public static void addRoom() {
        try {
            System.out.print("Enter Room Number: ");
            int number = sc.nextInt();

            for (Room r : rooms) {
                if (r.roomNumber == number) {
                    System.out.println("Room already exists!");
                    return;
                }
            }

            System.out.print("Enter Room Type: ");
            String type = sc.next();

            System.out.print("Enter Price: ");
            double price = sc.nextDouble();

            rooms.add(new Room(number, type, price));
            System.out.println("Room added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();
        }
    }

    public static void displayRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        Collections.sort(rooms, Comparator.comparingDouble(r -> r.price));

        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (r.isAvailable) {
                System.out.println("Room No: " + r.roomNumber +
                        ", Type: " + r.roomType +
                        ", Price: " + r.price);
            }
        }
    }

    public static void addCustomer() {
        try {
            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();

            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Contact: ");
            String contact = sc.next();

            customers.add(new Customer(id, name, contact, -1));
            System.out.println("Customer added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();
        }
    }

    public static void bookRoom() {
        try {
            System.out.print("Enter Customer ID: ");
            int custId = sc.nextInt();

            Customer customer = null;
            for (Customer c : customers) {
                if (c.customerId == custId) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) {
                System.out.println("Customer not found!");
                return;
            }

            System.out.print("Enter Room Number to Book: ");
            int roomNo = sc.nextInt();

            for (Room r : rooms) {
                if (r.roomNumber == roomNo) {
                    if (!r.isAvailable) {
                        System.out.println("Room already booked!");
                        return;
                    }

                    r.isAvailable = false;
                    customer.roomNumber = roomNo;
                    roomBookings.put(roomNo, customer);

                    System.out.println("Room booked successfully!");
                    return;
                }
            }

            System.out.println("Room not found!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();
        }
    }

    public static void checkout() {
        try {
            System.out.print("Enter Room Number: ");
            int roomNo = sc.nextInt();

            if (!roomBookings.containsKey(roomNo)) {
                System.out.println("No booking found for this room!");
                return;
            }

            Customer customer = roomBookings.get(roomNo);

            for (Room r : rooms) {
                if (r.roomNumber == roomNo) {
                    r.isAvailable = true;
                    break;
                }
            }

            roomBookings.remove(roomNo);

            Iterator<Customer> it = customers.iterator();
            while (it.hasNext()) {
                Customer c = it.next();
                if (c.customerId == customer.customerId) {
                    it.remove();
                    break;
                }
            }

            System.out.println("Checkout successful!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();
        }
    }

    public static void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        System.out.println("\nCustomer Details:");
        for (Customer c : customers) {
            System.out.println("ID: " + c.customerId +
                    ", Name: " + c.name +
                    ", Contact: " + c.contact +
                    ", Room: " + (c.roomNumber == -1 ? "Not Assigned" : c.roomNumber));
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- HOTEL MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Room");
            System.out.println("2. Display Available Rooms");
            System.out.println("3. Add Customer");
            System.out.println("4. Book Room");
            System.out.println("5. Checkout Customer");
            System.out.println("6. Display All Customers");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice;

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    displayRooms();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    bookRoom();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    displayCustomers();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}