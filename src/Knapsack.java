import java.util.*;

/**
 * Prompt:
 * Given the values and weights of n items and capacity W, find the maximum
 * value
 * of the knapsack, without exceeding the capacity.
 * 
 * Inputs:
 * 2-dimensional array where each sub-array represents and item and holds 2
 * integer values.
 * First integer: item's value
 * Second integer: item's weight
 * 
 * Constraints:
 * Can only have 1 of each item in the knapsack
 *
 * Key insights:
 * 1. Create knapsackValues matrix of maximum values, at each item and capcity.
 * a. Max capacities as columns
 * b. Items as rows
 * c. At each max capacity, with n number of items, what is the max value of
 * items?
 * 2. For each value[i][j], ask, what is the max value of items we can have at
 * this max capacity?
 * a. Either it's the previous max value at that max capacity or
 * b. The current value + the max value at the (j - currentWeight) capacity
 * 
 * Key formula:
 *
 * knapsackValues is my matrix
 *
 * [itemValue, itemWeight], knapsackValues[i, j] =
 * max { knapsackValues[i - 1][j], knapsackValues[i - 1][j - itemWeight] +
 * itemValue}
 * 
 * To get the items after computing the max value:
 * 
 * if knapsackValues[i][j] (current max value at a max capacity) ==
 * knapsackValues[i-1][j] (previous max value at the max capacity)
 * then did *not* add the current item.
 * 
 * if knapsackValues[i][j] != knapsackValues[i-1][j],
 * then *did* add the current item.
 * Add the current item to your list of items and
 * Move the capacity pointer (j) back by the weight of current item.
 * j = j - weightOfCurrentItem
 * 
 * Summary of Strategy:
 * 1. For i number of items, figure out the max value at each max capacity
 * Ex1: At max capacity 3, with these 2 items, what is my max value?
 * Either previous max value or the currentValue + max value at [i-1][c -
 * currentWeight]
 * 
 * Problem Type:
 * Dynamic programming: I'm finding the max value at each cell [i][c]
 * 
 * Problem history / context:
 * 1. Knapsack problem is NP-complete (intersection of NP and NP-hard)
 * 2. O(nW) time, but it's considered "pseudo-polynomial" time because W
 * actually increases
 * exponentially.
 * 
 * Quick Review:
 * P: Class of problems that can be solved by a deterministic Turing machine in
 * polynomial time.
 * NP: Nondeterministic polynomial time. Class of problems that can be solved by
 * a Nondeterministic Turing machine in polynomial time.
 * NP-hard: Problems that are at least as hard as those that can be solved by a
 * non-deterministic Turing machine in polynomial time.
 * 
 * Time/Space Complexity:
 * O(nc) time, n: number of items, c: number of columns
 * O(nc) space
 */
public class Knapsack {

	public List<List<Integer>> getMaxValue(int[][] items, int capacity) {
		/**
		 * Initialize size of array to items.length + 1 and capacity + 1 because
		 * because in our matrix we have “throw-away”
		 * max capacity: 0 and item with [0, 0]
		 * 
		 * items.length + 1, because need to use i - 1 later.
		 * capacity + 1, because if capacity 5, want to iterate until 5 (0-5 is 6
		 * elements)
		 */
		int[][] knapsackValues = new int[items.length + 1][capacity + 1];

		/**
		 * Create knapsack values matrix.
		 * Iterate through each item i.
		 */
		for (int i = 1; i < items.length + 1; i++) {
			int currentWeight = items[i - 1][1];
			int currentValue = items[i - 1][0];
			// Iterate through each capacity c
			for (int c = 0; c < capacity + 1; c++) {
				/**
				 * If current weight of item > capacity,
				 * max value is just the max value at the previous item,
				 * for the same capacity.
				 */
				if (currentWeight > c) {
					knapsackValues[i][c] = knapsackValues[i - 1][c];
				} else {
					/**
					 * If current weight is <= capacity,
					 * max value is the max of the max value at the previous item,
					 * for the same capacity, or the value of the current value +
					 * the max value at the cell knapsackValues[i - 1][c - currentValue]
					 */
					knapsackValues[i][c] = Math.max(knapsackValues[i - 1][c],
							knapsackValues[i - 1][c - currentWeight] + currentValue);
				}
			}
		}
		return getKnapsackItems(knapsackValues, items, knapsackValues[items.length][capacity]);
	}

	public List<List<Integer>> getKnapsackItems(int[][] knapsackValues, int[][] items, int weight) {
		List<List<Integer>> sequence = new ArrayList<List<Integer>>();
		List<Integer> totalWeight = new ArrayList<Integer>(Arrays.asList(weight));
		sequence.add(totalWeight);
		sequence.add(new ArrayList<Integer>());
		/**
		 * knapsackValues length in this case is 5 (4 values + 1 "throwaway" [0, 0])
		 * Last index is 4
		 */
		int i = knapsackValues.length - 1;
		/**
		 * max capacity columns length is 11 (10 max capacities + 1 "throwaway" value (0
		 * capacity)
		 */
		int c = knapsackValues[0].length - 1;
		/**
		 * i > 0, because don't want to iterate to 0, it's the "throwaway" value in out
		 * table
		 */
		while (i > 0 && c > 0) {
			/**
			 * if current value is equal to previous (above) value
			 * then did not add the current value.
			 * We used the above value.
			 * start at c = 10, i = 4
			 */
			if (knapsackValues[i][c] == knapsackValues[i - 1][c]) {
				i--;
			} else {
				/**
				 * Means did add the current item
				 * Is index is i - 1 because in knapsackValues, we have an extra row of 0s
				 * in the beginning
				 * So we started with 4 item values + 1 "throwaway" = 5 total
				 * i is index 4, but if we want then it's actually index 3 on our actual list of
				 * 4 items.
				 * 
				 * Include 0 index as first param because want to add to the beginning of the
				 * list.
				 * We're traversing the matrix backwards and want the earlier index first.
				 */
				int itemIndex = i - 1;
				sequence.get(1).add(0, i - 1);

				/**
				 * Move column pointer (capacity) by the weight of the current item
				 * Again is i -1 because of the extra 0s
				 * c index for current column is correct as is because we start at index 10.
				 * we have 0 - 10 index values for 11 values
				 */
				int itemWeight = items[itemIndex][1];
				c -= itemWeight;
				i--;
			}
		}
		return sequence;
	}

	public static void main(String[] args) {
		int[][] items = new int[][] { new int[] { 1, 2 }, new int[] { 4, 3 }, new int[] { 5, 6 }, new int[] { 6, 7 } };
		int capacity = 10;

		Knapsack knapsack = new Knapsack();
		List<List<Integer>> knapsackSolution = knapsack.getMaxValue(items, capacity);
		// Expected output: [[10], [1, 3]]
		System.out.println(knapsackSolution.toString());
	}
}
