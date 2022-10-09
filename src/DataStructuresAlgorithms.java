import java.util.*;

/**
 * Data structures and algorithms problem set
 * 
 * @author George Wee
 * @version 1.0
 * 
 */
public class DataStructuresAlgorithms {

	/**
	 * Basic Hash Table: Two Number Sum
	 * 
	 * Problem: Return 2 numbers that equal the target sum or [].
	 * 
	 * Key idea: potentialMatch = targetSum - num
	 * 
	 * Strategy: iterate through the array, search for potential match (number
	 * when added to current number will produce target sum) by storing numbers in
	 * a simple hash table and performing a constant time lookup.
	 * 
	 * Steps:
	 * 1. Create a basic hash table.
	 * 2. Iterate through array.
	 * 3. potentialMatch = targetSum - num
	 * 4. Lookup potentialMatch in hash table and return array of number and
	 * potentialMatch
	 * 5. If potentialMatch not found, store number in hash table
	 * 
	 * Time/Space Complexity:
	 * O(n) time: iterate through the entire list
	 * O(n) space: store n numbers in the hash table
	 * 
	 * @param numbers   array of numbers
	 * @param targetSum desired sum
	 * @return array of number and potential match or null
	 * 
	 */
	public int[] twoNumberSum(int[] numbers, int targetSum) {
		HashMap<Integer, Boolean> numbersHashMap = new HashMap<>();
		for (int number : numbers) {
			int potentialMatch = targetSum - number;
			if (numbersHashMap.containsKey(potentialMatch)) {
				return new int[] { potentialMatch, number };
			}
			numbersHashMap.put(number, true);
		}
		return new int[] {};
	}

	/**
	 * Balanced Brackets
	 * 
	 * Key idea:
	 * 1. If there is a closed bracket, the previous bracket
	 * (the last bracket on the stack) must be a matching open bracket,
	 * otherwise it's unbalanced.
	 * 
	 * 2. A string has balanced brackets only if the stack is empty after
	 * iterating through the entire string.
	 * 
	 * Time/Space Complexity:
	 * O(n) time: iterate through characters in expression
	 * O(n) space: store brackets in the expression
	 */

	public boolean isBalancedBrackets(String expression) {
		Stack<Character> stack = new Stack<>();
		String openBrackets = "([{";
		String closedBrackets = ")]}";
		HashMap<Character, Character> brackets = new HashMap<>();
		brackets.put(')', '(');
		brackets.put(']', '[');
		brackets.put('}', '{');

		for (char c : expression.toCharArray()) {
			if (openBrackets.contains(Character.toString(c))) {
				stack.push(c);
			} else if (closedBrackets.contains(Character.toString(c))) {
				// Check if c is a closing bracket for
				// the previous open bracket (top of the stack).
				// If it's not or if stack is empty before completely
				// iterating through the array, then expression does not
				// contain balanced brackets.
				if (stack.isEmpty() || brackets.get(c) != stack.pop()) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	/**
	 * Binary Search
	 * 
	 * Problem: Return index of target element or -1
	 * 
	 * Key idea: Search for target by recursively dividing the array in
	 * half and look for the target in either the left or right half. Each
	 * iteration reduces the search space by 1/2.
	 * 
	 * Steps:
	 * 1. Left/Right pointers at beginning/end of array
	 * 2. Check terminal (base) case: left index > right index
	 * 3. Compute floor of middle index
	 * 4. Check 3 cases:
	 * a. target == potentialMatch
	 * b. target < potentialMatch => look in left half of array
	 * c. target > potentialMatch => look in right half of array
	 * 
	 * Time/Space Complexity:
	 * O(log n) time: eliminate 1/2 of elements in each iteration
	 * For iterative approach, O(1) space (constant space): no elements stored
	 * For recursive approach, O(log n) space:
	 * 
	 * @param numbers array of numbers
	 * @param target  target number
	 * @param left    left pointer index
	 * @param right   right pointer index
	 * @return index of the target number or -1
	 */
	public int binarySearch(int[] numbers, int target, int left, int right) {
		/**
		 * Base case:
		 * When get to final number (either first or last),
		 * left == right, pivot index is 0 or lastIndex
		 * In the next iteration, left and right will cross
		 * because either left = pivot + 1 or right = pivot - 1,
		 * depending on whether target is greater or less than
		 * the potential match at the pivot index, indicating
		 * search is complete.
		 */
		if (left > right) {
			return -1;
		}
		// 1. Java rounds down by default
		// 2. Use this method for finding middle index to prevent Integer overflow
		// if left, right or both are a large value (Integer.MAX_VALUE)
		int pivot = left + (right - left) / 2;
		int potentialMatch = numbers[pivot];
		if (potentialMatch == target) {
			return pivot;
		}
		if (target > potentialMatch) {
			return binarySearch(numbers, target, pivot + 1, right);
		}
		return binarySearch(numbers, target, left, pivot - 1);
	}

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
	 * @param numbers  array of numbers
	 * @param startIdx starting index
	 * @param endIdx   ending index
	 * @return void
	 */
	public void quickSort(int[] numbers, int startIdx, int endIdx) {
		if (startIdx >= endIdx) {
			return;
		}
		int pivotIdx = startIdx;
		int leftIdx = startIdx + 1;
		int rightIdx = endIdx;
		while (rightIdx >= leftIdx) {
			if (numbers[leftIdx] > numbers[pivotIdx] && numbers[rightIdx] < numbers[pivotIdx]) {
				swap(numbers, leftIdx, rightIdx);
			}
			if (numbers[leftIdx] <= numbers[pivotIdx]) {
				leftIdx++;
			}
			if (numbers[rightIdx] >= numbers[pivotIdx]) {
				rightIdx--;
			}
		}
		// rightIdx and leftIdx have crossed
		// number at rightIdx is the last number that is smaller than the pivot number
		// so we can swap the number at rightIdx and the number at the pivotIdx
		swap(numbers, pivotIdx, rightIdx);

		// At this point, pivot number is in the correct position,
		// relative to the other numbers in the array (smaller numbers are to the left
		// of the pivot, larger numbers are to the right of the pivot).
		// rightIdx is the index of the newly positioned pivot number.

		// Continue sorting numbers with respect to a pivot on subarrays
		// to the left/right of the newly positioned pivot number.
		boolean leftSubarrayIsSmaller = rightIdx - 1 - startIdx < endIdx - (rightIdx + 1);

		if (leftSubarrayIsSmaller) {
			quickSort(numbers, startIdx, rightIdx - 1);
			quickSort(numbers, rightIdx + 1, endIdx);
		} else {
			quickSort(numbers, rightIdx + 1, endIdx);
			quickSort(numbers, startIdx, rightIdx - 1);
		}
	}

	public void swap(int[] numbers, int i, int j) {
		int swapTemp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = swapTemp;
	}

	/**
	 * Merge Sort
	 * 
	 * Divide and conquer algorithm
	 * 
	 * Key Idea:
	 * Divide the array in half recursively until we get to 1 element arrays
	 * and merge them into sorted sub-arrays until the entire array is sorted.
	 * 
	 * Time Complexity:
	 * O(n log n) time: Each level takes O(n) time and we have O (log n) levels
	 * because we continuously divide the arrays in half.
	 * 
	 * Space Complexity:
	 * O(n log n) space
	 * 
	 * @param numbers array of numbers
	 * @return sorted array
	 * 
	 */
	public int[] mergeSort(int[] numbers) {
		if (numbers.length == 1) {
			return numbers;
		}
		int middleIdx = numbers.length / 2;
		int[] leftHalf = Arrays.copyOfRange(numbers, 0, middleIdx);
		int[] rightHalf = Arrays.copyOfRange(numbers, middleIdx, numbers.length);
		return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf));
	}

	/**
	 * Merge Sorted Array
	 * 
	 * @param leftHalf  left half of array
	 * @param rightHalf right half of array
	 * @return sorted array
	 */
	public int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf) {
		int[] sortedArray = new int[leftHalf.length + rightHalf.length];
		int i, j, k;
		i = j = k = 0;
		while (i < leftHalf.length && j < rightHalf.length) {
			if (leftHalf[i] < rightHalf[j]) {
				sortedArray[k] = leftHalf[i];
				i++;
			} else {
				sortedArray[k] = rightHalf[j];
				j++;
			}
			k++;
		}
		while (i < leftHalf.length) {
			sortedArray[k] = leftHalf[i];
			i++;
			k++;
		}
		while (j < rightHalf.length) {
			sortedArray[k] = rightHalf[j];
			j++;
			k++;
		}
		return sortedArray;
	}

	/**
	 * Permutations
	 * 
	 * Original Array: 1, 2, 3
	 * 1. Can have 1, 2 or 3 in the first slot for 3 permutations
	 * 2. Swap 1 with itself to get the first permutation: 1, 2, 3
	 * 3. Swap 1 with 2 to get the second permutation: 2, 1, 3
	 * 4. Swap 1 with 3 to get the third permutation: 3, 2, 1
	 * 5. For the first permutation, you can have 2 or 3 in the second slot
	 * 6. Swap 2 with itself to get the first permutation: 1, 2, 3
	 * 7. Swap 2 with itself to get the second permutation: 1, 3, 2
	 * 8. For the second permutation, you can have 1 or 3 in the second slot
	 * 9. Swap 1 with itself to get the first permutation: 2, 1, 3
	 * 10. Swap 1 with 3 to et the second permutation: 2, 3, 1
	 * 11. For the third permutation, you can have 2 or 1 in the second slot
	 * 12. Swap 2 with itself to get the first permutation: 3, 2, 1
	 * 13. Swap 2 with 1 to get the third permutation: 3, 1, 2
	 * 
	 * @param args
	 */
	public List<List<Integer>> getPermutations(List<Integer> array) {
		List<List<Integer>> permutations = new ArrayList<List<Integer>>();
		getPermutations(0, array, permutations);
		return permutations;
	}

	public void getPermutations(int i, List<Integer> array, List<List<Integer>> permutations) {
		// Stopping condition - base case
		if (i == array.size() - 1) {
			permutations.add(new ArrayList<Integer>(array));
		} else {
			/**
			 * j used to swap numbers to get a different number in a slot
			 * i used to move to the next slot, to get permutations from that slot
			 * In the 1st iteration, will swap each number with itself
			 * to get { 1, 2, 3 }
			 * In 2nd iteration, i = 0, j = 1, will swap to get { 2, 1, 3 }
			 */
			for (int j = i; j < array.size(); j++) {
				swap(array, i, j);
				getPermutations(i + 1, array, permutations);
				// Need to swap back, to get back to original configuration { 1, 2, 3 }
				swap(array, i, j);
			}
		}
	}

	public void swap(List<Integer> array, int i, int j) {
		Integer temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}

	public void printNumbersArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1) {
				System.out.print(", ");
			}
		}
	}

	public void printList(List<List<Integer>> elements) {
		for (List<Integer> el : elements) {
			System.out.println(el.toString());
		}
	}

	public static void main(String[] args) {
		System.out.println("Hello Metaverse");

		DataStructuresAlgorithms problemSet = new DataStructuresAlgorithms();
		int[] numbers = new int[0];

		/**
		 * Two Number Sum
		 */
		numbers = new int[] { 1, 5, 8, 2, 4, 9, 12 };
		int targetSum = 21;
		int[] resultTwoNumberSum = problemSet.twoNumberSum(numbers, targetSum);
		System.out.println(resultTwoNumberSum);
		System.out.println("Two Number Sum. Target sum is: " + targetSum + " solution is: " + resultTwoNumberSum[0]
				+ ", " + resultTwoNumberSum[1]);

		/**
		 * Balanced Brackets
		 */
		String expression = "{{[[((a, b))],[]]}}"; // true
		// String expression = "{{[}}]]"; // false
		System.out.println("Balanced Brackets. Expression is: "
				+ expression
				+ " Is balanced brackets? " + problemSet.isBalancedBrackets(expression));

		/**
		 * Binary Search
		 */
		numbers = new int[] { 2, 5, 6, 9, 15, 19, 23 };
		int target = 19;
		int resultBinarySearch = problemSet.binarySearch(numbers, target, 0, numbers.length);
		System.out.println("Binary Search. " + "Target is: " + target + " Index of target is: " + resultBinarySearch);

		/**
		 * Quick Sort
		 */
		numbers = new int[] { 8, 5, 2, 9, 5, 6, 3 };
		problemSet.quickSort(numbers, 0, numbers.length - 1);
		System.out.print("Quick Sort. Sorted array is: ");
		problemSet.printNumbersArray(numbers);
		System.out.println();

		/**
		 * Merge Sort
		 */
		numbers = new int[] { 8, 5, 2, 9, 5, 6, 3 };
		int[] sortedArray = problemSet.mergeSort(numbers);
		System.out.print("Merge Sort. Sorted array is: ");
		problemSet.printNumbersArray(sortedArray);
		System.out.println();

		/**
		 * Permutations
		 */
		List<Integer> numberSet = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
		List<List<Integer>> permutations = problemSet.getPermutations(numberSet);
		System.out.print("Permutations of " + numberSet.toString() + ": \n");
		problemSet.printList(permutations);
	}
}
