package main.java.oop_and_collections.assignment_problems;
public class BrowserHistory {
    static class Node {
        String url;
        Node prev, next;
        Node(String url) { this.url = url; }
    }

    private Node current;

    public BrowserHistory(String homepage) {
        this.current = new Node(homepage);
    }

    public void visit(String url) {
        Node nextNode = new Node(url);
        current.next = nextNode; // Severing old forward chain lets GC reclaim discarded nodes
        nextNode.prev = current;
        current = nextNode;
    }

    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }

    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }

    public static void main(String[] args) {
        BrowserHistory bh = new BrowserHistory("srm.edu");
        bh.visit("a.com");
        bh.visit("b.com");
        bh.visit("c.com");
        System.out.println(bh.back(1));    // b.com
        System.out.println(bh.back(1));    // a.com
        System.out.println(bh.forward(1)); // b.com
        bh.visit("d.com");
        System.out.println(bh.forward(2)); // d.com
        System.out.println(bh.back(2));    // a.com
        System.out.println(bh.back(7));    // srm.edu
    }
}