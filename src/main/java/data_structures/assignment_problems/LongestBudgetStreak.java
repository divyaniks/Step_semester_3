package main.java.data_structures.assignment_problems;
public class LongestBudgetStreak {
    public static int[] longestStreak(int[] costs, long budget) {
        int left = 0;
        long currentSum = 0;
        int maxLen = 0;
        int bestStart = -1;

        for (int right = 0; right < costs.length; right++) {
            currentSum += costs[right];
            while (currentSum > budget && left <= right) {
                currentSum -= costs[left++];
            }
            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
                bestStart = left;
            }
        }
        return maxLen == 0 ? new int[]{0, -1} : new int[]{maxLen, bestStart};
    }

    public static void main(String[] args) {
        int[] res1 = longestStreak(new int[]{4, 2, 1, 7, 3, 1, 2, 1, 5}, 8);
        System.out.printf("(%d, %d)%n", res1[0], res1[1]); // (4, 4)

        int[] res2 = longestStreak(new int[]{9, 10}, 8);
        System.out.printf("(%d, %d)%n", res2[0], res2[1]); // (0, -1)
    }
}