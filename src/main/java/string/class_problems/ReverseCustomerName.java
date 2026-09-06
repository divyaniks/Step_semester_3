package string.class_problems;

import java.util.Scanner;

public class ReverseCustomerName {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        String reversed = "";

        for (int i = name.length() - 1; i >= 0; i--) {
            reversed += name.charAt(i);
        }

        System.out.println("Reversed name: " + reversed);

        sc.close();
    }
}