enum LeaveStatus { PENDING, APPROVED, REJECTED }

abstract class Employee {
    private final String name;
    public Employee(String name) { this.name = name; }
    public String getName() { return name; }
    public abstract int getMaxLeaveDays();
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) { super(name); }
    public int getMaxLeaveDays() { return 20; }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) { super(name); }
    public int getMaxLeaveDays() { return 10; }
}

class LeaveRequest {
    private final Employee employee;
    private final String dateRange;
    private LeaveStatus status = LeaveStatus.PENDING;

    public LeaveRequest(Employee employee, String dateRange) {
        this.employee = employee;
        this.dateRange = dateRange;
        System.out.printf("Leave request submitted by %s for %s. Status: %s.%n",
                employee.getName(), dateRange, status);
    }

    public void approve() {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot approve: Request is already finalized.");
            return;
        }
        status = LeaveStatus.APPROVED;
        System.out.printf("Leave request for %s approved. Status: %s.%n", employee.getName(), status);
    }

    public void revertToPending() {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change status: Approved request cannot revert to Pending.");
        }
    }
}

public class EmployeeLeaveManagement {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John Doe");
        Employee jane = new PartTimeEmployee("Jane Smith");

        LeaveRequest req1 = new LeaveRequest(john, "2024-10-10 to 2024-10-12");
        req1.approve();
        LeaveRequest req2 = new LeaveRequest(jane, "2024-11-01 to 2024-11-05");
        req1.revertToPending();
    }
}