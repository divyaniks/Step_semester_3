package interfaces_and_abstraction.class_problems;

public abstract class StaffMember {

    private double baseSalary;

    public StaffMember() {
        this(0.0);
    }

    public StaffMember(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double calculateQuarterlyBonus();
}