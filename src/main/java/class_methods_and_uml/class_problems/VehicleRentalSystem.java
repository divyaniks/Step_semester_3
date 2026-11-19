package class_methods_and_uml.class_problems;

abstract class Vehicle {
    private final String name;
    private boolean available = true;

    public Vehicle(String name) { this.name = name; }
    public String getName() { return name; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public abstract double calculateRentalCharge(int days);
}

class StandardCar extends Vehicle {
    public StandardCar(String name) { super(name); }
    public double calculateRentalCharge(int days) { return days * 50.0; }
}

class LuxuryCar extends Vehicle {
    public LuxuryCar(String name) { super(name); }
    public double calculateRentalCharge(int days) { return days * 100.0; }
}

class RentalService {
    public void rentVehicle(Vehicle v, int days) {
        if (!v.isAvailable()) {
            System.out.printf("Booking failed: %s is already rented.%n", v.getName());
            return;
        }
        v.setAvailable(false);
        System.out.printf("%s rented for %d days. Total charge: $%.2f.%n",
                v.getName(), days, v.calculateRentalCharge(days));
    }

    public void returnVehicle(Vehicle v) {
        v.setAvailable(true);
        System.out.printf("%s returned. Now available.%n", v.getName());
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        Vehicle luxuryA = new LuxuryCar("Luxury Car A");
        Vehicle standardB = new StandardCar("Standard Car B");

        service.rentVehicle(luxuryA, 3);
        service.rentVehicle(standardB, 5);
        service.returnVehicle(luxuryA);
    }
}