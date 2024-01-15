/**
 * Problem: House Robber  
 * 
 * Key Insights: 
 * 
 * At each index, I need to find the max value that can be robbed up to that index. 
 * 
 * If I'm at house 0, to max the value robbed at that index, I should rob house 0. 
 * 
 * If I'm at house 1, to max the value robbed at that index, I should rob house 0 or house 1. 
 * 
 * If I'm at house 2, to max the value robbed at that index, I should either rob house 2 and house 0 
 * 
 * or house 1
 * 
 * Time Complexity: O(n), n: number of houses
 * 
 * Space Complexity: O(n), the dynamic programming (DP) array 
 */
public class HouseRobber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
