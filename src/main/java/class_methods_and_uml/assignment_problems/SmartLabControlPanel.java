package class_methods_and_uml.assignment_problems;
import java.util.*;

// 1. Capability Abstraction
interface Capability {
    String getName();
    boolean apply(String deviceName, Object value);
}

class PowerCapability implements Capability {
    private boolean isOn = false;

    @Override
    public String getName() { return "Power"; }

    @Override
    public boolean apply(String deviceName, Object value) {
        if (!(value instanceof Boolean)) return false;
        this.isOn = (Boolean) value;
        System.out.printf("%s: %s.%n", deviceName, isOn ? "ON" : "OFF");
        return true;
    }
}

class BrightnessCapability implements Capability {
    private int brightness = 0;

    @Override
    public String getName() { return "Brightness"; }

    @Override
    public boolean apply(String deviceName, Object value) {
        int level = (Integer) value;
        if (level < 0 || level > 100) {
            System.out.printf("Rejected: %s brightness must be between 0%% and 100%%.%n", deviceName);
            return false;
        }
        this.brightness = level;
        System.out.printf("%s: brightness set to %d%%.%n", deviceName, brightness);
        return true;
    }
}

class TemperatureCapability implements Capability {
    private int temp = 22;

    @Override
    public String getName() { return "Temperature"; }

    @Override
    public boolean apply(String deviceName, Object value) {
        int target = (Integer) value;
        if (target < 16 || target > 30) {
            System.out.printf("Rejected: %s temperature must be between 16°C and 30°C.%n", deviceName);
            return false;
        }
        this.temp = target;
        System.out.printf("%s: temperature set to %d°C.%n", deviceName, temp);
        return true;
    }
}

// 2. Device Composition
class SmartDevice {
    private final String name;
    private final Map<String, Capability> capabilities = new LinkedHashMap<>();

    public SmartDevice(String name, Capability... initialCaps) {
        this.name = name;
        for (Capability cap : initialCaps) {
            capabilities.put(cap.getName(), cap);
        }
    }

    public void addCapability(Capability cap) {
        capabilities.put(cap.getName(), cap);
        System.out.printf("%s: %s capability added.%n", name, cap.getName());
    }

    public boolean hasCapability(String capName) {
        return capabilities.containsKey(capName);
    }

    public boolean execute(String capName, Object value) {
        Capability cap = capabilities.get(capName);
        if (cap == null) return false;
        return cap.apply(name, value);
    }
}

// 3. Scene Execution
class SceneStep {
    final String capabilityName;
    final Object value;

    public SceneStep(String capabilityName, Object value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }
}

class Scene {
    private final String name;
    private final List<SceneStep> steps = new ArrayList<>();

    public Scene(String name) { this.name = name; }

    public void addStep(String capabilityName, Object value) {
        steps.add(new SceneStep(capabilityName, value));
    }

    public void run(List<SmartDevice> devices) {
        System.out.printf("Scene '%s' started.%n", name);
        int appliedCount = 0;
        for (SceneStep step : steps) {
            for (SmartDevice device : devices) {
                if (device.hasCapability(step.capabilityName)) {
                    if (device.execute(step.capabilityName, step.value)) {
                        appliedCount++;
                    }
                }
            }
        }
        System.out.printf("Scene '%s' completed: %d actions applied.%n", name, appliedCount);
    }
}

public class SmartLabControlPanel {
    public static void main(String[] args) {
        SmartDevice ac = new SmartDevice("Lab AC", new PowerCapability(), new TemperatureCapability());
        SmartDevice lights = new SmartDevice("Ceiling Lights", new PowerCapability(), new BrightnessCapability());
        SmartDevice projector = new SmartDevice("Projector", new PowerCapability());
        List<SmartDevice> labDevices = Arrays.asList(ac, lights, projector);

        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep("Power", true);
        lectureMode.addStep("Brightness", 40);
        lectureMode.addStep("Temperature", 24);
        lectureMode.run(labDevices);

        ac.execute("Temperature", 12);
        projector.addCapability(new BrightnessCapability());
        projector.execute("Brightness", 70);
    }
}