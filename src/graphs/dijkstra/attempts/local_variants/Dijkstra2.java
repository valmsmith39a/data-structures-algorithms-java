import java.util.*;

public class Dijkstra2 {
	public int[] getMinDistancesList(int startVertexIndex, int[][][] edges) {
		int numberOfVertices = edges.length;
		int[] minDistances = new int[numberOfVertices];
		Arrays.fill(minDistances, Integer.MAX_VALUE);
		minDistances[startVertexIndex] = 0;
		Set<Integer> visited = new HashSet<>();

		while (visited.size() != numberOfVertices) {
			int[] currentVertex = getCurrentVertex(minDistances, visited);
			int currentVertexIndex = currentVertex[0];
			int currentVertexMinDistance = currentVertex[1];
			if (currentVertexMinDistance == Integer.MAX_VALUE) {
				break;
			}
			visited.add(currentVertexIndex);
			for (int[] adjacentVertex : edges[currentVertexIndex]) {
				int adjacentVertexIndex = adjacentVertex[0];
				int distanceToAdjacentVertex = adjacentVertex[1];
				if (visited.contains(adjacentVertexIndex)) {
					continue;
				}
				int currentAdjacentVertexMinDistance = minDistances[adjacentVertexIndex];
				int newAdjacentVertexMinDistance = currentVertexMinDistance + distanceToAdjacentVertex;
				if (newAdjacentVertexMinDistance < currentAdjacentVertexMinDistance) {
					minDistances[adjacentVertexIndex] = newAdjacentVertexMinDistance;
				}
			}
		}
		for (int i = 0; i < minDistances.length; i++) {
			if (minDistances[i] == Integer.MAX_VALUE) {
				minDistances[i] = -1;
			}
		}
		return minDistances;
	}

	private int[] getCurrentVertex(int[] minDistances, Set<Integer> visited) {
		int minDistance = Integer.MAX_VALUE;
		int minDistanceVertexIndex = -1;
		for (int i = 0; i < minDistances.length; i++) {
			if (visited.contains(i)) {
				continue;
			}
			if (minDistances[i] < minDistance) {
				minDistance = minDistances[i];
				minDistanceVertexIndex = i;
			}
		}
		return new int[] { minDistanceVertexIndex, minDistance };
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
		Dijkstra2 dijkstra = new Dijkstra2();
		int[] minDistances = dijkstra.getMinDistancesList(startingVertex, adjacencyList);
		// Solution: 0, 3, 2, 8
		System.out.println("Dijkstra's Algorithm, min distances: ");
		for (int distance : minDistances) {
			System.out.println(distance);
		}
	}
}
