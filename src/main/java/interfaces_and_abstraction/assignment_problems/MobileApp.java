package interfaces_and_abstraction.assignment_problems;

public class MobileApp {

    public static String controlDevice(Object device) {

        if (device instanceof RemoteControllable) {

            RemoteControllable remote =
                    (RemoteControllable) device;

            return remote.turnOn();
        }

        return "Device is not remotely controllable";
    }

    public static String checkEnergy(Object device) {

        if (device instanceof EnergyTrackable) {

            EnergyTrackable energy =
                    (EnergyTrackable) device;

            return "Energy usage: "
                    + energy.getEnergyUsage();
        }

        return "Energy tracking not supported";
    }
}