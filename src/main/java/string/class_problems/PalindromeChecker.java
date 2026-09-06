package string.class_problems;

import java.util.Scanner;

public class PalindromeChecker {

    // Iterative approach
    static boolean iterativeCheck(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // Recursive approach
    static boolean recursiveCheck(String str, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }

        return recursiveCheck(str, left + 1, right - 1);
    }

    // Array reversal approach
    static boolean arrayReverseCheck(String str) {
        char[] original = str.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        System.out.println("\nIterative Approach: "
                + iterativeCheck(str));

        System.out.println("Recursive Approach: "
                + recursiveCheck(str, 0, str.length() - 1));

        System.out.println("Array Reversal Approach: "
                + arrayReverseCheck(str));

        sc.close();
    }
}