package oop_fundamentals.class_problems;

import java.util.Scanner;

public class EventTicket {
    private String attendeeId;
    private double basePrice;
    private double amountPaid;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.amountPaid = 0.0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public double getBalanceDue() {
        return this.basePrice - this.amountPaid;
    }

    public String getAttendeeId() {
        return this.attendeeId;
    }

    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + getBalanceDue();
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        if (attendeeIds == null) {
            return "Registered: 0 | Rejected: 0";
        }

        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String choice = scanner.next();
            if ("single".equalsIgnoreCase(choice)) {
                String id = scanner.next();
                double price = scanner.nextDouble();
                try {
                    EventTicket t = new EventTicket(id, price);
                    System.out.println("Success");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if ("workshop".equalsIgnoreCase(choice)) {
                String id = scanner.next();
                double price = scanner.nextDouble();
                String track = scanner.next();
                try {
                    WorkshopTicket w = new WorkshopTicket(id, price, track);
                    double payment = scanner.nextDouble();
                    w.pay(payment);
                    System.out.println(w.getBalanceDue());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if ("batch".equalsIgnoreCase(choice)) {
                int n = scanner.nextInt();
                double price = scanner.nextDouble();
                String[] ids = new String[n];
                scanner.nextLine();
                for (int i = 0; i < n; i++) {
                    ids[i] = scanner.nextLine();
                }
                System.out.println(registerBatch(ids, price));
            }
        }
        scanner.close();
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    public String getTrack() {
        return this.track;
    }

    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue();
    }
}