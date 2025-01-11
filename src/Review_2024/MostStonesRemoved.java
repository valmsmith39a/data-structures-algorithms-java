package Review_2024;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Time Complexity: O(n^2)
 * - Adjacency Graph: O(n^2)
 * - DFS: O(V + E), in fully connected graph: O((n(n-1))/2) -> O(n^2)
 * 
 * Space Complexity:
 * - Adjacency Graph: O(V + E) - worst case, fully connected -> O(n(n - 1) / 2)
 * -> O(n^2)
 * - Visited Set: O(n)
 * - Recursive call stack: O(n) - worst case all the stones are connected
 */
public class MostStonesRemoved {

	public int removeStones(int[][] stones) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i < stones.length; i++) {
			for (int j = i + 1; j < stones.length; j++) {
				if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
					graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
					graph.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
				}
			}
		}

		Set<Integer> visited = new HashSet<>();
		int connectedComponents = 0;

		for (int i = 0; i < stones.length; i++) {
			if (!visited.contains(i)) {
				connectedComponents++;
				dfs(i, graph, visited);
			}
		}
		return stones.length - connectedComponents;
	}

	private void dfs(int stoneIndex, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
		visited.add(stoneIndex);
		for (int nei : graph.getOrDefault(stoneIndex, new ArrayList<>())) {
			if (!visited.contains(nei)) {
				dfs(nei, graph, visited);
			}
		}
	}
}
