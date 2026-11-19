package main.java.oop_and_collections.assignment_problems;
public class PolynomialCalculator {
    static class Node {
        int coeff, exp;
        Node next;
        Node(int coeff, int exp) {
            this.coeff = coeff;
            this.exp = exp;
        }
    }

    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exp > q.exp) {
                tail.next = new Node(p.coeff, p.exp);
                tail = tail.next;
                p = p.next;
            } else if (q.exp > p.exp) {
                tail.next = new Node(q.coeff, q.exp);
                tail = tail.next;
                q = q.next;
            } else {
                int sum = p.coeff + q.coeff;
                if (sum != 0) {
                    tail.next = new Node(sum, p.exp);
                    tail = tail.next;
                }
                p = p.next;
                q = q.next;
            }
        }
        while (p != null) {
            tail.next = new Node(p.coeff, p.exp);
            tail = tail.next;
            p = p.next;
        }
        while (q != null) {
            tail.next = new Node(q.coeff, q.exp);
            tail = tail.next;
            q = q.next;
        }
        return dummy.next;
    }

    public static void printPoly(Node head) {
        if (head == null) { System.out.println("0"); return; }
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            if (curr != head && curr.coeff > 0) sb.append(" + ");
            else if (curr.coeff < 0) sb.append(curr == head ? "-" : " - ");

            int absC = Math.abs(curr.coeff);
            if (curr.exp == 0) sb.append(absC);
            else if (curr.exp == 1) sb.append(absC == 1 ? "x" : absC + "x");
            else sb.append(absC == 1 ? "x^" + curr.exp : absC + "x^" + curr.exp);
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }
}