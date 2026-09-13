package oop_fundamentals.class_problems;

import java.util.Arrays;
import java.util.Scanner;

public class AuditTrailTicket {
    private String attendeeId;
    private double basePrice;
    private double amountPaid;
    private double[] lateFeeHistory;
    private int historyCount;

    public AuditTrailTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.amountPaid = 0.0;
        this.lateFeeHistory = new double[10];
        this.historyCount = 0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    protected void applyLateFee(double amount) {
        if (amount > 0 && historyCount < lateFeeHistory.length) {
            this.basePrice += amount;
            this.lateFeeHistory[historyCount++] = amount;
        }
    }

    public double getBalanceDue() {
        return this.basePrice - this.amountPaid;
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(this.lateFeeHistory, historyCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            double price = scanner.nextDouble();
            
            AuditTrailTicket ticket;
            try {
                if ("workshop".equalsIgnoreCase(type)) {
                    String track = scanner.next();
                    ticket = new AuditTrailWorkshopTicket(id, price, track);
                } else {
                    ticket = new AuditTrailTicket(id, price);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.close();
                return;
            }

            double payment = scanner.nextDouble();
            ticket.pay(payment);
            
            double fee = scanner.nextDouble();
            ticket.applyLateFee(fee);

            System.out.println("Balance: " + ticket.getBalanceDue());
            System.out.println("History: " + Arrays.toString(ticket.getLateFeeHistory()));
        }
        scanner.close();
    }
}

class AuditTrailWorkshopTicket extends AuditTrailTicket {
    private String track;

    public AuditTrailWorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}
