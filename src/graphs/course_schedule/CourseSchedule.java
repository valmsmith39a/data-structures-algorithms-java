import java.util.*;

public class CourseSchedule {
	
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> courseMap = new HashMap<>();
		
		for (int[] edge : prerequisites) {
			if (courseMap.containsKey(edge[0])) { 
				courseMap.get(edge[0]).add(edge[1]);
			} else {
				List<Integer> nextCourses = new LinkedList<>();
				nextCourses.add(edge[1]);
				courseMap.put(edge[0], nextCourses);
			}
		}
		boolean[] checked = new boolean[numCourses];
		boolean[] visited = new boolean[numCourses];

		for (int i = 0; i < numCourses; i++) {
			if (isCyclic(i, courseMap, checked, visited)) {
				return false;
			}
		}
		return true;
	}

   	private boolean isCyclic(int course, Map<Integer, List<Integer>> courseMap, boolean[] checked, boolean[] visited) {
		if (checked[course]) {
			return false;
		}
		if (visited[course]) {
			return true;
		}
		if (!courseMap.containsKey(course)) {
			return false;
		}
		visited[course] = true;
		boolean isCyclic = false;
		for (int childCourse : courseMap.get(course)) {
			isCyclic = isCyclic(childCourse, courseMap, checked, visited);
			if (isCyclic) {
					break;
			}
		}
		visited[course] = false;
		checked[course] = true;
		return isCyclic;
	}

}
