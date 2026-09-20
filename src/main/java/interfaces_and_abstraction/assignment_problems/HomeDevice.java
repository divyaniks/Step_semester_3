package interfaces_and_abstraction.assignment_problems;

public abstract class HomeDevice {

    private final String deviceId;
    private String deviceName;

    public HomeDevice(String deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public abstract String getStatus();
}