import java.util.*;

/**
 * Problem: Number of Connected Components in Undirected Graph - Leetcode #323
 * Runtime: O(V + E)
 * Space: O(V + E)
 * 1. Convert edges -> adj list: O(E) time, O(E) space
 * 2. Initialize visited array: O(V) time, O(V) space
 * 3. Iterate over each node: O(V) time, O(V) space
 */
class NumberOfConnectedComponents {
	public int countComponents(int n, int[][] edges) {
		int numOfComponents = 0;
		List<Integer>[] adj = new ArrayList[n];
		int[] visited = new int[n];

		// Build adjacency list
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		/**
		 * edges: [[0, 1], [1, 2]]
		 * adj: [[1], [0, 2], [1]] bc undirected graph
		 * adj[v1] is the ArrayList of neighbors
		 */
		for (int i = 0; i < edges.length; i++) {
			int v1 = edges[i][0];
			int v2 = edges[i][1];
			adj[v1].add(v2);
			adj[v2].add(v1);
		}

		// Count the number of components
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				numOfComponents++;
				dfs(i, adj, visited);
			}
		}
		return numOfComponents;
	}

	private void dfs(int vertex, List<Integer>[] adj, int[] visited) {
		visited[vertex] = 1;
		List<Integer> neighbors = adj[vertex];
		for (int i = 0; i < neighbors.size(); i++) {
			int neighbor = neighbors.get(i);
			if (visited[neighbor] == 0) {
				dfs(neighbor, adj, visited);
			}
		}
	}
}
