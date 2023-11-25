import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTimePQ {

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[] {time[1], time[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[] {k, 0});

        Map<Integer, Integer> delayMap = new HashMap<>();

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int delay = current[1];

            if (delayMap.containsKey(node)) {
                continue;
            }

            delayMap.put(node, delay);

            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int neighbor = edge[0];
                    int newDelay = delay + edge[1];
                    
                    if (!delayMap.containsKey(neighbor)) {
                        pq.add(new int[]{neighbor, newDelay});
                    }
                }
            }
        }

        if (delayMap.size() != n) {
            return -1;
        }

        return Collections.max(delayMap.values());
    }


}
