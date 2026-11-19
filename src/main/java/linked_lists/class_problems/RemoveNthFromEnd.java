package main.java.linked_lists.class_problems;
public class RemoveNthFromEnd {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    public static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node fast = dummy;
        Node slow = dummy;

        // Advance fast n + 1 steps so slow lands right before the target node
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}