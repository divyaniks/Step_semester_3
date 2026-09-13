package oop_fundamentals.class_problems;
import java.util.Arrays;
import java.util.Scanner;

public class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    public DischargeSummary(String patientId, String[] medicationCodes) {
        this.patientId = patientId;
        
        if (medicationCodes == null) {
            throw new IllegalArgumentException("construction rejected");
        }

        for (String code : medicationCodes) {
            if (code == null || !code.matches("^MED-[A-Z]$")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }
        
        this.medicationCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public String getPatientId() {
        return this.patientId;
    }

    public String[] getMedicationCodes() {
        return Arrays.copyOf(this.medicationCodes, this.medicationCodes.length);
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= this.medicationCodes.length) {
            throw new IndexOutOfBoundsException("Invalid entry offset");
        }
        
        String[] updatedCodes = getMedicationCodes();
        updatedCodes[index] = newCode;
        
        return new DischargeSummary(this.patientId, updatedCodes);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            DischargeSummary[] batch = new DischargeSummary[n];
            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                if ("null".equalsIgnoreCase(type)) {
                    batch[i] = null;
                } else {
                    String id = scanner.next();
                    int numCodes = scanner.nextInt();
                    String[] codes = new String[numCodes];
                    for (int j = 0; j < numCodes; j++) {
                        codes[j] = scanner.next();
                    }
                    try {
                        if ("critical".equalsIgnoreCase(type)) {
                            int days = scanner.nextInt();
                            batch[i] = new CriticalCareDischargeSummary(id, codes, days);
                        } else {
                            batch[i] = new DischargeSummary(id, codes);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
            }
            System.out.println(NightlyProcessor.processNightlyBatch(batch));
        }
        scanner.close();
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}

class NightlyProcessor {
    private static String systemMode;

    static {
        systemMode = "BATCH_PROCESSING_ACTIVE";
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processedCount = 0;
        int nullSkippedCount = 0;
        int criticalCareCount = 0;
        int routineCount = 0;

        if (summaries == null) {
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";
        }

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            
            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCareCount++;
            } else {
                routineCount++;
            }
        }

        return processedCount + " processed | " + 
               nullSkippedCount + " null skipped | " + 
               criticalCareCount + " critical-care | " + 
               routineCount + " routine";
    }
}