package main.java.linked_lists.assignment_problems;
import java.util.*;

public class QuizScoringFormula {
    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private static void applyTopOp(Deque<Integer> values, Deque<Character> ops) {
        int b = values.pop();
        int a = values.pop();
        char op = ops.pop();
        switch (op) {
            case '+': values.push(a + b); break;
            case '-': values.push(a - b); break;
            case '*': values.push(a * b); break;
            case '/': values.push(a / b); break;
        }
    }

    public static int evaluate(String expr) {
        Deque<Integer> values = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        int n = expr.length();

        for (int i = 0; i < n; i++) {
            char c = expr.charAt(i);
            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                int num = 0;
                while (i < n && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                i--;
                values.push(num);
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    applyTopOp(values, ops);
                }
                ops.pop(); // Discard '('
            } else {
                while (!ops.isEmpty() && ops.peek() != '(' && precedence(ops.peek()) >= precedence(c)) {
                    applyTopOp(values, ops);
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            applyTopOp(values, ops);
        }
        return values.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));   // 12
        System.out.println(evaluate("(8 - 2) * (1 + 2) / 4"));  // 4
    }
}