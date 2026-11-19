package main.java.data_structures_algorithms.class_problems;
import java.util.*;

public class PlacementDriveShortlist {
    static class Candidate {
        final String name;
        final int total;
        final int bestGain;
        final int startTest; // 1-based
        final int endTest;   // 1-based

        Candidate(String name, int[] scores) {
            this.name = name;
            int sum = 0;
            for (int s : scores) sum += s;
            this.total = sum;

            // Maximum subarray sum (Kadane's algorithm) on weekly deltas
            int maxGain = Integer.MIN_VALUE;
            int currGain = 0;
            int currStart = 1;
            int bestS = 1, bestE = 2;

            for (int i = 1; i < scores.length; i++) {
                int diff = scores[i] - scores[i - 1];
                if (currGain + diff < diff) {
                    currGain = diff;
                    currStart = i; // delta between test i and test i+1 starts at test i
                } else {
                    currGain += diff;
                }
                // Strict '>' keeps earliest run on ties
                if (currGain > maxGain) {
                    maxGain = currGain;
                    bestS = currStart;
                    bestE = i + 1;
                }
            }
            this.bestGain = maxGain;
            this.startTest = bestS;
            this.endTest = bestE;
        }
    }

    // Stable Merge Sort (descending by total)
    public static void mergeSort(Candidate[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Candidate[] arr, int left, int mid, int right) {
        Candidate[] temp = new Candidate[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            // '>=' preserves registration order on ties (stability)
            if (arr[i].total >= arr[j].total) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    // Binary search on descending-sorted array for first index with total < cutoff
    public static int countCutoff(Candidate[] arr, int cutoff) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].total >= cutoff) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // Two pointers on descending-sorted array
    public static String findPair(Candidate[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left].total + arr[right].total;
            if (sum == target) return arr[left].name + " + " + arr[right].name;
            else if (sum > target) left++;
            else right--;
        }
        return "no pair";
    }

    public static void main(String[] args) {
        Candidate[] list = {
            new Candidate("Asha",  new int[]{60, 55, 70, 72, 68, 80}),
            new Candidate("Ravi",  new int[]{70, 72, 65, 60, 75, 78}),
            new Candidate("Neha",  new int[]{50, 65, 62, 70, 74, 84}),
            new Candidate("Kiran", new int[]{80, 70, 66, 72, 70, 60}),
            new Candidate("Mohan", new int[]{55, 60, 58, 64, 70, 73})
        };

        mergeSort(list, 0, list.length - 1);
        System.out.println("Shortlist:");
        for (int i = 0; i < list.length; i++) {
            Candidate c = list[i];
            System.out.printf("%d. %s %d | best climb %+d (test %d to %d)%n",
                    i + 1, c.name, c.total, c.bestGain, c.startTest, c.endTest);
        }
        System.out.println("CUTOFF 405: " + countCutoff(list, 405) + " candidates");
        System.out.println("CUTOFF 410: " + countCutoff(list, 410) + " candidates");
        System.out.println("PAIR 785: " + findPair(list, 785));
        System.out.println("PAIR 900: " + findPair(list, 900));
    }
}