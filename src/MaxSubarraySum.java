class MaxSubarraySum {
    /**
     * Maximum Sum Subarray (Kadane's Algorithm)
     *
     * Problem:
     * Given an array of integers nums, find the contiguous subarray (containing at
     * least one number) which has the largest sum and return its sum.
     * 
     * Constraints:
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * 
     * Example:
     * Input: nums = [-2, 1, -3, 4, -1, 2, 1, -5 , 4]
     * Output: 6
     * Explanation: [4, -1, 2, 1] has the largest sum = 6
     * 
     * Key insights:
     * 1. Kadane's Algorithm
     * a. Find the current max subarray sum
     * i. current max subarray sum = max(previous max subarray sum + current value,
     * current value)
     * b. Global max subarray sum = max(current max subarray sum, previous global
     * max subarray sum)
     * i. Possible for current max subarray sum to be less than global max subarray
     * sum
     * ii. Ex. [1, 2, -1]
     * current max: 1, 3, 2 (max((3 + -1), -1) => 2)
     * global max: 1, 3, 3
     * 
     * Complexity Analysis:
     * 1. Time complexity: O(n) time: loop through array once
     * 2. Space complexity: O(1) space: no matter size of array, only have 2
     * variables to store current/global max
     *
     * History:
     * Maximum subarray problem first proposed by Ulf Grenander in 1977 as a
     * simplified model for maximum likelihood estimation of patterns in digitized
     * images.
     * 
     * He was looking for a rectangular subarray with the maximum sum, in a
     * 2-dimensional
     * array of real numbers.
     * 
     * He proposed a 1-dimensional problem to gain insight into the problem.
     * 
     * Jay Kadane heard about the problem while attending a seminar given by Michael
     * Shamos (who had
     * devised an n log n divide / conquer approach to the problem) and designed an
     * O(n) time algorith.
     */
    public int findMaxSubarraySum(int[] nums) {
        // Find max subarray sum at current index
        // Find the global max subarray sum at current index
        int currentMaxSum = nums[0];
        int globalMaxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // Current max subarray sum is either: current value or previous max subarray
            // sum + current value.
            currentMaxSum = Math.max(nums[i], currentMaxSum + nums[i]);
            // Global max subarray sum is either: max subarray sum at current index or the
            // global max subarray sum.
            globalMaxSum = Math.max(currentMaxSum, globalMaxSum);
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
