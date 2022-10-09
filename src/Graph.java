
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class Graph {
	// Node lookup hash table
	public Map<Integer, Node> nodeLookup = new HashMap<>();

	public class Node {
		int id;
		LinkedList<Node> adjacent;

		public Node(int id) {
			this.id = id;
			adjacent = new LinkedList<>();
		}
	}

	public Node getNode(int id) {
		return nodeLookup.get(id);
	}

	public void addNode(int id) {
		nodeLookup.put(id, new Node(id));
	}

	public void addEdge(int source, int destination) {
		Node s = getNode(source);
		Node d = getNode(destination);
		s.adjacent.add(d);
	}

	/**
	 * Depth First Search
	 * Key ideas:
	 * 1. Call DFS on every child node.
	 * 2. Use a Hash Set to track visited nodes
	 * 
	 * Time Complexity: O(V + E), V = number of vertices (visit each vertex), E =
	 * number of edges (visit each child of each vertex)
	 * Space Complexity: O(F), F = number of frames on the call stack, which is the
	 * number of nodes from
	 * source node to destination node, inclusive.
	 * This is because we call DFS recursively on each child node.
	 * Ex
	 * source node: A, destination node: D
	 * A -> B -> C -> D
	 * We call DFS on node A.
	 * Before DFS on node A gets resolved, we call DFS on node B.
	 * Before DFS on node B gets resolved, we call DFS on node C.
	 * Before DFS on node C gets resolved, we call DFS on node D.
	 * At that point, there are 4 frames on the call stack (4 functions called).
	 * Call on node D gets resolved, frame removed from the call stac.
	 * Call on node C gets resolved, frame removed from the call stack,etc.
	 */
	public boolean hasPathDFS(int source, int destination) {
		Node s = getNode(source);
		Node d = getNode(destination);
		Set<Integer> visited = new HashSet<Integer>();
		return hasPathDFS(s, d, visited);
	}

	public boolean hasPathDFS(Node source, Node destination, Set<Integer> visited) {
		// Compares the reference (memory location) of source and destination Node
		// objects
		if (source == destination) {
			return true;
		}
		if (visited.contains(source.id)) {
			return false;
		}
		visited.add(source.id);
		for (Node child : source.adjacent) {
			if (hasPathDFS(child, destination, visited)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Breadth First Search
	 * Key Ideas:
	 * 1. LinkedList nextToVisit to track all the nodes need to visit.
	 * 2. HashSet visited to track all the visited nodes.
	 * Time Complexity: O (V + E), V = number of vertices. E = number of edges.
	 * Space Complexity: O(V), V = number of edges, because nextToVisit stores all
	 * the vertices we need to visit.
	 */
	public boolean hasPathBFS(int source, int destination) {
		Node s = getNode(source);
		Node d = getNode(destination);
		return hasPathBFS(s, d);
	}

	public boolean hasPathBFS(Node source, Node destination) {
		LinkedList<Node> nextToVisit = new LinkedList<Node>();
		Set<Integer> visited = new HashSet<Integer>();
		// Adds to the end ("tail") of the list
		nextToVisit.add(source);
		while (!nextToVisit.isEmpty()) {
			// Removes from the beginning ("head") of the list
			Node node = nextToVisit.remove();
			if (node == destination) {
				return true;
			}
			if (visited.contains(node.id)) {
				continue;
			}
			visited.add(node.id);
			for (Node child : node.adjacent) {
				nextToVisit.add(child);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addNode(0);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);

		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(3, 5);

		// Example of how to access a child node
		// System.out.println("node " + graph.getNode(0).adjacent.get(0).id);
		// output: node 1
		// Example of how to access adjacent list (child nodes)
		// System.out.println("node " + graph.getNode(0).adjacent);
		// output: [Graph$Node@53e25b76, Graph$Node@73a8dfcc]

		// Test depth first search, has path
		// boolean hasPathDFS = graph.hasPathDFS(3, 0); // false
		boolean hasPathDFS = graph.hasPathDFS(0, 5); // true
		System.out.println("has path DFS? " + hasPathDFS);

		// Test breadth first search, if there is a path
		// from source to destination nodes
		// boolean hasPathBFS = graph.hasPathBFS(3, 0); // false
		boolean hasPathBFS = graph.hasPathBFS(0, 5); // true
		System.out.println("has path BFS? " + hasPathBFS);
	}
}
