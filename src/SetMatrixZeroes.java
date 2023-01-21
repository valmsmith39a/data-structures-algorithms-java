import java.util.*;

/**
 * Problem: 
 * 
 * Given m x n matrix, if element is 0, set entire row and column to 0's
 *
 * Must do it in place.
 *
 * Key insight: 
 *
 * 1. Use 1 row and 1 column to determine if whole row / col should be 0s.
 * 2. use first row and first column as the row and column and declare 1 
 * extra cell to represent the first cell of the first column (determins if rows should be zeroed out).
 *
 * Time Complexity: O(mn) time
 * Space Complexity: O(1) space
 */
public class SetMatrixZeroes {
	
	public void setZeroes(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean rowZero = false;

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (matrix[r][c] == 0) {
					matrix[0][c] = 0;
					if (r > 0) {
						matrix[r][0] = 0;
					} else {
						rowZero = true;
					}
				}
			}
		}
		for (int r = 1; r < rows; r++) {
			for (int c = 1; c < cols; c++) {
				if (matrix[0][c] == 0 || matrix[r][0] == 0) {
					matrix[r][c] = 0;
				}
			}
		}
		if (matrix[0][0] == 0) {
			for (int r = 0; r < rows; r++) {
				matrix[r][0] = 0;
			}
		}
		if (rowZero) {
			for (int c = 0; c < cols; c++) {
				matrix[0][c] = 0;
			}
		}
	}
}

