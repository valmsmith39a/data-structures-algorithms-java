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
		Map<Integer, List<Integer>> graph = new HashMap<>();
		int[] inDegree = new int[numCourses];

		for (int[] pre : prerequisites) {
			int course = pre[0];
			int prereq = pre[1];
			graph.putIfAbsent(prereq, new ArrayList<>());
			graph.get(prereq).add(course);
			inDegree[course]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		int processedCourses = 0;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			processedCourses++;

			List<Integer> courses = graph.getOrDefault(current, new ArrayList<>());
			for (int course : courses) {
				inDegree[course]--;
				if (inDegree[course] == 0) {
					queue.offer(course);
				}
			}
		}
		return processedCourses == numCourses;
	}
}
