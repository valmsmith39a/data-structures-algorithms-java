import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfRecentCalls {

	Deque<Integer> q;

	public NumberOfRecentCalls() {
		q = new ArrayDeque<>();
	}

	public int ping(int t) {
		// evict from front of queue
		while (!q.isEmpty() && q.peek() < t - 3000)
			q.poll();

		q.offer(t);

		return q.size();
	}
}
