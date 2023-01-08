import java.util.*;
/**
 * Problem: Clone Graph
 *
 * Solution: 
 * 	Run depth first search and store cloned nodes in hash map. 
 * 	that maps original node to cloned node. 
 *	
 * Time Complexity: O(V + E)
 * Space Complexity: O(N)
 */
public class CloneGraph {
	// Map old node to the new, cloned node
	Map<Node, Node> visited = new HashMap<>();

	public Node cloneGraph(Node node) {
		if (node == null) {
			return node;
		}
		if (visited.containsKey(node)) {
			return visited.get(node); 
		}
		Node clone = new Node(node.val, new ArrayList());
		visited.put(node, clone);
		for (Node neighbor : node.neighbors) {
			clone.neighbors.add(cloneGraph(neighbor));
		}
		return clone;

	}
}
