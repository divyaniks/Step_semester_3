package interfaces_and_abstraction.class_problems;

public class QuarterlyBonusMain {

    public static void main(String[] args) {

        TeamLead lead = new TeamLead(50000);

        System.out.println("Base Salary: "
                + lead.getBaseSalary());

        System.out.println("Quarterly Bonus: "
                + lead.calculateQuarterlyBonus());

        System.out.println(
                TeamLead.getAuditIfApplicable(lead)
        );
    }
}