package main.java.data_structures_algorithms.assignment_problems;
public class TokenMirrorCheck {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private static Node reverse(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverse(slow.next);
        Node p1 = head, p2 = secondHalf;
        boolean ok = true;

        while (p2 != null) {
            if (p1.data != p2.data) {
                ok = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        slow.next = reverse(secondHalf); // Restore list
        return ok;
    }
}