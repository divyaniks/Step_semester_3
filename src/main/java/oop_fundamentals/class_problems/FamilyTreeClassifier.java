package oop_fundamentals.class_problems;

import java.util.Scanner;

public class FamilyTreeClassifier {
    public static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        } else if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Base/Intermediate branch";
    }

    public static double getTotalBalanceDue(EventTicket[] tickets) {
        if (tickets == null) return 0.0;
        double total = 0.0;
        for (EventTicket ticket : tickets) {
            if (ticket != null) {
                total += ticket.getBalanceDue();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            EventTicket[] tickets = new EventTicket[n];
            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                String id = scanner.next();
                double price = scanner.nextDouble();
                if ("standard".equalsIgnoreCase(type)) {
                    tickets[i] = new EventTicket(id, price);
                } else if ("workshop".equalsIgnoreCase(type)) {
                    String track = scanner.next();
                    tickets[i] = new WorkshopTicket(id, price, track);
                } else if ("premium".equalsIgnoreCase(type)) {
                    String track = scanner.next();
                    double fee = scanner.nextDouble();
                    tickets[i] = new PremiumWorkshopTicket(id, price, track, fee);
                } else if ("hackathon".equalsIgnoreCase(type)) {
                    String team = scanner.next();
                    tickets[i] = new HackathonTicket(id, price, team);
                }
            }
            
            for (EventTicket t : tickets) {
                System.out.println(t.printTicket());
                System.out.println(classifyGeneration(t));
            }
            System.out.println("Total Balance Due: " + getTotalBalanceDue(tickets));
        }
        scanner.close();
    }
}

class EventTicket {
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

class PremiumWorkshopTicket extends WorkshopTicket {
    private double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    public String printTicket() {
        return "Premium Workshop Ticket | Track: " + getTrack() + " | Kit Fee: " + kitFee + " | Balance Due: " + getBalanceDue();
    }
}

class HackathonTicket extends EventTicket {
    private String teamName;

    public HackathonTicket(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    public String printTicket() {
        return "Hackathon Ticket | Team: " + teamName + " | Balance Due: " + getBalanceDue();
    }
}