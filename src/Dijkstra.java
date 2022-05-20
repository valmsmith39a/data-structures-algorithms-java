import java.util.Arrays;
import java.util.HashSet;

public class Dijkstra {
	/**
	 * Dijkstra's Algorithm
	 * 
	 * Background:
	 * Is a greedy algorithm: make the locally optimal choice at each stage
	 * Uses dynamic programming: recursively break down a problem into simpler
	 * sub-problems
	 * and find the optimal solutions to the sub-problems.
	 * Example algorithms:
	 * Dijkstra's algorithm for shortest path
	 * Fibonacci sequence
	 * Tower of Hanoi
	 * 
	 * Problem statement:
	 * Find the shortest path between start node and every vertex in the graph.
	 * 
	 * Return the minimumm distances from the starting vertex to each of the other
	 * vertices in the graph.
	 * 
	 * Given a start vertexIndex and an adjacency list (below), return
	 * an array with each element representing the minimum distance between the
	 * vertex at that index and the vertax at the start index.
	 * 
	 * Summary:
	 * 1. From the start vertex, visit the next vertex which has not been visited
	 * and has the shortest
	 * distance from the start vertex.
	 * 2. At each vertex, visit the adjacent vertices and for each vertex, update
	 * the minimum distance
	 * between the vertex and the start vertex.
	 * 
	 * Steps:
	 * 1. Initialize all min distances from start to each vertex to infinity
	 * 2. Traverse the graph while visited != number of vertices
	 * 3. Get the current vertex with min distance and has not been visited
	 * 4. Skip current min distance == infinity (because it means it's an
	 * unconnected vertex)
	 * 
	 * Adjacency list.
	 * Edge: [destination, distance]
	 * Index 0 has outbound edges of 1 and 3.
	 * 0: [[1, 3], [2, 2]]
	 * 1: [[3, 5]]
	 * 2: [[3, 8]]
	 * 3: []
	 * 
	 * Solution:
	 * Dijkstra's Algorithm, min distances:
	 * 0
	 * 3
	 * 2
	 * 8
	 * 
	 */
	public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
		// Used for stopping condition.
		int numberOfVertices = edges.length;
		// Instantiate and allocate memory for array to track min distances.
		int[] minDistances = new int[numberOfVertices];
		// Initialize each min distance with infinity.
		int MAX_VALUE = Integer.MAX_VALUE;
		Arrays.fill(minDistances, MAX_VALUE);
		// Initialize the min distance for the start vertex.
		// Distance from start vertex to start vertex is 0
		minDistances[start] = 0;
		// Track the visited vertices
		HashSet<Integer> visited = new HashSet<>();

		/*
		 * *
		 * In each iteration:
		 * 1. Get the vertex with the minimum distance from the start
		 * vertex which has not been visited.
		 * 2. Visit each outbound vertex and update the minDistances array
		 */
		while (visited.size() != numberOfVertices) {
			int[] currentEdge = getVertexWithMinDistance(minDistances, visited);
			int vertex = currentEdge[0];
			int currentMinDistance = currentEdge[1];

			// If currentMinDistance is infinity, tha vertex
			// is not connected to the graph and all the
			// vertices that are connected to the graph have been visited.
			if (currentMinDistance == MAX_VALUE) {
				break;
			}

			// Track visited vertices
			visited.add(vertex);

			// Visit all adjacent vertices to your current vertex
			// Ex adjacentEdge: [destination, distance]
			for (int[] adjacentEdge : edges[vertex]) {
				int destination = adjacentEdge[0];
				int distanceToDestination = adjacentEdge[1];
				if (visited.contains(destination)) {
					continue;
				}
				// New potential min distance from destination to starting node
				int newPathDistance = currentMinDistance + distanceToDestination;
				// Current min distance from destination to starting node
				int currentDestinationDistance = minDistances[destination];
				if (newPathDistance < currentDestinationDistance) {
					minDistances[destination] = newPathDistance;
				}
			}
		}
		return Arrays.stream(minDistances).map(distance -> {
			if (distance == MAX_VALUE) {
				return -1;
			}
			return distance;
		}).toArray();
	}

	// Get the next vertex, which has not been visited, with the minimum distance to
	// the starting vertex.
	public int[] getVertexWithMinDistance(int[] minDistances, HashSet<Integer> visited) {
		int currentMinDistance = Integer.MAX_VALUE;
		int vertex = -1;

		for (int vertexIndex = 0; vertexIndex < minDistances.length; vertexIndex++) {
			if (visited.contains(vertexIndex)) {
				continue;
			}
			// Ex startIndex = 2. 5 total vertices.
			// For index = 0, distance = infinity
			// infinity <= infinity, so vertex = 0, currentMinDistance = infinity
			// For startIndex = 2, distance = 0, vertex = 2 and currentMinDistance = 0
			// Subsequent vertices will have distances (infinity)
			// greater than currentMinDistance and will skip the if block
			// We can then get the vertex index and the distance of the
			// min distance from the start index
			int distance = minDistances[vertexIndex];
			if (distance <= currentMinDistance) {
				vertex = vertexIndex;
				currentMinDistance = distance;
			}
		}
		// Return -1 for distance for vertices disconnected from the rest of the graph.
		return new int[] { vertex, currentMinDistance };
	}

	public static void main(String[] args) {
		int[][][] adjacencyList = new int[4][2][2];
		int index = 0;
		for (int[][] vertex : adjacencyList) {
			if (index == 0) {
				vertex[0] = new int[] { 1, 3 };
				vertex[1] = new int[] { 2, 2 };
			}
			if (index == 1) {
				vertex[0] = new int[] { 3, 5 };
				vertex[1] = new int[] { 0, 3 };
			}
			if (index == 2) {
				vertex[0] = new int[] { 3, 8 };
			}
			index++;
		}
		int startingVertex = 0;
		Dijkstra dijkstra = new Dijkstra();
		int[] minDistances = dijkstra.dijkstrasAlgorithm(startingVertex, adjacencyList);
		System.out.println("Dijkstra's Algorithm, min distances: ");
		for (int distance : minDistances) {
			System.out.println(distance);
		}
	}
}
