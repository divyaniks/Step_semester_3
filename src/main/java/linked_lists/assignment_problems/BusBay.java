package main.java.linked_lists.assignment_problems;
public class BusBay {
    private final int[] buffer;
    private final int capacity;
    private int front = 0;
    private int size = 0;

    public BusBay(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }

    public boolean insertFront(int bus) {
        if (isFull()) return false;
        front = (front - 1 + capacity) % capacity;
        buffer[front] = bus;
        size++;
        return true;
    }

    public boolean insertLast(int bus) {
        if (isFull()) return false;
        int rear = (front + size) % capacity;
        buffer[rear] = bus;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) return false;
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) return false;
        size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : buffer[front];
    }

    public int getRear() {
        if (isEmpty()) return -1;
        int rear = (front + size - 1) % capacity;
        return buffer[rear];
    }

    public static void main(String[] args) {
        BusBay b = new BusBay(3);
        System.out.println(b.insertLast(101));  // true
        System.out.println(b.insertLast(102));  // true
        System.out.println(b.insertFront(100)); // true
        System.out.println(b.insertFront(99));  // false
        System.out.println(b.getRear());        // 102
        System.out.println(b.isFull());         // true
        System.out.println(b.deleteLast());     // true
        System.out.println(b.insertFront(98));  // true
        System.out.println(b.getFront());       // 98
    }
}