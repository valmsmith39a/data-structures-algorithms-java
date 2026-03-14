package Review_2025_Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Time Complexity: O(V + E)
 * Space Complexity: O(V):
 * Average Case: O(Height of Graph) -> O(log V)
 * Worst Case: Skewed Graph (A - B - C): O(Height of Graph) -> O(V)
 * 
 * c - a - b
 * |
 * d
 * {
 * a: [b, c ],
 * b: [d]
 * }
 * 
 * node clone visited neighbors clone neighbors end of function
 * a a a:a b, c b
 * b b b:b d d return clone b
 * d d d:d null, ret clone d
 * -
 * c c c:c null, ret clone c c return clone c
 * return clone a
 */

public class CloneGraph {
	private Map<Node, Node> visited = new HashMap<>();

	public Node cloneGraph(Node node) {
		if (node == null) {
			return null;
		}

		if (visited.containsKey(node)) {
			return visited.get(node);
		}

		Node clone = new Node(node.val, new ArrayList<>());
		visited.put(node, clone);

		for (Node nei : node.neighbors) {
			clone.neighbors.add(cloneGraph(nei));
		}

		return clone;
	}
}
