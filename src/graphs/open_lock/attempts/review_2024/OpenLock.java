import java.util.Set;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Open Lock #752
 * 
 * 1. Rethink the problem as a tree
 * a. Each node is a combination
 * b. The children nodes are the combinations 1 move away from the current node.
 * 2. Use Breadth First Search to generate the children nodes and to find the
 * correct combination
 * 
 * Time Complexity: O(10^4), not totally O(1) bc still depends on the graph
 * size, so for example if problem changed to 5 digits.
 * Space Complexity: O(10^4 + Deadends)
 * 
 */
public class OpenLock {
	public int openLock(String[] deadends, String target) {
		Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
		if (deadSet.contains("0000"))
			return -1;
		if ("0000".equals(target))
			return 0;

		Queue<String> queue = new LinkedList<>();
		queue.offer("0000");

		Set<String> visited = new HashSet<>();
		visited.add("0000");

		int moves = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				String current = queue.poll();
				if (current.equals(target)) {
					return moves;
				}
				for (String next : getNextStates(current)) {
					if (!visited.contains(next) && !deadSet.contains(next)) {
						visited.add(next);
						queue.offer(next);
					}
				}
			}
			moves++;
		}
		return -1;
	}

	private List<String> getNextStates(String state) {
		List<String> nextStates = new ArrayList<>();
		char[] chars = state.toCharArray();
		for (int i = 0; i < 4; i++) {
			char originalChar = chars[i];
			chars[i] = originalChar == '9' ? '0' : (char) (originalChar + 1);
			nextStates.add(new String(chars));

			chars[i] = originalChar == '0' ? '9' : (char) (originalChar - 1);
			nextStates.add(new String(chars));

			chars[i] = originalChar;
		}
		return nextStates;
	}

}
