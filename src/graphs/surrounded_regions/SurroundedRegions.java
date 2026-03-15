
/**
 * Problem: Surrounded Regions (#130)
 * 
 * Key Insights: 
 * 1. Identify all the O's that cannot be flipped to X (cannot be captured). 
 *  a. If O is on the border, cannot be flipped to X 
 *  b. If O is adjacent to an O on the border, cannot be flipped to X 
 * 2. Flip all the O's that can be flipped to X (not on the border, not adjacent to a border O)
 * 3. Flip all the A's (O's that cannot be flipped to X) back to O's
 * 
 * Time Complexity: O(rows x cols)
 * 
 * Space Complexity: O(rows x cols)
 *
 */
public class SurroundedRegions {
 
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int rows = board.length; 
        int cols = board[0].length;

        // Identifying all the O's that cannot be flipped to X's (cannot be captured)
        // because they are at the border or adjancent to an O at the border
        for (int i = 0; i < rows; i++) {
            dfs(board, i, 0);
            dfs(board, i, cols - 1);
        }

        for (int j = 0; j < cols; j++) {
            dfs(board, 0, j);
            dfs(board, rows - 1, j);
        }

        // Flip all the O's (that can be flipped - not a border O or adjacent to a border O) to X's 
        // Flip all the A's (O's that cannot be flipped bc they are border O or adjacent to border O) back to O's
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        int rows = board.length; 
        int cols = board[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'A';
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
    }
}
