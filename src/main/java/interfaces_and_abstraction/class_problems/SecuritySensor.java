package interfaces_and_abstraction.class_problems;

public class SecuritySensor {

    protected String zoneName;

    public SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }
}