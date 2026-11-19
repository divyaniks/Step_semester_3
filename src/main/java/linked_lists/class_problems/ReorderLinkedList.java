package main.java.linked_lists.class_problems;
public class ReorderLinkedList {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    public static void reorderList(Node head) {
        if (head == null || head.next == null) return;

        // 1. Find middle
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Split and reverse second half
        Node prev = null, curr = slow.next;
        slow.next = null;
        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // 3. Merge two halves alternately
        Node first = head, second = prev;
        while (second != null) {
            Node tmp1 = first.next;
            Node tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }
}