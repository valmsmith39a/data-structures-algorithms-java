/**
 * Problem: Remove Duplicates From Sorted Array II (#80)
 * 
 * Key Insights: 
 * 1. Use an index to track where next valid number (new number or 1st duplicate) should go 
 * 
 * Time Complexity: O(n)
 * 
 * Space Complexity: O(1), because new array constructed in place 
 */
public class RemoveDuplicatesFromSortedArraryII {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int idx = 1;
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count <= 2) {
                nums[idx] = nums[i];
                idx++;
            }
        }

        return idx;
    }
    
}
