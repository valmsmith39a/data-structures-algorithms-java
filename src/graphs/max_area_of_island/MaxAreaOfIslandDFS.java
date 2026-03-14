/**
 * 695. Max Area of Island
 * Strategy: Interate through each cell, DFS, flip 1 to 0 to mark visited,
 * increment currentArea, dfs 4 directions
 * Time Complexity: O(r * c), r = rows, c = columns, visit each cell once
 * Space Complexity: O(r * c) from recursive call stack in worst case of all
 * land, O(1) if we don't count the call stack
 */
public class MaxAreaOfIslandDFS {

	private int currArea;

	public int maxAreaOfIsland(int[][] grid) {
		int maxArea = 0;

		if (grid == null || grid.length == 0) {
			return 0;
		}

		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				currArea = 0;
				dfs(grid, r, c);
				maxArea = Math.max(maxArea, currArea);
			}
		}
		return maxArea;
	}

	private void dfs(int[][] grid, int r, int c) {
		if (r < 0 || c < 0 || r > grid.length - 1 || c > grid[0].length - 1 || grid[r][c] == 0) {
			return;
		}

		grid[r][c] = 0;
		currArea += 1;

		dfs(grid, r + 1, c);
		dfs(grid, r - 1, c);
		dfs(grid, r, c + 1);
		dfs(grid, r, c - 1);
	}
}
