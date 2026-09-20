package interfaces_and_abstraction.class_problems;

public class Textbook extends LibraryItem
        implements Renewable, Reservable {

    private String title;

    public Textbook(String itemId, String title) {
        super(itemId);
        this.title = title;
    }

    @Override
    public String getItemType() {
        return "Textbook";
    }

    @Override
    public String renew() {
        return "Renewed textbook: " + title;
    }

    @Override
    public String reserve() {
        return "Reserved textbook: " + title;
    }
}