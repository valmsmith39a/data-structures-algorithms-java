import java.util.*;

/**
 * Problem: Find the minimum cost to connect all points (minimum spanning tree for an undirected graph).
 *
 * Insights: 
 * 1. For every node, what is the least cost (closest) node it can be connected to?
 * 2. Use Prim's Algorithm.
 *
 * Prim's Algorithm:
 * 1. Choose a starting node
 * 2. Add all adjacent nodes to priority queue (min heap, so min on top in root node).
    a. Priority queue will have the node with the minimum distance to some other node.
 * 3. Select next node to visit (add to minimum spanning tree).
 *  a. Pop top element of priority queue
 *  b. Check if in MST 
 *      i. If not, then add to MST 
 *      ii. If it is, then pop next element from priority queue until find one not in MST and add it to MST. 
 *
 * Differences to Dijkstra's:
 * 1. Next node to visit
 *  a. Prim: node with the shortest distance to some other node that is not in MST (haven't visited yet).
 *  b. Dijkstra's: node with the shortest distance to the source node that haven't visited yet.  
    Time Complexity: O(n^2 * log(n))
        1. Push / Pop in min heap: log(n) time 
        2. Push / Pop n^2 times (find edges for each node with every other node in graph)    
 */
public class MinCostConnectAllPoints {
    
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int minCost = 0;
        int edgesUsed = 0;
        boolean[] inMST = new boolean[n];
        PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());
        heap.add(new Pair(0, 0));
        
        while (edgesUsed < n) {
            Pair<Integer, Integer> topElement = heap.poll();
            int currentWeight = topElement.getKey();
            int currentNode = topElement.getValue();
            
            if (inMST[currentNode]) {
                continue;
            }

            // currentNode is node with smallest cost that is not in MST
            minCost += currentWeight;
            inMST[currentNode] = true;
            edgesUsed++;

            for (int nextNode = 0; nextNode < n; nextNode++) {
                if (!inMST[nextNode]) {
                    int nextWeight = Math.abs(points[nextNode][0] - points[currentNode][0]) + Math.abs(points[nextNode][1] - points[currentNode][1]);
                    heap.add(new Pair(nextWeight, nextNode));
                }
            }
        }
        return minCost;
    } 
}

