package Review_2025;

/**
 * Time Complexity: O(n * ⍺(n))
 * 1. initialize parent array: O(n), n = number of nodes
 * 2. iterate over edges and do union-find on each edge: O(m * ⍺(n))
 * a. m = number of edges
 * b. ⍺(n) = runtime for find function, which effectively amortizes to O(1)
 * i. ⍺(n): Inverse Ackerman function: function that grows extremely slowly
 * 3. O(n) + O(m * ⍺(n)) => O(n) + O(n * ⍺(n)) bc m = n in this problem => O(n *
 * ⍺(n)) bc this dominates O(n) time
 * 
 */
public class RedundantConnection {
	private int[] parent;

	public int[] findRedundantConnection(int[][] edges) {
		int n = edges.length;
		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		for (int[] edge : edges) {
			if (!unionFind(edge[0], edge[1])) {
				return edge;
			}
		}

		return new int[0];
	}

	private boolean unionFind(int x, int y) {
		int rx = find(x);
		int ry = find(y);

		if (rx == ry)
			return false;

		parent[rx] = ry;
		return true;
	}

	private int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

}
