package interfaces_and_abstraction.class_problems;

public class TeamLead extends StaffMember implements Auditable {

    public TeamLead() {
        this(0.0);
    }

    public TeamLead(double baseSalary) {
        super(baseSalary);
    }

    @Override
    public double calculateQuarterlyBonus() {
        return getBaseSalary() * 0.10;
    }

    @Override
    public String getAuditDetails() {
        return "TeamLead audit";
    }

    public static String getAuditIfApplicable(StaffMember staff) {
        if (staff instanceof Auditable) {
            return ((Auditable) staff).getAuditDetails();
        }

        return "Not auditable";
    }
}