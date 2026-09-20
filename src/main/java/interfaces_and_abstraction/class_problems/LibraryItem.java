package interfaces_and_abstraction.class_problems;

public abstract class LibraryItem {

    private final String itemId;

    public LibraryItem(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public abstract String getItemType();
}