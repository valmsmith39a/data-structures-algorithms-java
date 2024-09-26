/**
 * Key: Use Depth First Search (DFS), flip the 1s to 0s
 * 
 * Time complexity: O(r * c), because visit each cell once 
 * Space complexity: O(r * c), in worst case, recursive call for each cell because they are all 1s 
 * 
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    count++;
                }
            }
        } 
        return count;
    }

    public void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
    }
}
