import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 286. Walls and Gates
 * Strategy: Multi-source BFS
 * Time Complexity: O(m*n), because we visit each cell at most once.
 * Space Complexity: O(m*n), because in the worst case, we might have to add all
 * the cells to the queue (if all cells are gates).
 */

public class WallsAndGates {

	int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public void wallsAndGates(int[][] rooms) {
		Deque<int[]> q = new ArrayDeque<>();
		for (int r = 0; r < rooms.length; r++) {
			for (int c = 0; c < rooms[r].length; c++) {
				if (rooms[r][c] == 0) {
					q.offer(new int[] { r, c });
				}
			}
		}

		while (!q.isEmpty()) {
			int[] g = q.poll();
			int gr = g[0];
			int gc = g[1];

			for (int[] dir : DIRS) {
				int r = gr + dir[0];
				int c = gc + dir[1];

				if (r < 0 || r >= rooms.length || c < 0 || c >= rooms[r].length)
					continue;

				if (rooms[r][c] == Integer.MAX_VALUE) {
					rooms[r][c] = rooms[gr][gc] + 1;
					q.offer(new int[] { r, c });
				}
			}
		}
	}
}
