package interfaces_and_abstraction.class_problems;

public class Magazine extends LibraryItem
        implements Renewable {

    private String title;

    public Magazine(String itemId, String title) {
        super(itemId);
        this.title = title;
    }

    @Override
    public String getItemType() {
        return "Magazine";
    }

    @Override
    public String renew() {
        return "Renewed magazine: " + title;
    }
}