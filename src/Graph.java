
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Graph {
	// node lookup hash table
	private HashMap<Integer, Node> nodeLookup = new HashMap<Integer, Node>();

	public static class Node {
		private int id;
		LinkedList<Node> adjacent = new LinkedList<Node>();
		private Node (int id) {
			this.id = id;
		}
	}
	
	private Node getNode(int id) {
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
	
	public boolean hasPathDFS(int source, int destination) {
		Node s = getNode(source);
		Node d = getNode(destination);
		HashSet<Integer> visited = new HashSet<Integer>();
		return hasPathDFS(s, d, visited);
	}
	
	public boolean hasPathDFS(Node source, Node destination, HashSet<Integer> visited) {
		if(visited.contains(source.id)) {
			return false;
		}
		visited.add(source.id);
		if (source == destination) {
			return true;
		}
		for (Node child : source.adjacent) {
			if (hasPathDFS(child, destination, visited)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasPathBFS(int source, int destination) {
		Node s = getNode(source);
		Node d = getNode(destination);
		return hasPathBFS(s, d);
	}
	
	public boolean hasPathBFS(Node source, Node destination) {
		LinkedList<Node> nextToVisit = new LinkedList<Node>();
		HashSet<Integer> visited = new HashSet<Integer>();
		nextToVisit.add(source);
		while (!nextToVisit.isEmpty()) {
			Node node = nextToVisit.remove();
			if (node == destination) {
				return true;
			}
			
			if (visited.contains(node.id) ) {
				continue;
			}
			visited.add(node.id);
			
			for (Node child: node.adjacent) {
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
