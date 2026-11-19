package main.java.data_structures_algorithms.class_problems;
class Order {
    // Static nested class: represents an independent data record without needing an Order instance
    public static class LineItem {
        private final String productName;
        private final int quantity;
        private final double unitPrice;

        public LineItem(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public double total() {
            return quantity * unitPrice;
        }
    }

    private final LineItem[] items;
    private int count = 0;

    public Order(int capacity) {
        this.items = new LineItem[capacity];
    }

    public void addItem(LineItem item) {
        if (count < items.length) {
            items[count++] = item;
        }
    }

    // Member inner class: bound to a specific Order instance so it can directly access private 'items' and 'count'
    public class Cursor {
        private int index = 0;

        public boolean hasNext() { return index < count; }
        public LineItem next() { return items[index++]; }
        public void reset() { index = 0; }
    }

    public Cursor cursor() {
        return new Cursor();
    }
}

public class OrderWalkthrough {
    public static void main(String[] args) {
        Order order = new Order(10);
        order.addItem(new Order.LineItem("Pen", 3, 10));
        order.addItem(new Order.LineItem("Book", 2, 250));
        order.addItem(new Order.LineItem("Bag", 1, 700));

        Order.Cursor cursor = order.cursor();
        double grandTotal = 0;
        while (cursor.hasNext()) {
            Order.LineItem item = cursor.next();
            double t = item.total();
            grandTotal += t;
            System.out.printf("%s %.0f%n", item.productName, t);
        }
        System.out.printf("Total %.0f%n", grandTotal);
        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
    }
}