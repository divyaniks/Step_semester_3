package oop_and_collections.class_problems;
import java.util*;
public class ProductPriceFinder {
    private static int lowerBound(int[] prices, int target) {
        int low = 0, high = prices.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    private static int upperBound(int[] prices, int target) {
        int low = 0, high = prices.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    public static void findRangeCount(int[] prices, int minPrice, int maxPrice) {
        int lb = lowerBound(prices, minPrice);
        int ub = upperBound(prices, maxPrice);
        System.out.println("Lower bound index " + lb);
        System.out.println("Upper bound index " + ub);
        System.out.println("Count " + (ub - lb));
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        findRangeCount(prices, 150, 300);
    }
}