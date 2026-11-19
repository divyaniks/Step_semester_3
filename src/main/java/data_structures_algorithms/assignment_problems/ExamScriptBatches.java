public class ExamScriptBatches {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static Node reverseInGroups(Node head, int k) {
        if (head == null || k <= 1) return head;

        Node dummy = new Node(0);
        dummy.next = head;
        Node prevGroupTail = dummy;

        while (true) {
            Node kth = prevGroupTail;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) break; // Fewer than k nodes left

            Node nextGroupHead = kth.next;
            Node prev = nextGroupHead;
            Node curr = prevGroupTail.next;
            Node groupTail = curr;

            while (curr != nextGroupHead) {
                Node temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            prevGroupTail.next = kth;
            prevGroupTail = groupTail;
        }
        return dummy.next;
    }
}