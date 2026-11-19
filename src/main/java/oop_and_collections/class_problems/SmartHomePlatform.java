package main.java.oop_and_collections.class_problems;
import java.util.*;

interface Dimmable { void dim(int level); }
interface Schedulable { void schedule(String time); }
interface EnergyMonitorable { void reportEnergy(); }

abstract class SmartDevice {
    protected final String id;
    public SmartDevice(String id) { this.id = id; }
    public String getId() { return id; }
    public void turnOn() { System.out.println(id + " is ON"); }
}

class Light extends SmartDevice implements Dimmable, Schedulable {
    public Light(String id) { super(id); }
    public void dim(int level) { System.out.println(id + " dimmed to " + level); }
    public void schedule(String time) { System.out.println(id + " scheduled " + time); }
}

class Fan extends SmartDevice implements Schedulable {
    public Fan(String id) { super(id); }
    public void schedule(String time) { System.out.println(id + " scheduled " + time); }
}

class Plug extends SmartDevice implements EnergyMonitorable {
    private final int kwh;
    public Plug(String id, int kwh) { super(id); this.kwh = kwh; }
    public void reportEnergy() { System.out.println(id + " energy " + kwh + " kWh"); }
}

public class SmartHomePlatform {
    private final Map<String, SmartDevice> devices = new HashMap<>();

    public void addDevice(SmartDevice d) { devices.put(d.getId(), d); }

    public void processCommand(String cmdLine) {
        String[] parts = cmdLine.split(" ");
        String cmd = parts[0], id = parts[1];
        SmartDevice d = devices.get(id);
        if (d == null) return;

        switch (cmd) {
            case "ON":
                d.turnOn();
                break;
            case "DIM":
                if (d instanceof Dimmable) ((Dimmable) d).dim(Integer.parseInt(parts[2]));
                else System.out.println(id + " rejected: DIM unsupported");
                break;
            case "SCHEDULE":
                if (d instanceof Schedulable) ((Schedulable) d).schedule(parts[2]);
                else System.out.println(id + " rejected: SCHEDULE unsupported");
                break;
            case "ENERGY":
                if (d instanceof EnergyMonitorable) ((EnergyMonitorable) d).reportEnergy();
                else System.out.println(id + " rejected: ENERGY unsupported");
                break;
        }
    }

    public static void main(String[] args) {
        SmartHomePlatform home = new SmartHomePlatform();
        home.addDevice(new Light("L1"));
        home.addDevice(new Fan("F1"));
        home.addDevice(new Plug("P1", 12));

        String[] commands = {
            "ON L1", "DIM L1 40", "DIM F1 30",
            "SCHEDULE F1 22:00", "ENERGY P1", "ENERGY L1"
        };
        for (String c : commands) home.processCommand(c);
    }
}