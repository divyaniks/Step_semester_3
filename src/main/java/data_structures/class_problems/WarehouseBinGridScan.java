package main.java.data_structures.class_problems;
public class WarehouseBinGridScan {
    public static void warehouseSummary(int[][] grid) {
        long total = 0;
        int maxVal = -1;
        int maxRow = 0, maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                total += grid[r][c];
                // Strictly '>' ensures the first encountered max is kept on ties
                if (grid[r][c] > maxVal) {
                    maxVal = grid[r][c];
                    maxRow = r;
                    maxCol = c;
                }
            }
        }
        System.out.printf("total = %d, maxCoordinate = (%d, %d)%n", total, maxRow, maxCol);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };
        warehouseSummary(grid); // total = 49, maxCoordinate = (2, 1)
    }
}