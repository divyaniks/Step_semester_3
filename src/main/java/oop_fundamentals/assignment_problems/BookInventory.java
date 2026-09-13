package oop_fundamentals.assignment_problems;

import java.util.Scanner;

public class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int total = scanner.nextInt();
            try {
                BookInventory b = new BookInventory(total);
                int actions = scanner.nextInt();
                for (int i = 0; i < actions; i++) {
                    String action = scanner.next();
                    if ("out".equalsIgnoreCase(action)) {
                        b.checkOut();
                    } else if ("in".equalsIgnoreCase(action)) {
                        b.checkIn();
                    }
                }
                System.out.println(b.getCopiesAvailable());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}