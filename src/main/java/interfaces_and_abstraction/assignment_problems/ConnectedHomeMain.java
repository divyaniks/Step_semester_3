package interfaces_and_abstraction.assignment_problems;

public class ConnectedHomeMain {

    public static void main(String[] args) {

        WashingMachine washingMachine =
                new WashingMachine("WM-101", "Washing Machine");

        Refrigerator refrigerator =
                new Refrigerator("RF-202", "Refrigerator");

        washingMachine.setEnergyUsage(2.5);
        refrigerator.setEnergyUsage(3.2);

        System.out.println(
                MobileApp.controlDevice(washingMachine)
        );

        System.out.println(
                MobileApp.controlDevice(refrigerator)
        );

        System.out.println(
                MobileApp.checkEnergy(washingMachine)
        );

        System.out.println(
                MobileApp.checkEnergy(refrigerator)
        );

        System.out.println(
                washingMachine.getStatus()
        );

        System.out.println(
                refrigerator.getStatus()
        );
    }
}