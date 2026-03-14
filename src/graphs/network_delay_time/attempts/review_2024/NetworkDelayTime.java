public class NetworkDelayTime {
    /**
     * Time Complexity: 
     *  iterate through each vertex and remove (poll): O(V log V)
     *      iterate through each vertex: O(V)
     *      remove (poll): O(log V)
     *  iterate through each edge (connection between vertices) and insert (offer): O(E log V)
     *      interate through each edge: O(E)
     *      insert (offer): O(log V)
     * 
     * Space Complexity: 
     *   Total: O(E + V)
     *      Graph: O(E + V)
     *      Priority Queue: O(V)
     *      Visited Set: O(V)
     *  
     */

     public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[] { time[1], time[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] { k, 0 });

        Set<Integer> visited = new HashSet<>();

        int maxTime = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int time = current[1];
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            maxTime = Math.max(maxTime, time);
            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int edgeNode = edge[0];
                    int edgeTime = edge[1];
                    int timeFromStartNode = time + edgeTime;
                    pq.offer(new int[] { edgeNode, timeFromStartNode });
                }
            }
        }
        return visited.size() == n ? maxTime : -1;
    }
}
