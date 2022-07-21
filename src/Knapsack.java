import java.util.*;

class Knapsack {
	/**
	 * Prompt: 
	 * 	Given an array of arrays where each sub-array holds 2 integer values and represents items.
	 *	First integer: item's value
	 *	Second integer: item's weight
	 *	Also given the maximum capacity of the knapsack
	 *		 
	 *	Find the number of items that you can fit into your knapsack that would maximize the 
	 *	value of the items without exceeding the capacity.
	 *	
	 *	Can only have 1 of each item in the knapsack
	 *
	 *	Key insights:
	 *		1. Matrix: 
	 *			a. Max capacities as columns
	 *			b. Items as rows 
	 *			c. At each max capacity, with x number of items, what is the max value of items? 
	 *		2. For each value[i][j], ask, what is the max value of items I can have at this max capacity?
	 *			a. Either it's the previous max value at that max capacity or 
	 *			b. The current value + the max value at the (j - currentWeight) capacity
	 *	
	 *	Key formula: 
	 *
	 *	knapsackValues is my matrix 
	 *
	 *  [itemValue, itemWeight], knapsackValues[i, j] = 
	 *  	max { knapsackValues[i - 1][j], knapsackValues[i - 1][j - itemWeight] + itemValue}
	 * 
	 *  To get the items after computing the max value: 
	 *  
	 *  	if knapsackValues[i][j] (current max value at a max capacity) == knapsackValues[i-1][j] (previous max value at the max capacity)
	 *  	then did *not* add the current item. 
	 *  	
	 *  	if knapsackValues[i][j] != knapsackValues[i-1][j],
	 *  	then *did* add the current item.
	 *  		Add the current item to your list of items and 
	 *  		Move the capacity pointer (j) back by the weight of current item. 
	 *  			j = j - weightOfCurrentItem
	 *  
	 *   Time/Space Complexity: 
	 *   O(nc) time, n: number of items, c: number of columns 
	 *   O(nc) space
	 *
	 */

	static List<List<Integer>> knapsackGetMaxValue(int[][] items, int capacity) {
		// Initialize size of array to items.length + 1 and capacity + 1 because 
		// because in our matrix we have “throw-away”
		// max capacity: 0 and item with [0, 0]  
		int[][] knapsackValues = new int[items.length + 1][capacity + 1];
		
		// i: items  
		for (int i = 1; i < items.length + 1; i++) {
			int currentWeight = items[i - 1][1];
			int currentValue = items[i - 1][0];
			// c: capacity
			for (int c = 0; c < capacity + 1; c++) {
				if (currentWeight > c) {
					knapsackValues[i][c] = knapsackValues[i - 1][c];
				} else {
					// max of { knapsackValues[i - 1][c - currentWeight] + currentValue, knapsackValues[i - 1][c] }
					knapsackValues[i][c] = Math.max(knapsackValues[i - 1][c], knapsackValues[i-1][c - currentWeight] + currentValue);
				}
			}
		}
		return getKnapsackItems(knapsackValues, items, knapsackValues[items.length][capacity]);
	}
	
	static List<List<Integer>> getKnapsackItems(int[][] knapsackValues, int[][] items, int weight) {
		List<List<Integer>>sequence = new ArrayList<List<Integer>>();
		List<Integer> totalWeight = new ArrayList<Integer>();
		totalWeight.add(weight);
		sequence.add(totalWeight);
		sequence.add(new ArrayList<Integer>());
		int i = knapsackValues.length - 1;
		int c = knapsackValues[0].length - 1;
		while (i > 0) {
			// if current value is equal to previous (above) value 
			// then did not add the current value.
			// We used the above value. 
			if (knapsackValues[i][c] == knapsackValues[i - 1][c]) {
				i--;
			} else {
				// Means did add the current item 
				// Is index is i - 1 because in knapsackValues, we have an extra row of 0s
				// in the beginning 
				sequence.get(1).add(0, i - 1);
				// Move column pointer (capacity) by the weight of the current item
				// Again is i -1 because of the extra 0s 
				c -= items[i-1][1];
				i--;
			}
			if (c == 0) {
				break;
			}
		}
		return sequence;
	}
	
	
	public static void main(String[] args) {
		int[][] items = new int[][] {new int[] {1, 2}, new int[] {4, 3}, new int[] {5, 6}, new int[] {6, 7}};
		int capacity = 10;
		
		List<List<Integer>> knapsackSolution = knapsackGetMaxValue(items, capacity);
		for (int i = 0; i < knapsackSolution.size(); i++) {
			System.out.println(knapsackSolution.get(i));
		}
	}
}
