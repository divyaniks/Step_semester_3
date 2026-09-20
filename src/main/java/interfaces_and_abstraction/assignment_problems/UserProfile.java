package interfaces_and_abstraction.assignment_problems;

public class UserProfile implements Exportable {

    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        return "User profile exported: " + username;
    }
}