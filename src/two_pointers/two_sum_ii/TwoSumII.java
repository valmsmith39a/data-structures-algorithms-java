/**
 * Problem: Two Sum II 
 * 
 * Key Insights: 
 * 1. Array is sorted, so can use 2 pointer method
 *  a. left / right pointers at each end. 
 *  b. if currentSum < target, can't move right pointer to the left bc then
 *     currentSum would be even smaller, so must move left pointer to the right by 1.
 *  c. if currentSum > target, can't move left pointer to the right bc then 
 *     currentSum would be even greater, so must move right pointer to the left by 1.  
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class TwoSumII {

    public int[] twoSumII(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int currentSum = numbers[left] + numbers[right];

            if (currentSum == target) {
                return new int[] { left + 1, right + 1 };
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
}

