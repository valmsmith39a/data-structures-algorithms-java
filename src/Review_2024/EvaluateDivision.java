package Review_2024;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Evaluate Division #399
 * 
 * Key Insight:
 * 1. Graph, DFS
 * 
 * Time Complexity: O(E + Q * (V + E)).
 * a. Since E is proportional to V we can replace E with V
 * b. O(V + Q*(V +V)) => O(V + 2QV) => O(V * (1 + 2Q)) => O(Q*V)
 * 
 * Space Complexity: O(V + E)
 * a. graph storage: O(V + E)
 * b. visited set: O(V)
 * c. DFS call stack: O(V)
 * d. O(V + E) + O(V) + O(V) => O(V + E)
 */
public class EvaluateDivision {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		Map<String, Map<String, Double>> graph = new HashMap<>();
		for (int i = 0; i < equations.size(); i++) {
			String numerator = equations.get(i).get(0);
			String denominator = equations.get(i).get(1);
			double value = values[i];

			graph.putIfAbsent(numerator, new HashMap<>());
			graph.putIfAbsent(denominator, new HashMap<>());

			graph.get(numerator).put(denominator, value);
			graph.get(denominator).put(numerator, 1 / value);
		}

		double[] results = new double[queries.size()];
		for (int i = 0; i < queries.size(); i++) {
			String numerator = queries.get(i).get(0);
			String denominator = queries.get(i).get(1);

			if (!graph.containsKey(numerator) || !graph.containsKey(denominator)) {
				results[i] = -1.0;
			} else if (numerator.equals(denominator)) {
				results[i] = 1.0;
			} else {
				Set<String> visited = new HashSet<>();
				results[i] = dfs(graph, numerator, denominator, 1.0, visited);
			}
		}
		return results;
	}

	private double dfs(Map<String, Map<String, Double>> graph, String current, String target, double product,
			Set<String> visited) {
		// Check if the current node has already been visited to avoid cycles
		if (visited.contains(current)) {
			return -1.0;
		}

		// If the current node is the target, return the accumulated product
		if (current.equals(target)) {
			return product;
		}

		// Mark the current node as visited
		visited.add(current);

		// Traverse neighbors
		Map<String, Double> neighbors = graph.get(current);
		for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
			String neighbor = entry.getKey();
			double value = entry.getValue();

			// Recursively calculate the result for the neighbor
			double result = dfs(graph, neighbor, target, product * value, visited);
			if (result != -1.0) {
				return result;
			}
		}

		// Backtrack by removing the current node from the visited set
		visited.remove(current);
		return -1.0;
	}

	public static void main(String[] args) {
		EvaluateDivision evd = new EvaluateDivision();

		List<List<String>> equations = new ArrayList<>();
		equations.add(Arrays.asList("a", "b"));
		equations.add(Arrays.asList("b", "c"));

		double[] values = new double[] { 2.0, 3.0 };
		List<List<String>> queries = new ArrayList<>();
		queries.add(Arrays.asList("a", "c"));
		queries.add(Arrays.asList("b", "a"));
		queries.add(Arrays.asList("a", "e"));
		queries.add(Arrays.asList("a", "a"));
		queries.add(Arrays.asList("x", "x"));

		double[] results = evd.calcEquation(equations, values, queries);
		// [6.0, 0.5, -1.0, 1.0, -1.0]
		System.out.println(Arrays.toString(results));
	}
}
