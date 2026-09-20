package interfaces_and_abstraction.class_problems;

public class DigitalPass implements Renewable {

    private String passId;

    public DigitalPass(String passId) {
        this.passId = passId;
    }

    @Override
    public String renew() {
        return "Renewed digital pass: " + passId;
    }
}