import java.util.*;

public class NetworkDelayTime {
    Map<Integer, List<Pair<Integer, Ingeter>>> adj = new HashMap<>();

    private void dijkstra(int[] signalReceivedAt, int source, int n) {
        Queue<Pair<Integer. Integer>> pq = new PriorityQueue<Pair<Integer, Integer>>(Comparator.comparing(Pair::getKey));
        pq.add(new Pair(0, source));

        signalReceivedAt[source] = 0;

        while (!pa.isEmpty()) {
            Pair<Integer, Integer> topPair = pq.remove();

            int currentNode = topPair.getValue();
            int currentNodeTime = topPair.getKey();
            
            if (currentNodeTime > signalReceivedAt[currentNode]) {
                continue;
            }

            // If node was not initial times array parameter 
            if (!adj.containsKey(currentNode)) {
                continue;
            }

            /**
             * Update min response time for each neighbor node to current node.
             * Add updated node to Priority Queue
             */ 
            for (Pair<Integer, Integer> edge : adj.get(currentNode)) {
                // time between current node and neighbor node 
                int time = edge.getKey();
                int neighborNode = edge.getValue();
                if (signalReceivedAt[neighborNode] > currentNodeTime + time) {
                    signalReceivedAt[neighborNode] = currentNodeTime + time;
                    pq.add(new Pair(signalReceivedAt[neighborNode], neighborNode));
                }
            }
        }
    }

    /**
     * times: delay times from one node to another.
     * n: number of nodes 
     * k: start node
     */ 
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list 
        for (int[] time : times) {
            int s = time[0];
            int d = time[1];
            int travelTime = time[2];
            adj.putIfAbsent(s, new ArrayList<>());
            adj.get(s).add(new Pair(travelTime, d));
        }
        
        int signalReceivedAt = new int[n + 1];
        Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);
        // Compute the min delay times from start node to every other node. 
        dijkstra(signalReceivedAt, k, n);

        // Maximum delay time  will be the amount of time to reach all nodes        
        int maxDelayTime = Integer.MIN_VALUE:
        for (int i = 1; i < n; i++) {
            maxDelayTime = Math.max(maxDelayTime);
        }
        // If maxDelayTime is MAX_Value, means at least one node not reachable.
        return maxDelayTime == Integer.MAX_VALUE ? -1 : maxDelayTime;
    }
}

