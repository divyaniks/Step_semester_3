package oop_fundamentals.class_problems;
import java.util.Scanner;

public class AccessRuleEngineP1 {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) return "DENIED";

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "protected":
            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeBatch(String[][] attempts) {
        if (attempts == null) return "Allowed: 0 | Denied: 0";
        
        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            if (attempt != null && attempt.length >= 2) {
                String result = classifyAccess(attempt[0], attempt[1]);
                if ("ALLOWED".equals(result)) {
                    allowed++;
                } else {
                    denied++;
                }
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String choice = scanner.next();
            if ("single".equalsIgnoreCase(choice)) {
                String mod = scanner.next();
                String ctx = scanner.next();
                System.out.println(classifyAccess(mod, ctx));
            } else if ("batch".equalsIgnoreCase(choice)) {
                int n = scanner.nextInt();
                String[][] attempts = new String[n][2];
                for (int i = 0; i < n; i++) {
                    attempts[i][0] = scanner.next();
                    attempts[i][1] = scanner.next();
                }
                System.out.println(summarizeBatch(attempts));
            } else if ("record".equalsIgnoreCase(choice)) {
                scanner.nextLine();
                String id = scanner.nextLine();
                String ward = scanner.nextLine();
                double vitals = scanner.nextDouble();
                scanner.nextLine();
                String facility = scanner.nextLine();
                try {
                    PatientRecord record = new PatientRecord(id, ward, vitals, facility);
                    System.out.println("Success");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }
}

class PatientRecord {
    private String patientId;
    String wardCode;                  
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        if (patientId == null || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.patientId = patientId;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    private PatientRecord() {}
}