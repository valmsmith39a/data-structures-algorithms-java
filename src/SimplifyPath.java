import java.util.*;

/**
 *  Problem: Simplify Path (#71)
 * 
 *  Key insights: 
 *  1. Use a stack to keep track of path 
 *  2. If "..", pop off the stack else if not ".", push to stack 
 * 
 *  Time Complexity: O(n)
 *  Space Complexity: O(n)
 */

public class SimplifyPath {

    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return path;
        }

        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");

        for (String dir : components) {
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!dir.isEmpty() && !dir.equals(".")) {
                stack.push(dir);
            }
        }
        String res = "";
        for (String dir : stack) {
            res += "/" + dir;
        }
        return !res.isEmpty() ? res : "/";
    }
}
