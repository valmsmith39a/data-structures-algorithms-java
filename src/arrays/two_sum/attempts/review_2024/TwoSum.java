package Review_2024;
import java.util.Map;
import java.util.HashMap;

/** 
 * Key: HashMap to store numbers, Complement = Target - currentNumber 
 * Time Complexity: O(n): iterate through the list once 
 * Space Complexity: O(n): in worst case, store all the numbers in the list
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[] { i, numMap.get(complement) };
            }
            numMap.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        int[] arr = new int[] { 2, 7, 11, 15 };
        int target = 9;
        int[] ans = ts.twoSum(arr, target);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
