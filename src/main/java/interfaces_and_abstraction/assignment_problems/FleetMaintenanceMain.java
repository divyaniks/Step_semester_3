package interfaces_and_abstraction.assignment_problems;

public class FleetMaintenanceMain {

    static String getInsuranceIfAvailable(Object vehicle) {

        if (vehicle instanceof Insurable) {
            Insurable insurable = (Insurable) vehicle;
            return insurable.getInsuranceDetails();
        }

        return "Insurance not available";
    }

    public static void main(String[] args) {

        Forklift forklift =
                new Forklift("FL-101", 2500);

        HeavyDutyForklift heavyForklift =
                new HeavyDutyForklift(
                        "HDF-202",
                        4500,
                        "INS-7788"
                );

        ServiceableVehicle[] vehicles = {
            forklift,
            heavyForklift
        };

        for (ServiceableVehicle vehicle : vehicles) {
            System.out.println(vehicle.performService());
        }

        System.out.println(
                getInsuranceIfAvailable(heavyForklift)
        );

        System.out.println(
                getInsuranceIfAvailable(forklift)
        );
    }
}