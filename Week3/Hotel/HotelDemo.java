package Week3.Hotel;

class ServiceTask implements Runnable {

    private String serviceName;
    private int roomNumber;
    private int duration;

    public ServiceTask(String serviceName, int roomNumber, int duration) {
        this.serviceName = serviceName;
        this.roomNumber = roomNumber;
        this.duration = duration;
    }

    @Override
    public void run() {
        try {
//            System.out.println(Thread.currentThread().getName());
            System.out.println(serviceName + " started for Room " + roomNumber);
            Thread.sleep(duration);
            System.out.println(serviceName + " completed for Room " + roomNumber);
        } catch (InterruptedException e) {
            System.out.println(serviceName + " interrupted for Room " + roomNumber);
        }
    }
}

public class HotelDemo {
    public static void main(String[] args) {

        ServiceTask cleaning = new ServiceTask("Room Cleaning", 101, 3000);
        ServiceTask foodDelivery = new ServiceTask("Food Delivery", 102, 2000);
        ServiceTask maintenance = new ServiceTask("Maintenance", 103, 4000);

        Thread t1 = new Thread(cleaning);
        Thread t2 = new Thread(foodDelivery);
        Thread t3 = new Thread(maintenance);

        t1.start();
        t2.start();
        t3.start();

        System.out.println("All threads have been started...");
    }
}
