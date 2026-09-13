package oop_fundamentals.class_problems;

import java.util.Scanner;

public class TicketSettlementEngine {
    private final String ticketId;
    private double basePrice;
    private double amountPaid;
    private static int sharedCounter = 1000;

    public TicketSettlementEngine(double basePrice) {
        sharedCounter++;
        this.ticketId = "TCK-" + sharedCounter;
        this.basePrice = basePrice;
        this.amountPaid = 0.0;
    }

    public String getTicketId() {
        return this.ticketId;
    }

    public double getBalanceDue() {
        return this.basePrice - this.amountPaid;
    }

    public static int getTicketsIssued() {
        return sharedCounter - 1000;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public void pay(double amount, String mode) {
        System.out.println("Processing via " + mode);
        this.pay(amount);
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'F') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }
        return true;
    }

    public static String processNightlySettlement(TicketSettlementEngine[] tickets) {
        int processedCount = 0;
        int nullSkippedCount = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (tickets == null) {
            return "0 processed | 0 null skipped | 0 group | 0 individual";
        }

        for (TicketSettlementEngine t : tickets) {
            if (t == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            if (t instanceof GroupTicket) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processedCount + " processed | " + 
               nullSkippedCount + " null skipped | " + 
               groupCount + " group | " + 
               individualCount + " individual";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String op = scanner.next();
            if ("promo".equalsIgnoreCase(op)) {
                String code = scanner.next();
                System.out.println(isValidPromoCode(code));
            } else if ("pay".equalsIgnoreCase(op)) {
                double base = scanner.nextDouble();
                TicketSettlementEngine t = new TicketSettlementEngine(base);
                double p1 = scanner.nextDouble();
                t.pay(p1);
                double p2 = scanner.nextDouble();
                String mode = scanner.next();
                t.pay(p2, mode);
                System.out.println(t.getTicketId());
                System.out.println("Issued: " + getTicketsIssued());
                System.out.println("Remaining Balance: " + t.getBalanceDue());
            } else if ("batch".equalsIgnoreCase(op)) {
                int n = scanner.nextInt();
                TicketSettlementEngine[] ledger = new TicketSettlementEngine[n];
                for (int i = 0; i < n; i++) {
                    String type = scanner.next();
                    if ("null".equalsIgnoreCase(type)) {
                        ledger[i] = null;
                    } else if ("group".equalsIgnoreCase(type)) {
                        double price = scanner.nextDouble();
                        int size = scanner.nextInt();
                        ledger[i] = new GroupTicket(price, size);
                    } else {
                        double price = scanner.nextDouble();
                        ledger[i] = new TicketSettlementEngine(price);
                    }
                }
                System.out.println(processNightlySettlement(ledger));
            }
        }
        scanner.close();
    }
}

class GroupTicket extends TicketSettlementEngine {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}