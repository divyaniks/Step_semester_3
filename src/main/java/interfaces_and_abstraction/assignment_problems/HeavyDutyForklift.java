package interfaces_and_abstraction.assignment_problems;

public class HeavyDutyForklift extends Forklift
        implements Insurable {

    private String insuranceNumber;

    public HeavyDutyForklift(String vehicleId,
                             double serviceCost,
                             String insuranceNumber) {

        super(vehicleId, serviceCost);
        this.insuranceNumber = insuranceNumber;
    }

    @Override
    public String performService() {
        return super.performService()
                + " | Heavy-duty inspection completed";
    }

    @Override
    public String getInsuranceDetails() {
        return "Insurance: " + insuranceNumber;
    }
}