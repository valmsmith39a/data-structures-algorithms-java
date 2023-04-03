import java.util.*;
/**
 * Problem: 417. Pacific Atlantic Water Flow
 * 
 * Description: 
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * 
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * 
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * 
 * Key Insights:
 * 1. Depth-first search to find all the cells that can flow to the Pacific ocean and all the cells that can flow to the Atlantic ocean.
 * 2. Start from cells that border Pacific and Atlantic oceans and do a depth-first search to find all the cells that can flow to the ocean.
 * 
 * Time Complexity: O(mn), because we visit each cell once.
 * Space Complexity: O(mn), because we use a boolean matrix to keep track of visited cells.
 */
public class PacificAtlanticWaterflow {

     public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int ROWS = heights.length;
        int COLS = heights[0].length;
        boolean[][] p = new boolean[ROWS][COLS];
        boolean[][] a = new boolean[ROWS][COLS];
        List<List<Integer>> res = new ArrayList<>();

        for (int r = 0; r < ROWS; r++) {
            dfs(r, 0, p, heights[r][0], heights);
            dfs(r, COLS - 1, a, heights[r][COLS - 1], heights);
        }
        
        for (int c = 0; c < COLS; c++) {
            dfs(0, c, p, heights[0][c], heights);
            dfs(ROWS - 1, c, a, heights[ROWS - 1][c], heights);
        }

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (p[r][c] && a[r][c]) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }
        return res;
    }

    private void dfs(int r, int c, boolean[][] visited, int prevHeight, int[][] heights) {
        if (r < 0 || c < 0 || r >= heights.length || c >= heights[0].length || visited[r][c] || heights[r][c] < prevHeight) {
            return;
        }
        visited[r][c] = true;
        dfs(r - 1, c, visited, heights[r][c], heights);
        dfs(r, c + 1, visited, heights[r][c], heights);
        dfs(r + 1, c, visited, heights[r][c], heights);
        dfs(r, c - 1, visited, heights[r][c], heights);
    }

    public static void main(String[] args) {
        int[][] heights = new int[][]{
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };
        PacificAtlanticWaterflow p = new PacificAtlanticWaterflow();
        List<List<Integer>> res = p.pacificAtlantic(heights);
        // Expected: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
    
}
