import java.util.*;

/**
 * Problem: Rotate image 
 * 
 * Key Insights: 
 * 1. Use left, right, top, bottom to traverse the matrix 
 * 2. Save topLeft and move in counter clockwise; 
 * 
 * Time complexity: O(n2) time
 * Space complexity: O(1) space
 *
 */
public class RotateImage {
    
    public void rotate(int[][] matrix) {
        int l = 0;
        int r = matrix.length - 1;
        while (l < r) {
            for (int i = 0; i < r - l; i++) {
                int top = l;
                int bottom = r;
                int topLeft = matrix[top][l + i];
                matrix[top][l + i] = matrix[bottom - i][l];
                matrix[bottom - i][l] = matrix[bottom][r - i];
                matrix[bottom][r - i] = matrix[top + i][r];
                matrix[top + i][r] = topLeft;
            }
            r -= 1;
            l += 1;
        }
    }
}

