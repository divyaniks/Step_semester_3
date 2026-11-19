package main.java.data_structures_algorithms.assignment_problems;
import java.util.*;

public class RecentCache {
    static class Node {
        int key;
        String page;
        Node prev, next;
        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(-1, ""); // Dummy MRU boundary
    private final Node tail = new Node(-1, ""); // Dummy LRU boundary

    public RecentCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public String get(int key) {
        Node node = map.get(key);
        if (node == null) {
            System.out.println("get(" + key + ") -> -1");
            return "-1";
        }
        removeNode(node);
        insertAtFront(node);
        System.out.println("get(" + key + ") -> " + node.page);
        return node.page;
    }

    public void put(int key, String page) {
        if (map.containsKey(key)) {
            Node existing = map.get(key);
            existing.page = page;
            removeNode(existing);
            insertAtFront(existing);
            System.out.printf("put(%d,%s) updated%n", key, page);
            return;
        }

        if (map.size() == capacity) {
            Node lru = tail.prev;
            removeNode(lru);
            map.remove(lru.key);
            System.out.printf("put(%d,%s) evicted %d%n", key, page, lru.key);
        } else {
            System.out.printf("put(%d,%s)%n", key, page);
        }

        Node newNode = new Node(key, page);
        insertAtFront(newNode);
        map.put(key, newNode);
    }

    public void printCache() {
        List<String> items = new ArrayList<>();
        Node curr = head.next;
        while (curr != tail) {
            items.add(curr.key + "=" + curr.page);
            curr = curr.next;
        }
        System.out.println("Cache (most recent first): " + String.join(", ", items));
    }
}