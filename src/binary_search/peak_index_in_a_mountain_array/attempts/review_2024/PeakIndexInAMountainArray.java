package Review_2024;

/**
 * Peak Index in a Mountain Array #852
 * Remember:
 * When moving left, the right bound is pivotIdx, because arr[pivotIdx] could be
 * the value looking for.
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(log n)
 */
public class PeakIndexInAMountainArray {

	public int peakIndexInMountainArray(int[] arr) {
		return binarySearch(arr, 0, arr.length - 1);
	}

	private int binarySearch(int[] arr, int left, int right) {
		if (left == right) {
			return left;
		}
		int pivotIdx = left + (right - left) / 2;
		if (arr[pivotIdx] < arr[pivotIdx + 1]) {
			return binarySearch(arr, pivotIdx + 1, right);
		}
		return binarySearch(arr, left, pivotIdx);
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 0, 10, 5, 2 };
		PeakIndexInAMountainArray pi = new PeakIndexInAMountainArray();
		int res = pi.peakIndexInMountainArray(arr);
		System.out.println("Answer is: " + res);
	}
}
