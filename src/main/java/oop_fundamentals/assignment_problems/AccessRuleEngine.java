package oop_fundamentals.assignment_problems;
import java.util.Scanner;

public class AccessRuleEngine{
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

    public static String summarizeByModifier(String[][] attempts) {
        if (attempts == null) return "";
        
        int privAllowed = 0, privDenied = 0;
        int defAllowed = 0, defDenied = 0;
        int protAllowed = 0, protDenied = 0;
        int pubAllowed = 0, pubDenied = 0;

        for (String[] attempt : attempts) {
            if (attempt != null && attempt.length >= 2) {
                String mod = attempt[0];
                String result = classifyAccess(mod, attempt[1]);
                boolean allowed = "ALLOWED".equals(result);

                if ("private".equals(mod)) {
                    if (allowed) privAllowed++; else privDenied++;
                } else if ("default".equals(mod)) {
                    if (allowed) defAllowed++; else defDenied++;
                } else if ("protected".equals(mod)) {
                    if (allowed) protAllowed++; else protDenied++;
                } else if ("public".equals(mod)) {
                    if (allowed) pubAllowed++; else pubDenied++;
                }
            }
        }
        return "private: " + privAllowed + " allowed / " + privDenied + " denied | " +
               "default: " + defAllowed + " allowed / " + defDenied + " denied | " +
               "protected: " + protAllowed + " allowed / " + protDenied + " denied | " +
               "public: " + pubAllowed + " allowed / " + pubDenied + " denied";
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
                System.out.println(summarizeByModifier(attempts));
            } else if ("record".equalsIgnoreCase(choice)) {
                scanner.nextLine();
                String id = scanner.nextLine();
                String branch = scanner.nextLine();
                double fines = scanner.nextDouble();
                scanner.nextLine();
                String name = scanner.nextLine();
                try {
                    LibraryMember member = new LibraryMember(id, branch, fines, name);
                    System.out.println("Success");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }
}

class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    private LibraryMember() {}
}