package Review_2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
public class GraphValidTree {

	public boolean validTree(int n, int[][] edges) {
		if (edges.length != n - 1) {
			return false;
		}

		Map<Integer, List<Integer>> adj = new HashMap<>();
		for (int i = 0; i < n; i++) {
			adj.put(i, new ArrayList<>());
		}
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}

		Set<Integer> visited = new HashSet<>();

		if (hasCycle(0, -1, adj, visited)) {
			return false;
		}

		return visited.size() == n;

	}

	private boolean hasCycle(int node, int parent, Map<Integer, List<Integer>> adj, Set<Integer> visited) {
		if (visited.contains(node)) {
			return true;
		}

		visited.add(node);

		for (int nei : adj.get(node)) {
			if (nei == parent) {
				continue;
			}
			if (hasCycle(nei, node, adj, visited)) {
				return true;
			}
		}

		return false;
	}
}
