import java.util.*;

class TrappingRainWater {
    /**
     * Problem: Trapping Rain Water 
     *
     * Description: 
     *  Given elevation map with heights compute how much water can be trapped after raining
     *
     * Solution: 
     *  1. Calculate and store the left max height going from 1 to n - 1
     *  2. Calculate and store the right max height going from n - 2 to 0
     *  3. At each index, find the max amount of water able to be stored by 
     *     getting the min of the left/right max heights and subtract current heightear
     * 
     * Other: 
     *  1. Dynamic Programming
     *  2. Memoization: store results of solutions to sub-problems.
     *
     */
    public int computeWaterTrapped(int[] heights) {
        // Iterate through heights from 1 to n - 1, to calculate / store the max left
        // height at each index 
        int size = heights.length;
        int[] leftMax = new int[size];
        int[] rightMax = new int[size];
        leftMax[0] = heights[0];
        rightMax[size - 1] = heights[size - 1];
        int waterTrapped = 0;

        // Start from i = 1, because there is no i - 1 at index 0
        for (int i = 1; i < size - 1; i++) {
            leftMax[i] = Math.max(heights[i], leftMax[i - 1]);
        }
        
        // Start from j = size - 2, because there is no j + 1 at the last index (size - 1)
        for (int j = size - 2; j >= 0; j--) {
            rightMax[j] = Math.max(heights[j], rightMax[j + 1]);
        }
       
        /* Get the minimum height between leftMax height and rightMax height 
            at current index and subtract the height at that index. 
            This will give you the units of water trapped at that index 
            Area = height x width = (Min of max left/right heights - height) x 1 */  
        for (int k = 0; k < size - 1; k++) {
            waterTrapped += Math.min(leftMax[k], rightMax[k]) - heights[k];
        }
        return waterTrapped;
    }
    public static void main(String[] args) {
        int[] heights1 = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrappingRainWater water = new TrappingRainWater();
        int waterTrapped = water.computeWaterTrapped(heights1);
        System.out.println("Heights from elevation map: " + Arrays.toString(heights1));
        // Solution: 6
        System.out.println("Units of water trapped after raining: " + waterTrapped);
        
        int[] heights2 = new int[] { 4, 2, 0, 3, 2, 5 };
        int waterTrapped2 = water.computeWaterTrapped(heights2);
        System.out.println("Heights 2 from elevation map: " + Arrays.toString(heights2));
        // Solution: 9
        System.out.println("Units of water trapped after raining: " + waterTrapped2);
    }
}
