package main.java.linked_lists.class_problems;
public class FindIntersectionNode {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    private static int getLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public static Node findIntersection(Node a, Node b) {
        int lenA = getLength(a);
        int lenB = getLength(b);

        while (lenA > lenB) {
            a = a.next;
            lenA--;
        }
        while (lenB > lenA) {
            b = b.next;
            lenB--;
        }

        while (a != null && b != null) {
            if (a == b) return a; // Compare node identity, not value
            a = a.next;
            b = b.next;
        }
        return null;
    }
}