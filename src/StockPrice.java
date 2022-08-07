import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StockPrice {
    /**
     * 
     *
     * Time Complexity: O(N log N) time 
     *  1. update: 
     *      a. insert in hash map O(1) time
     *      b. heap - push operation O(log N) time 
     *      c. for N update calls => O (N log N) time 
     *  2. current: O(1) time
     *  3. maximum / minimum: 
     *      a. Pop outdated records. Worst case pop (N - 1) elements  bc 
     *          one of them will be correct. Each pop takes O(log N) time. In worst
     *          case may pop N - 1 elements (many outdated elements). But only 
     *          pop once, so for many maximum calls, will only pop at most N times.      *          Worst case O(N log N) time (N: N maximum or minimum calls + N pops = 2N => N)
     * 
     * Space Complexity: O(N) space
     *
     *
     *
     */
    private int latestTime;
    private Map<Integer, Integer> timestampPriceMap;
    // Min / Max heap to store min/max prices 
    private PriorityQueue<int[]> minHeap, maxHeap;
    private int minPrice = Integer.MAX_VALUE;
    private int maxPrice = 0;

    public StockPrice() {
        latestTime = 0;
        timestampPriceMap = new HashMap<>();
        minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    }

    public void update(int timestamp, int price) {
        latestTime = Math.max(latestTime, timestamp);
        timestampPriceMap.put(timestamp, price);
        minHeap.add(new int[] { price, timestamp });
        maxHeap.add(new int[] { price, timestamp });
    }

    public int current() {
        return timestampPriceMap.get(latestTime);
    }

    public int maximum() {
        int[] top = maxHeap.peek();
        while (timestampPriceMap.get(top[1]) != top[0]) {
            maxHeap.remove();
            top = maxHeap.peek();
        }
        return top[0];
    }


    public int minimum() {
        int[] top = minHeap.peek();
        while(timestampPriceMap.get(top[1]) != top[0]) {
            minHeap.remove();
            top = minHeap.peek();
        }
        return top[0];
    }
    

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);
        stockPrice.update(2, 5);
        System.out.println("current: " + stockPrice.current());
        System.out.println("max: " + stockPrice.maximum());
        stockPrice.update(1, 3);
        System.out.println("max: " + stockPrice.maximum());
        stockPrice.update(4, 2);
        System.out.println("min: " + stockPrice.minimum());
    }
}
