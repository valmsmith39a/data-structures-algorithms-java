package Review_2024;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayList;

/**
 * Course Schedule # 207
 * 
 * Topological Sort (ordered so that each vertext always leads into the next
 * one)
 * Set up adjacency list (HashMap or list of lists (more memory efficient bc
 * don't have to deal with key / values and
 * collision handling mechanisms)). keys are pre-requisites and neighbors are
 * the courses they can lead to.
 * Important: Create inDegree list that tracks the number of incoming vertexes
 * (pre-reqs) for each vertex (course)
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> adj = new HashMap<>();
		for (int[] pre : prerequisites) {
			adj.putIfAbsent(pre[1], new ArrayList<>());
			adj.get(pre[1]).add(pre[0]);
		}
		int[] inDegree = new int[numCourses];
		for (List<Integer> courses : adj.values()) {
			for (int course : courses) {
				inDegree[course]++;
			}
		}
		Queue<Integer> queue = new LinkedList<>();
		int count = 0;
		for (int i = 0; i < numCourses; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
				count++;
			}
		}

		while (!queue.isEmpty()) {
			int course = queue.poll();
			for (int nextCourse : adj.getOrDefault(course, new ArrayList<>())) {
				inDegree[nextCourse]--;
				if (inDegree[nextCourse] == 0) {
					queue.offer(nextCourse);
					count++;
				}
			}
		}
		return count == numCourses;
	}
}
