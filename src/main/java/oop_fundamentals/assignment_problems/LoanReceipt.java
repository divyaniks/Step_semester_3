package oop_fundamentals.assignment_problems;
import java.util.Arrays;
import java.util.Scanner;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        
        if (bookIds == null) {
            throw new IllegalArgumentException("construction rejected");
        }

        for (String id : bookIds) {
            if (id == null || !id.matches("^BK-\\d{3}$")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }
        
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return this.memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(this.bookIds, this.bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= this.bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid entry offset");
        }
        
        String[] updatedIds = getBookIds();
        updatedIds[index] = newId;
        
        return new LoanReceipt(this.memberId, updatedIds);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            LoanReceipt[] receipts = new LoanReceipt[n];
            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                if ("null".equalsIgnoreCase(type)) {
                    receipts[i] = null;
                } else {
                    String id = scanner.next();
                    int numIds = scanner.nextInt();
                    String[] bookIds = new String[numIds];
                    for (int j = 0; j < numIds; j++) {
                        bookIds[j] = scanner.next();
                    }
                    try {
                        if ("reference".equalsIgnoreCase(type)) {
                            String room = scanner.next();
                            receipts[i] = new ReferenceOnlyLoanReceipt(id, bookIds, room);
                        } else {
                            receipts[i] = new LoanReceipt(id, bookIds);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
            }
            System.out.println(NightlyCirculationLedger.processNightlyCirculation(receipts));
        }
        scanner.close();
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

class NightlyCirculationLedger {
    private static String systemMode;

    static {
        systemMode = "CIRCULATION_PROCESSING_ACTIVE";
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processedCount = 0;
        int nullSkippedCount = 0;
        int referenceOnlyCount = 0;
        int regularCount = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnlyCount++;
            } else {
                regularCount++;
            }
        }

        return processedCount + " processed | " + 
               nullSkippedCount + " null skipped | " + 
               referenceOnlyCount + " reference-only | " + 
               regularCount + " regular";
    }
}