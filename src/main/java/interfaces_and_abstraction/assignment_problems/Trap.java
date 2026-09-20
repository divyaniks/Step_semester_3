package interfaces_and_abstraction.assignment_problems;

public class Trap implements Defendable {

    private String trapType;

    public Trap(String trapType) {
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}