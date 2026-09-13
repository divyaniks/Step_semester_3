package oop_fundamentals.class_problems;
import java.util.Scanner;

public class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String hashedLockerPin;
    private boolean isIdSet = false;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        if (patientId != null) {
            this.patientId = patientId;
            this.isIdSet = true;
        }
        this.name = name;
        this.discharged = false;
    }

    public void setPatientId(String id) {
        if (!this.isIdSet && id != null) {
            this.patientId = id;
            this.isIdSet = true;
        }
    }

    public String getPatientId() {
        return this.patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin != null && pin.matches("\\d{4,6}")) {
            this.hashedLockerPin = "HASHED_" + pin.hashCode();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientProfile p = new PatientProfile();
        
        if (scanner.hasNext()) {
            String name = scanner.nextLine();
            p.setName(name);
            
            String id1 = scanner.next();
            p.setPatientId(id1);
            
            String id2 = scanner.next();
            p.setPatientId(id2);
            
            String pin = scanner.next();
            p.setLockerPin(pin);
            
            System.out.println(p.getName());
            System.out.println(p.getPatientId());
        }
        scanner.close();
    }
}