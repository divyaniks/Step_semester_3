package interfaces_and_abstraction.assignment_problems;

public class Forklift extends ServiceableVehicle {

    public Forklift(String vehicleId, double serviceCost) {
        super(vehicleId, serviceCost);
    }

    @Override
    public String performService() {
        return "Forklift " + getVehicleId()
                + " serviced for " + getServiceCost();
    }
}