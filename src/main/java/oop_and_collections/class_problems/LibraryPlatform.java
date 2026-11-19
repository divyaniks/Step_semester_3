package main.java.oop_and_collections.class_problems;
interface Reservable { void reserve(String memberId); }
interface Downloadable { void download(); }

abstract class Resource implements Comparable<Resource> {
    protected final String id;
    public Resource(String id) { this.id = id; }
    public String getId() { return id; }
    public int compareTo(Resource o) { return this.id.compareTo(o.id); }
}

class Book extends Resource implements Reservable {
    public Book(String id) { super(id); }
    public void reserve(String memberId) { System.out.println(id + " reserved for " + memberId); }
}

class EBook extends Resource implements Downloadable {
    public EBook(String id) { super(id); }
    public void download() { System.out.println(id + " downloaded"); }
}

public class LibraryPlatform {
    private final Resource[] resources = new Resource[100];
    private int size = 0;

    private int binarySearch(String id) {
        int low = 0, high = size - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = resources[mid].getId().compareTo(id);
            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -(low + 1); // insertion point
    }

    public void addResource(Resource r) {
        int idx = binarySearch(r.getId());
        if (idx >= 0) {
            System.out.println("duplicate rejected");
            return;
        }
        int insertPos = -(idx + 1);
        for (int i = size; i > insertPos; i--) {
            resources[i] = resources[i - 1];
        }
        resources[insertPos] = r;
        size++;
        System.out.println(r.getId() + " added");
    }

    public void reserve(String id, String memberId) {
        int idx = binarySearch(id);
        if (idx < 0) return;
        if (resources[idx] instanceof Reservable) ((Reservable) resources[idx]).reserve(memberId);
        else System.out.println(id + " rejected: reserve unsupported");
    }

    public void download(String id) {
        int idx = binarySearch(id);
        if (idx < 0) return;
        if (resources[idx] instanceof Downloadable) ((Downloadable) resources[idx]).download();
        else System.out.println(id + " rejected: download unsupported");
    }

    public void find(String id) {
        int idx = binarySearch(id);
        if (idx >= 0) System.out.println(id + " found at index " + idx);
        else System.out.println(id + " not found");
    }

    public static void main(String[] args) {
        LibraryPlatform lib = new LibraryPlatform();
        lib.addResource(new Book("B1"));
        lib.addResource(new Book("B1"));
        lib.addResource(new EBook("E1"));
        lib.reserve("B1", "M1");
        lib.download("B1");
        lib.download("E1");
        lib.find("E1");
    }
}