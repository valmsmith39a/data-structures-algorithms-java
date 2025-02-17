package Review_2025;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}

		int[] indegree = new int[numCourses];

		for (int[] pre : prerequisites) {
			int course = pre[0];
			int prereq = pre[1];
			graph.get(prereq).add(course);
			indegree[course]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		List<Integer> order = new ArrayList<>();

		while (!queue.isEmpty()) {
			int current = queue.poll();
			order.add(current);
			for (int neighbor : graph.get(current)) {
				indegree[neighbor]--;
				if (indegree[neighbor] == 0) {
					queue.offer(neighbor);
				}
			}
		}

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
