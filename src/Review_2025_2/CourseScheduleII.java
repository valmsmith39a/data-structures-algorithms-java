package Review_2025_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Course Schedule 2
 * Techniques:
 * 1. Topological sort:
 * a. Linear ordering of nodes in Directed Acyclic Graph.
 * b. Sorted so that for every node A -> B, A is before B.
 * 2. BFS
 * 3. Adjacency list
 * 
 * Time Complexity: O(V + E)
 * O(V + E) + O(V) + O(V + E) + O(V) = 2 O(V + E) + 2 O(V)
 * Drop constants
 * O(V + E) is dominant term over O(V)
 * So: O(V + E)
 * 
 * Space Complexity: O(V + E)
 * O(V + E) + O(V) + O(V) + O(V) + O(V) = O(V + E)
 */
public class CourseScheduleII {

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		// Build the adjacency list
		// O(V) time.
		Map<Integer, List> graph = new HashMap<>();
		for (int i = 0; i < numCourses; i++) {
			// O(1)
			graph.put(i, new ArrayList<>());
		}

		// Build indegree array by iterating over edges (connections between vertexes)
		// indegree of a node: number of inbound edges (prerequisites) into node
		// (course)
		// O(E) time.
		int[] indegree = new int[numCourses];
		for (int[] pre : prerequisites) {
			int prereq = pre[1];
			int course = pre[0];
			graph.get(prereq).add(course);
			indegree[course]++;
		}
		// Build queue for BFS
		// O(V) time
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		// BFS: Process each node in queue and add to queue
		// O(V + E)
		List<Integer> order = new ArrayList<>();
		while (!queue.isEmpty()) {
			int current = queue.poll();
			order.add(current);
			List<Integer> neighbors = graph.get(current);
			for (int nei : neighbors) {
				indegree[nei]--;
				if (indegree[nei] == 0) {
					queue.offer(nei);
				}
			}
		}

		// Convert to int array. Size of order same as numCourses means no cycles
		// O(V) time.
		if (order.size() == numCourses) {
			int[] result = new int[numCourses];
			for (int i = 0; i < numCourses; i++) {
				result[i] = order.get(i);
			}
			return result;
		} else {
			return new int[0];
		}
	}
}
