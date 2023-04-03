import java.util.*;

/**
 * Problem: 
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 
 * Key insight: 
 * 1. 4 pointers: left, right, top, bottom
 * 2. When finished traversing a row or column, move the corresponding pointer
 * Time Complexity: O(m*n), m = number of rows, n = number of columns 
 * Space Complexity: O(1), excluding the result list
 */

public class SpiralMatrix {
   
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res; 
        }
        int left = 0;
            int right = matrix[0].length - 1;
            int top = 0;
        int bottom = matrix.length - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) res.add(matrix[top][i]);
            top++;
            for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) res.add(matrix[bottom][i]);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
                left++;
            }
        }
        return res;
    } 

    public static void main(String[] args) {
        SpiralMatrix sm = new SpiralMatrix();
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        // Expected: [1,2,3,6,9,8,7,4,5]
        System.out.println(sm.spiralOrder(matrix));
    }
}
