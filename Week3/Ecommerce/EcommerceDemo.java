package Week3.Ecommerce;

class Order {
    private String orderId;
    private String customerName;

    public Order(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }
}

class OrderProcessing implements Runnable {
    private Order order;
    private String stage;

    public OrderProcessing(Order order, String stage) {
        this.order = order;
        this.stage = stage;
    }

    @Override
    public void run() {
        try {
            System.out.println(order.getOrderId() + " (" + order.getCustomerName() + ") - " + stage + " started.");
            Thread.sleep(2000);
            System.out.println(order.getOrderId() + " (" + order.getCustomerName() + ") - " + stage + " completed.");
        } catch (InterruptedException e) {
            System.out.println("Order was interrupted: " + order.getOrderId());
        }
    }
}
    
public class EcommerceDemo {
    public static void main(String[] args) {
        Order order1 = new Order("O123", "Alice");
        Order order2 = new Order("O124", "Bob");
        Order order3 = new Order("O125", "Charlie");

        processOrder(order1);
        processOrder(order2);
        processOrder(order3);
    }

    private static void processOrder(Order order) {
        Thread validationThread = new Thread(new OrderProcessing(order, "Validation"));
        Thread paymentThread = new Thread(new OrderProcessing(order, "Payment"));
        Thread shipmentThread = new Thread(new OrderProcessing(order, "Shipment"));

        validationThread.start();
        paymentThread.start();
        shipmentThread.start();
    }
}

