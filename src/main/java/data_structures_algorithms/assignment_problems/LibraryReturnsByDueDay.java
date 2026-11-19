package main.java.data_structures_algorithms.assignment_problems;
public class LibraryReturnsByDueDay {
    static class Node {
        String title;
        int day;
        Node next;
        Node(String title, int day) {
            this.title = title;
            this.day = day;
        }
    }

    public static Node sortByDueDay(Node head) {
        if (head == null || head.next == null) return head;

        Node mid = getMiddle(head);
        Node rightHead = mid.next;
        mid.next = null;

        Node left = sortByDueDay(head);
        Node right = sortByDueDay(rightHead);
        return merge(left, right);
    }

    private static Node getMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static Node merge(Node a, Node b) {
        Node dummy = new Node("", 0);
        Node tail = dummy;
        while (a != null && b != null) {
            // '<=' preserves drop order on ties (stable)
            if (a.day <= b.day) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }
}