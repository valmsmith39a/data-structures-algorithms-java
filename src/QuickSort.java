/**
 * Quick Sort
 * 
 * Key ideas:
 * 1. Recursively sort numbers with respect to a pivot.
 * 2. Move numbers <= pivot to the left of the pivot.
 * 3. Move numbers > pivot to the right of the pivot.
 * 
 * Steps:
 * 1. Choose a pivot, create left and right pointers.
 * 2. Move numbers <= pivot to the left of the pivot.
 * 3. Move numbers > pivot to the right of the pivot.
 * 4. Swap number at pivot with the number at the right pointer
 * so that pivot will be in it's sorted position.
 * 5. Continue on sub-arrays to the left/right of the newly positioned pivot.
 * 
 * Time Complexity:
 * Worst case: O(n^2) time: if pivot splits array in lop-sided manner (most of
 * numbers are to the left or right of the pivot).
 * Best case: if pivot evenly splits the array (approximately equal numbers on
 * each side),
 * then we make log n calls of quick sort. Performing O (log n) operations n
 * times => O(n log n)
 * Average case: O (n log n)
 *
 * Space Complexity:
 * O(log n): run quick sort recursively on smaller sub-array first.
 * 
 * Example iteration:
 * 8, 5, 2, 9, 5, 6, 3
 * swap
 * 8, 5, 2, 3, 5, 6, 9
 * swap
 * 6, 5, 2, 3, 5, 8, 9
 * 
 * pivot value: 8
 * 
 * leftIdx value:
 * 5, 2, 9
 * swap
 * 5, 2, 3, 5, 6, 9
 * 
 * rightIdx value:
 * 3
 * swap
 * 9, 6
 * 
 * key insight: rightIdx always ends up at a number smaller than the pivot
 * 
 * @param numbers array of numbers
 * @param start   starting index
 * @param end     ending index
 * @return void
 */
public class QuickSort {

	public void quickSort(int[] numbers, int start, int end) {
		/**
		 * If start > end, return bc incorrect values.
		 * If start == end, return bc only 1 number, nothing to sort.
		 */
		if (start >= end) {
			return;
		}
		int pivot = start;
		int left = start + 1;
		int right = end;
		while (left < right) {
			if (numbers[left] > numbers[pivot] && numbers[right] < numbers[pivot]) {
				swap(numbers, left, right);
			}
			if (numbers[left] <= numbers[pivot]) {
				left++;
			}
			if (numbers[right] >= numbers[pivot]) {
				right--;
			}
		}
		// rightIdx and leftIdx are the same or have crossed
		// number at rightIdx is the last number that is smaller than the pivot number
		// so we can swap the number at rightIdx and the number at the pivotIdx
		swap(numbers, pivot, right);

		// At this point, pivot number is in the correct position,
		// relative to the other numbers in the array (smaller numbers are to the left
		// of the pivot, larger numbers are to the right of the pivot).
		// rightIdx is the index of the newly positioned pivot number.

		// Continue sorting numbers with respect to a pivot on subarrays
		// to the left/right of the newly positioned pivot number.
		boolean leftSubarrayIsSmaller = right - 1 - start < end - (right + 1);
		if (leftSubarrayIsSmaller) {
			quickSort(numbers, start, right - 1);
			quickSort(numbers, right + 1, end);
		} else {
			quickSort(numbers, right + 1, end);
			quickSort(numbers, start, right - 1);
		}
	}

	public void swap(int[] numbers, int i, int j) {
		int swap = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = swap;
	}

	public void printNumbersArray(int[] numbers) {
		for (int num : numbers) {
			System.out.println(num);
		}
	}

	public static void main(String args[]) {
		int[] numbers = new int[] { 8, 5, 2, 9, 5, 6, 3 };
		QuickSort sort = new QuickSort();
		sort.quickSort(numbers, 0, numbers.length - 1);
		// Output: 2, 3, 5, 5, 6, 8, 9
		System.out.println("Quick Sort. Sorted array is: ");
		sort.printNumbersArray(numbers);
		System.out.println();
	}
}
