package Review_2024;

/**
 * Word Search #79
 * 
 * Time Complexity: O(M * N * 4 *L)
 * M: number of rows, N: number of columns
 * Iterate through each cell, do 4 searcher on each cell, go L characters deep
 * on each search
 * 
 * Space Complexity:
 * 
 */
public class WordSearch {
	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0 || word == null || word.length() == 0) {
			return false;
		}
		int rows = board.length;
		int cols = board[0].length;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == word.charAt(0)) {
					if (dfs(board, word, 0, i, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean dfs(char[][] board, String word, int index, int row, int col) {
		if (index == word.length()) {
			return true;
		}
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
				|| board[row][col] != word.charAt(index)) {
			return false;
		}
		char temp = board[row][col];
		board[row][col] = '#';
		boolean found = dfs(board, word, index + 1, row + 1, col)
				|| dfs(board, word, index + 1, row - 1, col)
				|| dfs(board, word, index + 1, row, col + 1)
				|| dfs(board, word, index + 1, row, col - 1);
		board[row][col] = temp;
		return found;
	}
}
