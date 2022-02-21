import java.util.Arrays;
import java.util.HashSet;

public class Dijkstra {
	/**
	 * Dijkstra's Algorithm
	 * Find the shortest path between start node and every vertex in the graph
	 * 
	 * Steps:
	 * 	1. Initialize all min distances from start to each vertex to infinity 
	 * 	2. Traverse the graph while visited != number of vertices
	 * 	3. Get the current vertex with min distance and has not been visited
	 *  4. Skip current min distance == infinity (because it means it's an unconnected vertex)
	 * 
	 * Adjacency list: [destination, distance]
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
	public int[] dijkstrasAlgorithm(int start, int[][][]edges) {
		int numberOfVertices = edges.length;
		// initialize all min distances from start to each vertex to infinity
		int[] minDistances = new int[numberOfVertices];
		// simulate infinity with MAX_VALUE
		int MAX_VALUE = Integer.MAX_VALUE;
		Arrays.fill(minDistances, MAX_VALUE);
		// distance from start vertex to start vertex is 0
		minDistances[start] = 0;
		// keep track of visited vertices 
		HashSet<Integer> visited = new HashSet<>();
		
		while(visited.size() != numberOfVertices) {
			int[] currentEdge = getVertexWithMinDistance(minDistances, visited);
			int vertex = currentEdge[0];
			int currentMinDistance = currentEdge[1];
			
			if (currentMinDistance == MAX_VALUE) {
				break;
			}
			
			visited.add(vertex);
			
			for (int[] adjacentEdge : edges[vertex]) {
				int destination = adjacentEdge[0];
				int distanceToDestination = adjacentEdge[1];
				if (Arrays.asList().contains(destination)) {
					continue;
				}
				int newPathDistance = currentMinDistance + distanceToDestination;
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
	
	public int[] getVertexWithMinDistance(int[] minDistances, HashSet<Integer> visited) {
		int currentMinDistance = Integer.MAX_VALUE;
		int vertex = -1;
		int vertexIndex = 0;

		for (int distance : minDistances) {
			if (visited.contains(vertexIndex)) {
				vertexIndex++;
				continue;
			}
			if (distance <= currentMinDistance) {
				vertex = vertexIndex;
				currentMinDistance = distance;
			}
			vertexIndex++;
		}
		return new int[] { vertex, currentMinDistance };
	}

	public static void main(String[] args) {
		int[][][] adjacencyList = new int[4][2][2];
		int index = 0;
		for (int[][] vertex : adjacencyList) {
			if (index == 0) {
				vertex[0] = new int[] {1, 3};
				vertex[1] = new int[] {2, 2};
			}
			if (index == 1) {
				vertex[0] = new int[] {3, 5};
				vertex[1] = new int[] {0, 3};
			}
			if (index == 2) {
				vertex[0] = new int[] {3, 8};
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
