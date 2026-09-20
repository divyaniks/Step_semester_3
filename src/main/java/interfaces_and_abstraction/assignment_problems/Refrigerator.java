package interfaces_and_abstraction.assignment_problems;

public class Refrigerator extends HomeDevice
        implements RemoteControllable, EnergyTrackable {

    private boolean running;
    private double energyUsage;

    public Refrigerator(String deviceId, String deviceName) {
        super(deviceId, deviceName);
    }

    @Override
    public String turnOn() {
        running = true;
        return getDeviceName() + " turned on";
    }

    @Override
    public String turnOff() {
        running = false;
        return getDeviceName() + " turned off";
    }

    @Override
    public double getEnergyUsage() {
        return energyUsage;
    }

    public void setEnergyUsage(double energyUsage) {
        this.energyUsage = energyUsage;
    }

    @Override
    public String getStatus() {
        return running ? "Running" : "Off";
    }
}