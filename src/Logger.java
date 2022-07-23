import java.util.HashMap;

/**
 * Logger Rate Limiter
 * 
 * Resource:
 * https://leetcode.com/problems/logger-rate-limiter/
 * 
 * Problem:
 * Design a logger system that receives a stream of messages with timestamps.
 * Each unique message should only be printed at most once every 10 seconds.
 * All messages in chronological order.
 * Several messages may arrive at same timestamp.
 * 
 * Implement:
 * Logger() initialize the logger object
 * shouldPrintMessage(int timestamp, string message) {
 * if message should be printed given the timestamp return
 * true, otherwise return false.
 * }
 * 
 * Input:
 * [[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11,
 * "foo"]]
 * 
 * Problem Summary:
 * Design a logger system:
 * 1. Receives a stream of messages with timestamps.
 * [[], [1, "foo"], [2, "bar"], [3, "foo"], [11, "foo"]]
 * 
 * 2. Each unique message only printed at most once every 10 seconds.
 * 
 * Summary of Implementation:
 * 1. Track message and timestamp in a hash table
 * key: message
 * value: timestamp
 * 2. If message does not exist, add the message and timestamp to hash table and
 * return true.
 * 3. If message does exist and the difference between new timestamp and old
 * timestamp is >= 10,
 * update timestamp value and return true, else return false.
 * 
 * Time Complexity:
 * O(1) time: lookup and update of hashtable takes constant time.
 * 
 * Space Complexity:
 * O(M) space: size of all incoming messages.
 * 
 */
public class Logger {
    private HashMap<String, Integer> msgHashMap;

    public Logger() {
        msgHashMap = new HashMap<String, Integer>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!this.msgHashMap.containsKey(message)) {
            this.msgHashMap.put(message, timestamp);
            return true;
        }

        Integer oldTimestamp = this.msgHashMap.get(message);
        if (timestamp - oldTimestamp >= 10) {
            this.msgHashMap.put(message, timestamp);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Object[][] array = new Object[][] { { 1, "foo" }, { 2, "bar" }, { 3, "foo" }, { 8, "bar" }, { 10, "foo" },
                { 11, "foo" } };
        Logger logger = new Logger();
        boolean shouldPrintMessage = false;
        for (int i = 0; i < array.length; i++) {
            shouldPrintMessage = logger.shouldPrintMessage((int) array[i][0], (String) array[i][1]);
            if (shouldPrintMessage) {
                System.out.println(shouldPrintMessage);
            } else {
                System.out.println(shouldPrintMessage);
            }
        }
    }
}
