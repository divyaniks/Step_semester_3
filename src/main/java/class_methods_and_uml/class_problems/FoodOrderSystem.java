package main.java.class_methods_and_uml.class_problems;
import java.util.*;

interface IPaymentMethod {
    String getName();
    boolean pay(double amount);
}

class CreditCardPayment implements IPaymentMethod {
    private final boolean shouldSucceed;
    public CreditCardPayment(boolean shouldSucceed) { this.shouldSucceed = shouldSucceed; }
    public String getName() { return "Credit Card"; }
    public boolean pay(double amount) { return shouldSucceed; }
}

class DigitalWalletPayment implements IPaymentMethod {
    private final boolean shouldSucceed;
    public DigitalWalletPayment(boolean shouldSucceed) { this.shouldSucceed = shouldSucceed; }
    public String getName() { return "Digital Wallet"; }
    public boolean pay(double amount) { return shouldSucceed; }
}

class LineItem {
    final String name;
    final int qty;
    final double unitPrice;

    public LineItem(String name, int qty, double unitPrice) {
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public double getSubtotal() { return qty * unitPrice; }
}

class FoodOrder {
    private final int orderId;
    private final List<LineItem> items = new ArrayList<>();
    private String status = "Created";

    public FoodOrder(int orderId) {
        this.orderId = orderId;
        System.out.println("Order created.");
    }

    public void addItem(String name, int qty, double price) {
        items.add(new LineItem(name, qty, price));
        System.out.printf("Added %s (Qty %d).%n", name, qty);
    }

    public void placeAndPay(IPaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
            return;
        }
        double total = items.stream().mapToDouble(LineItem::getSubtotal).sum();
        if (paymentMethod.pay(total)) {
            status = "Paid";
            System.out.printf("Order placed successfully. Payment via %s successful. Order status: %s.%n",
                    paymentMethod.getName(), status);
            System.out.printf("Notification: Order #%d placed and paid.%n", orderId);
        } else {
            status = "Pending Payment";
            System.out.printf("Order placed. Payment via %s failed. Order status: %s.%n",
                    paymentMethod.getName(), status);
            System.out.printf("Notification: Order #%d placed, awaiting payment.%n", orderId);
        }
    }
}

public class FoodOrderSystem {
    public static void main(String[] args) {
        FoodOrder order123 = new FoodOrder(123);
        order123.addItem("Pizza", 2, 12.0);
        order123.addItem("Soda", 1, 2.0);

        FoodOrder emptyOrder = new FoodOrder(122);
        emptyOrder.placeAndPay(new CreditCardPayment(true));

        order123.placeAndPay(new CreditCardPayment(true));

        FoodOrder order124 = new FoodOrder(124);
        order124.addItem("Burger", 1, 8.0);
        order124.placeAndPay(new DigitalWalletPayment(false));
    }
}