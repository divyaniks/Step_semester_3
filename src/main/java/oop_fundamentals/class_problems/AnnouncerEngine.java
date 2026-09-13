package oop_fundamentals.class_problems;

import java.util.Scanner;

public class AnnouncerEngine {
    public static String batchPrint(AnnouncerEventTicket[] tickets) {
        if (tickets == null) return "";
        StringBuilder report = new StringBuilder();
        for (AnnouncerEventTicket ticket : tickets) {
            if (ticket != null) {
                report.append(ticket.printTicket());
                if (ticket instanceof AnnouncerWorkshopTicket) {
                    AnnouncerWorkshopTicket wt = (AnnouncerWorkshopTicket) ticket;
                    report.append(" [Track via downcast: ").append(wt.getTrack()).append("]");
                }
                report.append(" | ");
            }
        }
        return report.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            AnnouncerEventTicket[] tickets = new AnnouncerEventTicket[n];
            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                double price = scanner.nextDouble();
                if ("standard".equalsIgnoreCase(type)) {
                    tickets[i] = new AnnouncerEventTicket(price);
                } else if ("workshop".equalsIgnoreCase(type)) {
                    String track = scanner.next();
                    tickets[i] = new AnnouncerWorkshopTicket(price, track);
                }
            }
            System.out.println(batchPrint(tickets));
        }
        scanner.close();
    }
}

class AnnouncerEventTicket {
    private double basePrice;

    public AnnouncerEventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String printTicket() {
        return "Standard | Balance: " + basePrice;
    }
}

class AnnouncerWorkshopTicket extends AnnouncerEventTicket {
    private String track;

    public AnnouncerWorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: " + track + " | Balance: " + getBasePrice();
    }
}