import java.util.*;

public class BreadthFirstSearch {

    public class Node {
        int id; 
        List<Node> neighbors;

        public Node(int id) {
            this.id = id;
            neighbors = new ArrayList<>();
        }
    }

    public boolean hasPathBFS(Node source, Node destination) {
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(source);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == destination) {
                return true;
            }
            if (visited.contains(node.id)) {
                continue;
            }
            visited.add(node.id);
            for (Node neighbor : node.neighbors) {
                queue.offer(neighbor);
            }
        }
        return false;
    }
}
