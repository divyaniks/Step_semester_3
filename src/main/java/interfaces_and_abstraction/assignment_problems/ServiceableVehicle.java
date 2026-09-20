package interfaces_and_abstraction.assignment_problems;

public abstract class ServiceableVehicle {

    private final String vehicleId;
    private double serviceCost;

    public ServiceableVehicle(String vehicleId, double serviceCost) {
        this.vehicleId = vehicleId;
        this.serviceCost = serviceCost;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public abstract String performService();
}