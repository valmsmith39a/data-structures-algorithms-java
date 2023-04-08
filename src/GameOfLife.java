class GameOfLife {
    /**
     * Problem Name: Game of Life
     * 
     * Problem Statement:
     * A board is an m x n grid of cells.
     * Each cell can be in 2 states: live (1) or dead(0)
     * 4 rules:
     * 1. Any live cell with < 2 live neighbors, dies, as if by under-population.
     * 2. Any live cell with 2 or 3 live neighbors, continues
     * 3. Any live cell with > 3 live neighbors, dies, as if by over-population.
     * 4. Any dead cell with 3 live neighbors, becomes a live cell as if by
     * reproduction.
     * Given current state of an m x n grid board, return the next state.
     * 
     * Constraints:
     * 1. m == board.length
     * 2. n == board[i].length
     * 3. 1 <= m, n <= 25
     * 4. board[i][j] is 0 or 1
     * 
     * Key insights:
     * 1. Conventional method is to use 2 board states for current and next states.
     * But can update board in-place by representing dies, but was once alive as -1
     * and represent alive, but was once dead as 2.
     * 2. Iterate through neighbors of a cell using a neighbors array of [ 0, 1, -1
     * ],
     * because each neighbor is (row - 1, col, row + 1, col, row - 1, col - 1, etc)
     * Can think of a sub-matrix that is 3 x 3. Skip over if neighbors_array[i] == 0
     * and neighbors_array[j] == 0
     *
     * Complexity Analysis:
     * Time Complexity: O(mn) time, because iterate through the m x n grid board
     * Space Complexity: O(1) space, because updating board in place
     * 
     * History:
     * In 1970, British mathematician John Conway devised the Game of Life, a
     * cellular automaton - a discrete model of computation consisting of a grid of
     * cells, each one
     * in a finite number of states (e.g. "on" or "off"). The next state of each
     * cell a function of the states
     * of the neighboring cells. Conway's Game of Life is a zero-player game,
     * meaning it's evolution
     * is determined by it's initial state and does not need further input.
     */
    public int[][] updateBoard(int[][] board) {
        int[] neighbors = { 0, 1, -1 };
        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int neighbor_row = row + neighbors[i];
                            int neighbor_col = col + neighbors[j];
                            // Math.abs because if cell was original alive but is now dead, use -1
                            if ((neighbor_row >= 0 && neighbor_row < rows)
                                    && (neighbor_col >= 0 && neighbor_col < cols)
                                    /*
                                     * Need to keep the previous state when counting live neighbors.
                                     * To represent cell was alive, but is now dead, use -1.
                                     * Math.abs(-1) = 1, which tells you that the cell was previously alive.
                                     * So you can increase the count of liveNeighbors.
                                     */
                                    && Math.abs(board[neighbor_row][neighbor_col]) == 1) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }
                // Rule 1: if live cell has < 2 live neighbors, dies
                // Rule 3: if live cell has > 3 live neighbors, dies
                if (board[row][col] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = -1;
                }
                // Rule 4: if dead cell has 3 live neighbors, becomes a live cell
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    // 2 means cell was dead, but is now alive
                    board[row][col] = 2;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
        return board;
    }

    public void printBoard(int[][] board) {
        for (int m = 0; m < board.length; m++) {
            for (int n = 0; n < board[0].length; n++) {
                System.out.print(board[m][n]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                { 0, 1, 0 },
                { 0, 0, 1 },
                { 1, 1, 1 },
                { 0, 0, 0 },
                { 0, 0, 0 }
        };
        GameOfLife life = new GameOfLife();
        System.out.println("Current board state: ");
        life.printBoard(board);
        life.updateBoard(board);
        /*
         * 000
         * 101
         * 011
         * 010
         * 000
         */
        System.out.println("Next board state: ");
        life.printBoard(board);
    }
}

