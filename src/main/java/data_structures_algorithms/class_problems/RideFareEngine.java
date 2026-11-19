package main.java.data_structures_algorithms.class_problems;
abstract class Ride {
    protected final double distance;
    public Ride(double distance) { this.distance = distance; }
    public abstract String getType();
    public abstract double calculateFare();
}

class Auto extends Ride {
    public Auto(double distance) { super(distance); }
    public String getType() { return "Auto"; }
    public double calculateFare() { return 30.0 + (15.0 * distance); }
}

class Cab extends Ride {
    private final double surge;
    public Cab(double distance, double surge) {
        super(distance);
        this.surge = surge;
    }
    public String getType() { return "Cab"; }
    public double calculateFare() { return (50.0 + (20.0 * distance)) * surge; }
}

class Bike extends Ride {
    private final int waitingMinutes;
    public Bike(double distance, int waitingMinutes) {
        super(distance);
        this.waitingMinutes = waitingMinutes;
    }
    public String getType() { return "Bike"; }
    public double calculateFare() { return 20.0 + (10.0 * distance) + (2.0 * waitingMinutes); }
}

public class RideFareEngine {
    public static void main(String[] args) {
        Ride[] rides = {
            new Auto(4),
            new Cab(6, 1.5),
            new Bike(3, 5)
        };

        double total = 0;
        for (Ride r : rides) {
            double fare = r.calculateFare();
            total += fare;
            System.out.printf("%s: %.0f%n", r.getType(), fare);
        }
        System.out.printf("Total: %.0f%n", total);
    }
}