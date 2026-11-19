package class_methods_and_uml.assignment_problems;
import java.util.*;

// 1. Pluggable Credit Policy
interface CreditPolicy {
    String getCategoryName();
    int getMaxCredits();
}

class RegularPolicy implements CreditPolicy {
    public String getCategoryName() { return "Regular"; }
    public int getMaxCredits() { return 24; }
}

class HonorsPolicy implements CreditPolicy {
    public String getCategoryName() { return "Honors"; }
    public int getMaxCredits() { return 28; }
}

class ExchangePolicy implements CreditPolicy {
    public String getCategoryName() { return "Exchange"; }
    public int getMaxCredits() { return 20; }
}

// 2. Domain Classes
class Student {
    private final String name;
    private final CreditPolicy policy;
    private int currentCredits;

    public Student(String name, CreditPolicy policy, int currentCredits) {
        this.name = name;
        this.policy = policy;
        this.currentCredits = currentCredits;
    }

    public String getName() { return name; }
    public CreditPolicy getPolicy() { return policy; }
    public int getCurrentCredits() { return currentCredits; }

    public boolean canTakeCredits(int credits) {
        return (currentCredits + credits) <= policy.getMaxCredits();
    }

    public void addCredits(int credits) { currentCredits += credits; }
    public void removeCredits(int credits) { currentCredits -= credits; }
}

class Elective {
    private final String title;
    private final int credits;
    private final int capacity;
    private final Set<Student> enrolled = new LinkedHashSet<>();
    private final Queue<Student> waitlist = new LinkedList<>();

    public Elective(String title, int credits, int capacity) {
        this.title = title;
        this.credits = credits;
        this.capacity = capacity;
    }

    public void enroll(Student student) {
        if (enrolled.contains(student) || waitlist.contains(student)) {
            System.out.printf("Enrollment failed: %s is already enrolled or waitlisted.%n", student.getName());
            return;
        }
        // Check credit limit BEFORE seat availability
        if (!student.canTakeCredits(credits)) {
            System.out.printf("Enrollment failed: %s would exceed the %s credit limit (%d/%d).%n",
                    student.getName(), student.getPolicy().getCategoryName(),
                    student.getCurrentCredits() + credits, student.getPolicy().getMaxCredits());
            return;
        }
        if (enrolled.size() < capacity) {
            enrolled.add(student);
            student.addCredits(credits);
            System.out.printf("%s enrolled in %s (credits: %d/%d).%n",
                    student.getName(), title, student.getCurrentCredits(), student.getPolicy().getMaxCredits());
        } else {
            waitlist.offer(student);
            System.out.printf("%s is full. %s added to waitlist (position %d).%n",
                    title, student.getName(), waitlist.size());
        }
    }

    public void drop(Student student) {
        if (!enrolled.remove(student)) {
            System.out.printf("Drop failed: %s is not enrolled in %s.%n", student.getName(), title);
            return;
        }
        student.removeCredits(credits);
        System.out.printf("%s dropped %s (credits: %d/%d).%n",
                student.getName(), title, student.getCurrentCredits(), student.getPolicy().getMaxCredits());

        // Atomic promotion from waitlist
        while (!waitlist.isEmpty() && enrolled.size() < capacity) {
            Student next = waitlist.poll();
            if (next.canTakeCredits(credits)) {
                enrolled.add(next);
                next.addCredits(credits);
                System.out.printf("%s promoted from waitlist and enrolled in %s (credits: %d/%d).%n",
                        next.getName(), title, next.getCurrentCredits(), next.getPolicy().getMaxCredits());
                break;
            }
        }
    }
}

public class ElectiveSeatRush {
    public static void main(String[] args) {
        Elective cloud = new Elective("Cloud Computing", 4, 2);
        Student asha = new Student("Asha", new RegularPolicy(), 20);
        Student ravi = new Student("Ravi", new HonorsPolicy(), 22);
        Student neha = new Student("Neha", new ExchangePolicy(), 12);
        Student kiran = new Student("Kiran", new RegularPolicy(), 22);

        cloud.enroll(asha);
        cloud.enroll(ravi);
        cloud.enroll(neha);
        cloud.enroll(kiran);
        cloud.drop(asha);
    }
}