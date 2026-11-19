package main.java.linked_lists.assignment_problems;
import java.util.*;

public class FireDrillExits {
    public static int[][] exitDistance(char[][] plan) {
        int rows = plan.length;
        int cols = plan.length;
        int[][] dist = new int[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int r = 0; r < rows; r++) {
            Arrays.fill(dist[r], -1);
            for (int c = 0; c < cols; c++) {
                if (plan[r][c] == 'E') {
                    dist[r][c] = 0;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr, c = curr;

            for (int[] d : dirs) {
                int nr = r + d;
                int nc = c + d;
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && plan[nr][nc] == '.' && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };
        int[][] res = exitDistance(plan);
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }
}