package interfaces_and_abstraction.class_problems;

public class MotionSensor extends SecuritySensor implements Alertable {

    public MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {
        return "[" + zoneName + "] " + message;
    }
}