package main.java.data_structures.assignment_problems;
import java.util.*;

public class NetBalancePeriodCounter {
    public static int countPeriods(int[] transactions, long k) {
        Map<Long, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0L, 1);

        long currentSum = 0;
        int count = 0;

        for (int tx : transactions) {
            currentSum += tx;
            count += prefixCount.getOrDefault(currentSum - k, 0);
            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPeriods(new int[]{3, 4, -7, 1, 3, 3, 1, -4}, 7)); // 4
        System.out.println(countPeriods(new int[]{1, 2, 3}, 10));                  // 0
    }
}