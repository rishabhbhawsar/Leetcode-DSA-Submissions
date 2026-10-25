class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int closedIslands = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    if (isClosed(grid, i, j, rows, cols))
                        closedIslands++;
                }
            }
        }
        return closedIslands;
    }
    private boolean isClosed(int[][] grid, int i, int j, int rows, int cols) {
        if (grid[i][j] == 1) {
            return true;
        }
        if (isOnPerimeter(i, j, rows, cols)) {
            return false;
        }

        grid[i][j] = 1;

        boolean left = isClosed(grid, i, j - 1, rows, cols);
        boolean right = isClosed(grid, i, j + 1, rows, cols);
        boolean up = isClosed(grid, i - 1, j, rows, cols);
        boolean down = isClosed(grid, i + 1, j, rows, cols);

        return left && right && up && down;
    }
    private boolean isOnPerimeter(int i, int j, int rows, int cols) {
        return i == 0 || j == 0 || i == rows - 1 || j == cols - 1;
    }
}