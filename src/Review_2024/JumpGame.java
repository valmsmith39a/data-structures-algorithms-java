public class JumpGame {

    public boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachable) return false;
            reachable = Math.max(reachable, nums[i] + i);
            if (reachable > nums.length - 1) return true;
        }
        return true;
    }
    
}
