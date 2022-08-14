class MaxSubarraySum {
    /**
     *  Max Subarray Sum (Kadane's Algorithm)
     *
     *  Problem:
     *      Find the maximium contiguous subarray sum 
     *  
     *  Key insights: 
     *  1. Kadane's Algorithm
     *      a. Find the current max subarray sum
     *          i. current max subarray sum = max(previous max subarray sum, current value)
     *      b. Global max subarray sum = max(current max subarray sum, previous global max subarray sum)
     *          i. Possible for current max subarray sum to be less than global max subarray sum 
     *          ii. Ex. [1, 2, -1]
     *          current max: 1, 3, 2 (max((3 + -1), -1) => 2)
     *          global max: 1, 3, 3 
     * 
     * Complexity Analysis: 
     * 1. Time complexity: O(n) time: loop through array once 
     * 2. Space complexity: O(1) space: no matter size of array, only have 2 variables to store current/global max 
     *
     */ 
    public int findMaxSubarraySum(int[] nums) {
        // Find max subarray sum at current index 
        // Find the global max subarray sum at current index
        int currentMaxSum = nums[0];
        int globalMaxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // Current max subarray sum is either: previous max subarray sum + current value 
            // or current value
            currentMaxSum = Math.max(nums[i], currentMaxSum + nums[i]);
            globalMaxSum = Math.max(globalMaxSum, currentMaxSum);
        }
        return globalMaxSum;
    }
    public static void main(String args[]) {
        int[] nums = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        MaxSubarraySum maxSubarray = new MaxSubarraySum();
        int maxSubarraySum = maxSubarray.findMaxSubarraySum(nums);
        System.out.println("Max subarray sum is: " + maxSubarraySum);
    }
}
