import java.util.*;

/**
 * Problem: Alien Dictionary
 * 
 * Solution:
 * 1. Build reverse adjacency list
 * 2. Compare letters in current and next word to find relationships between
 * characters (edges)
 * 3. Run depth first search on graph but collect letters at the end of dfs
 * function ("post dfs" or Topological Sort)
 * 
 * Key idea:
 * The first difference in characters between current and next word, indicates
 * that the
 * character in the first word comes before the character in the second word.
 * 
 * Time Complexity: O(c), c = total number of characters in all the words.
 * O(c) to build the adjacency list, find relationships (edges), run depth first
 * search
 * on each character.
 * 
 * Space Complexity:
 * O(V + E):
 * 1. Space to build adjacency list
 * 2. V = # of vertices, E = # of edges
 * O(1): Number of unique characters (U)fixed at 26 and edges is 26^2
 * O(U + min(U^2, N): For arbitrarily large number of unique characters.
 */
public class AlienDictionary {
	private Map<Character, List<Character>> reverseAdjList = new HashMap<>();
	private Map<Character, Boolean> visited = new HashMap<>();
	private StringBuilder output = new StringBuilder();

	public String alienOrder(String[] words) {

		// Build the reverse adjacency list
		for (String word : words) {
			for (Character c : word.toCharArray()) {
				reverseAdjList.put(c, new ArrayList<>());
			}
		}

		/**
		 * Create edges (relationships between characters)
		 * Iterate until length - 1 because comparing current and next words.
		 */
		for (int i = 0; i < words.length - 1; i++) {
			String word1 = words[i];
			String word2 = words[i + 1];

			if (word1.length() > word2.length() && word1.startsWith(word2)) {
				return "";
			}

			for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
				// if characters are different, know that char for word 1 < char for word 2
				if (word1.charAt(j) != word2.charAt(j)) {
					reverseAdjList.get(word2.charAt(j)).add(word1.charAt(j));
					// After first character difference found, no relevant relationship can be found
					break;
				}
			}
		}

		/**
		 * Iterate through reverse adjacency list
		 */
		for (Character c : reverseAdjList.keySet()) {
			boolean result = dfs(c);
			/**
			 * if result == false, then node is "gray", meaning visited but not fully
			 * visited.
			 * Detected a cycle. Ex. we, ee, we. w < e, but e < w.
			 */
			if (!result)
				return "";
		}
		return output.toString();
	}

	private boolean dfs(Character c) {
		if (visited.containsKey(c)) {
			return visited.get(c);
		}
		visited.put(c, false);
		for (Character adj : reverseAdjList.get(c)) {
			boolean result = dfs(adj);
			if (!result)
				return false;
		}
		visited.put(c, true);
		output.append(c);
		return true;
	}
}
