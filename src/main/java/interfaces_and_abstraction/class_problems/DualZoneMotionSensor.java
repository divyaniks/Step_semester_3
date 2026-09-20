package interfaces_and_abstraction.class_problems;

public class DualZoneMotionSensor extends MotionSensor {

    private String secondZoneName;

    public DualZoneMotionSensor(String zoneName, String secondZoneName) {
        super(zoneName);
        this.secondZoneName = secondZoneName;
    }

    @Override
    public String sendAlert(String message) {
        return super.sendAlert(message)
                + " [also covering " + secondZoneName + "]";
    }
}