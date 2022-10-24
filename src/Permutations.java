import java.util.*;

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
 * 7. Swap 2 with 3 to get the second permutation: 1, 3, 2
 * 8. For the second permutation, you can have 1 or 3 in the second slot
 * 9. Swap 1 with itself to get the first permutation: 2, 1, 3
 * 10. Swap 1 with 3 to get the second permutation: 2, 3, 1
 * 11. For the third permutation, you can have 2 or 1 in the second slot
 * 12. Swap 2 with itself to get the first permutation: 3, 2, 1
 * 13. Swap 2 with 1 to get the third permutation: 3, 1, 2
 * 
 * @param args
 */
public class Permutations {

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

	public void printList(List<List<Integer>> elements) {
		for (List<Integer> el : elements) {
			System.out.println(el.toString());
		}
	}

	public static void main(String[] args) {
		Permutations perm = new Permutations();
		List<Integer> numberSet = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
		List<List<Integer>> permutations = perm.getPermutations(numberSet);

		System.out.print("Permutations of " + numberSet.toString() + ": \n");
		perm.printList(permutations);
	}
}