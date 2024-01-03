import java.util.*;

/**
 * Problem: Insert Delete Get Random in O(1) time (#380)
 * 
 * Key Insights: 
 * 1. Use a HashMap to store the value as the key and it's index as the value in the map 
 *  a. For remove, use the HashMap to get the removeIndex to find the value to remove in the ArrayList in constant time 
 * 2. Use an ArrayList to store the values for getting a random value 
 *  b. For getting a random value, generate a random index using Random class. 
 * 
 * Time Complexity: O(1)
 * 
 * Space Complexity: O(n), map and array list 
 */
public class InsertDeleteGetRandom {
    private Map<Integer, Integer> valueToIndex;
    private List<Integer> values;
    private Random rand = new Random();

    public RandomizedSet() {
        valueToIndex = new HashMap<>();
        values = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) {
            return false;
        }
        valueToIndex.put(val, values.size());
        values.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) {
            return false;
        }
        int lastElement = values.get(values.size() - 1);
        int removeIndex = valueToIndex.get(val);

        values.set(removeIndex, lastElement);
        valueToIndex.put(lastElement, removeIndex);

        values.remove(values.size() - 1);
        valueToIndex.remove(val);

        return true;
    }

    public int getRandom() {
        return values.get(rand.nextInt(values.size()));
    }
    
}
