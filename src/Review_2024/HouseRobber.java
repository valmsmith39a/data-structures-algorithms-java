/**
 * House Robber #198
 * 
 * Dynamic Programming 
 * 
 * Key Insights: 
 * 1. I need to keep track of the max amount at each index 
 * 2. The max amount at current index (starting from index 2) is either  
 *  a. Max amount at i - 1 or 
 *  b. Max amount at i - 2 + amount at i 
 * 3. Max amount at index 0 is just the amount at index 0 
 * 4. Max amount at index 1 is just the Max of amount at index 0 and index 1 
 */
public class HouseRobber {
    /**
     *  Solution 1: 
     *      Create a dynamic programming array (int[] dp) to keep track of the max amount at each index 
     * 
     *  Time Complexity: O(n)
     * 
     *  Space Complexity: O(n), dp array 
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 1) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    /**
     *  Solution 2
     *      2 pointers to keep track of prev1 (i - 1) and prev2 (i - 2)      
     *  
     *  Time Complexity: O(n), need to iterate through numbers  
     * 
     *  Space Complexity: O(1), only need 2 pointers instead of dp array 
     */
     public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int prev1 = Math.max(nums[1], nums[0]);
        int prev2 = nums[0];
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}
