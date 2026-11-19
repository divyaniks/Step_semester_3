package main.java.data_structures_algorithms.assignment_problems;
public class DeliveryRouteLoopDetector {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static String detectLoop(Node head) {
        Node slow = head, fast = head;
        boolean hasLoop = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) return "Loop: no";

        // Count loop length
        int length = 1;
        Node curr = slow.next;
        while (curr != slow) {
            length++;
            curr = curr.next;
        }

        // Find loop start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return String.format("Loop: yes, starts at stop %d, length %d", slow.data, length);
    }
}