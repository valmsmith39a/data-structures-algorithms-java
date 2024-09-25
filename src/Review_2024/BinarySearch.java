/**
 * key: Because the array is sorted, compare target against midpoint to get a O(log n) solution
 * Time Complexity: O(log n), bc each iteration eliminates half the elements k (iterations) = log n (base 2)
 * Space Complexity: O(log n) on call stack 
 */
public class BinarySearch {

	public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] arr, int target, int left, int right) {
        if (right < left) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (target == arr[mid]) {
            return mid;
        }
        if (target < arr[mid]) {
            return binarySearch(arr, target, left, mid - 1);
        } 
        return binarySearch(arr, target, mid + 1, right);
    }
}
