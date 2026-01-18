public class LongestIncreasingPath {
	private int m, n;
	private int[][] grid;
	private int[][] memo;
	private final int[][] DIRS = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public int longestIncreasingPath(int[][] matrix) {
		int best = 0;
		m = matrix.length;
		n = matrix[0].length;
		grid = matrix;
		memo = new int[m][n];

		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				best = Math.max(best, dfs(r, c));
			}
		}

		return best;
	}

	private int dfs(int r, int c) {
		if (memo[r][c] != 0)
			return memo[r][c];

		int bestHere = 1;
		for (int[] d : DIRS) {
			int nr = r + d[0], nc = c + d[1];

			if (nr < 0 || nr >= m || nc < 0 || nc >= n)
				continue;
			if (grid[nr][nc] <= grid[r][c])
				continue;

			bestHere = Math.max(bestHere, 1 + dfs(nr, nc));
		}

		memo[r][c] = bestHere;

		return bestHere;
	}

}
