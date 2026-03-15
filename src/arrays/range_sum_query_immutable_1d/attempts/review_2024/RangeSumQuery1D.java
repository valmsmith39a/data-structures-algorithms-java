public class RangeSumQuery1D {
    int[] prefix;

    /* 
        Range Sum Query Immutable (1D) #303
        Key Insights: 
        1. Create a prefix sum that tracks the cumulative sum at each index 
        2. Prefix sum is the prefix sum at *previous index* + value at current index
           prefix[0] = 0
           prefix[1] = prefix[0] + nums[0] 
           prefix[i+1] = prefix[i] + nums[i]
           prefix[i] is the prefix sum at previous index
           nums[i] is value at current index
        3. To sum range: [1, 3]
           Want prefix sum at 3 + 1 (to account for construction of prefix sum array)
           Minus the prefix sum at 1 (which is the previous prefix sum) bc don't want 
           to minus the value at the current left index 

        Time Complexity:
        1. Create the prefix sum: O(n)
        2. Get sum range is: O(1)

        Space Complexity: 
        1. Create prefix sum: O(n)
        2. Get sum range: O(1)
     */
    public RangeSumQuery1D(int[] nums) {
        prefix = new int[nums.length + 1];
        prefix[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}