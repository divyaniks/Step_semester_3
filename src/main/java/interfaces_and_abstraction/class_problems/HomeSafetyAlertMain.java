package interfaces_and_abstraction.class_problems;

public class HomeSafetyAlertMain {

    static void broadcastAll(Alertable[] devices, String message) {
        for (Alertable device : devices) {
            System.out.println(device.sendAlert(message));
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor) {
            MotionSensor motionSensor = (MotionSensor) a;
            return motionSensor.getZoneName();
        }

        return "Not a motion sensor";
    }

    public static void main(String[] args) {

        MotionSensor m = new MotionSensor("Living Room");

        DualZoneMotionSensor d =
                new DualZoneMotionSensor("Hallway", "Stairwell");

        SmokeDetector s = new SmokeDetector("SD-01");

        System.out.println(m.sendAlert("Motion detected"));
        System.out.println(d.sendAlert("Motion detected"));
        System.out.println(s.sendAlert("Smoke detected"));

        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));

        Alertable[] devices = {m, d, s};

        broadcastAll(devices, "Safety alert detected");
    }
}