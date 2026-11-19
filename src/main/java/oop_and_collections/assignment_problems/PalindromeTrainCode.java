package main.java.oop_and_collections.assignment_problems;
public class PalindromeTrainCode {
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

        // 1. Find middle (end of first half)
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse second half
        Node secondHalfHead = reverse(slow.next);

        // 3. Compare halves
        Node p1 = head, p2 = secondHalfHead;
        boolean result = true;
        while (p2 != null) {
            if (p1.data != p2.data) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4. Restore original list
        slow.next = reverse(secondHalfHead);
        return result;
    }
}