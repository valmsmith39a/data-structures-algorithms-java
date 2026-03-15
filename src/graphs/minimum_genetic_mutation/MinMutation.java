import java.util.*; 
import java.util.LinkedList;
import javafx.util.Pair;
public class MinMutation {


      public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> validSequences = new HashSet<>(Arrays.asList(bank));

        if (!validSequences.contains(endGene)) {
            return -1;
        }

        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(startGene, 0));
        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        while (!queue.isEmpty()) {
            Pair<String, Integer> currentPair = queue.poll();
            String currentSequence = currentPair.getKey();
            int mutationCount = currentPair.getValue();

            if (currentSequence.equals(endGene)) {
                return mutationCount;
            }

             for (int i = 0; i < currentSequence.length(); i++) {
                for (char c : new char[]{'A', 'C', 'G', 'T'}) {
                    StringBuilder sb = new StringBuilder(currentSequence);
                    sb.setCharAt(i, c);
                    String mutated = sb.toString();
                    
                    if (!visited.contains(mutated) && validSequences.contains(mutated)) {
                        visited.add(mutated);
                        queue.offer(new Pair<>(mutated, mutationCount + 1));
                    }
                }
            }
        }
        return -1; 
    }
}
