package main.java.data_structures.class_problems;
public class MaxSumSubarrayFixedK {
    public static int maxSumSubarray(int[] sales, int k) {
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += sales[i];
        }
        int maxSum = windowSum;

        for (int i = k; i < sales.length; i++) {
            windowSum += sales[i] - sales[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSumSubarray(new int[]{2, 1, 5, 1, 3, 2}, 3)); // 9
    }
}