public class NumberOfIslands {
    /**
     * Number of Islands
     * 
     * Problem:
     * Given an m x n 2D binary grid with a map of '1's (land) and '0's (water),
     * return the number of islands.
     * 
     * An island is surrounded by water and is formed by connecting adjacent lands
     * horizontally or vertically.
     * 
     * Assume all 4 edges of grid are surrounded by water.
     * 
     * Example:
     * 
     * Input: grid = [
     * ["1","1","0","0","0"],
     * ["1","1","0","0","0"],
     * ["0","0","1","0","0"],
     * ["0","0","0","1","1"]
     * ]
     * 
     * Output: 3
     * 
     * Problem Summary:
     * 1. Given a 2 dimensional matrix with '1's and '0's
     * 2. Cluster of '1's (adjacent to each other) are islands.
     * 3. Find the number of islands (cluster of '1's).
     * 
     * Implementation:
     * 1. Traverse through each element of the matrix using a double for loop
     * 2. If encounter a '1', increment numberOfIslands counter, call depth first
     * search on that element, flip '1' to '0',
     * and call DFS on each adjacent element as well (up, down, left, right).
     * This process will flip all the '1's to '0's for a cluster of '1's.
     * 3. When return to initial double for loop, the entire cluster will be flipped
     * from '1' to '0',
     * and that counts as 1 island.
     **/

    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        // Flip all adjacent 1s to 0s.
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    int getNumberOfIslands(char[][] grid) {
        // no grid or empty grid
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int numOfIslands = 0;

        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                // Identify an island
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    numOfIslands++;
                }
            }
        }
        return numOfIslands;
    }

    public static void main(String args[]) {
        char[][] grid1 = new char[][] { {} };
        char[] row1 = new char[] { '1', '1', '1', '1', '0' };
        char[] row2 = new char[] { '1', '1', '0', '1', '0' };
        char[] row3 = new char[] { '1', '1', '0', '0', '0' };
        char[] row4 = new char[] { '0', '0', '0', '0', '0' };
        grid1 = new char[][] { row1, row2, row3, row4 };

        System.out.println("Number of Islands");
        System.out.println("Grid 1: ");

        for (int i = 0; i < 4; i++) {
            System.out.println(grid1[i]);
        }

        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int totalIslands = numberOfIslands.getNumberOfIslands(grid1);
        // Expected: 1
        System.out.println("Total number of islands is: " + totalIslands);

        char[][] grid2 = new char[][] { {} };
        char[] grid2row1 = new char[] { '1', '1', '0', '0', '0' };
        char[] grid2row2 = new char[] { '1', '1', '0', '0', '0' };
        char[] grid2row3 = new char[] { '0', '0', '1', '0', '0' };
        char[] grid2row4 = new char[] { '0', '0', '0', '1', '1' };
        grid2 = new char[][] { grid2row1, grid2row2, grid2row3, grid2row4 };

        System.out.println("Number of Islands");
        System.out.println("Grid 2: ");

        for (int i = 0; i < 4; i++) {
            System.out.println(grid2[i]);
        }

        int totalIslandsGrid2 = numberOfIslands.getNumberOfIslands(grid2);
        // Expected: 3
        System.out.println("Total number of islands is: " + totalIslandsGrid2);
    }
}
