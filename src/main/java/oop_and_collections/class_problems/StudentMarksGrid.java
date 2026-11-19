package main.java.oop_and_collections.class_problems;
import java.util.*;

public class StudentMarksGrid {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};
        String[] students = {"Anu", "Ravi", "Meena"};
        // null indicates missing mark
        Integer[][] marks = {
            {80, 90, 70},
            {60, 85, null},
            {95, 75, 88}
        };

        List<String> totals = new ArrayList<>();
        for (int i = 0; i < students.length; i++) {
            int sum = 0;
            for (Integer m : marks[i]) {
                if (m != null) sum += m;
            }
            totals.add(students[i] + " " + sum);
        }

        List<String> toppers = new ArrayList<>();
        for (int j = 0; j < subjects.length; j++) {
            int maxMark = -1;
            String topperName = "";
            for (int i = 0; i < students.length; i++) {
                if (marks[i][j] != null && marks[i][j] > maxMark) {
                    maxMark = marks[i][j];
                    topperName = students[i];
                }
            }
            toppers.add(subjects[j] + " " + topperName + " " + maxMark);
        }

        System.out.println("Totals " + String.join(", ", totals));
        System.out.println("toppers " + String.join(", ", toppers));
    }
}