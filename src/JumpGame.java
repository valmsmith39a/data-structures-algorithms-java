/**
 * Problem: Jump Game  
 * 
 * Key Insights: 
 * 1. Greedy approach: compute max reach index at each index 
 * 2. If current idx is > maxReach, then know for sure cannot jump to the end with current set of nums 
 * 
 * nums: 2, 3, 1, 1, 4
 * idx:  0, 1, 2, 3, 4
 * max:  0, 2, 5
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class JumpGame {
 
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }
}
