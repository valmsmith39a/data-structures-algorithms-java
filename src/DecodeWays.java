import java.util.HashMap;
import java.util.Map;

class DecodeWays {
    
    Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodings(String s) {
        return recursiveWithMemo(0, s);
    }
    
    private int recursiveWithMemo(int index, String str) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        
        if (index == str.length()) {
            return 1;
        }


        if (str.charAt(index) == '0') {
            return 0;
        }

        if (index == str.length() - 1) {
           return 1;
        }

        // Similar to going left / right in a binary tree
        int ans = recursiveWithMemo(index + 1, str);
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
            ans += recursiveWithMemo(index + 2, str);
        }

        memo.put(index, ans);
        return ans;
    }

    public static void main(String[] args) {
        DecodeWays decode = new DecodeWays();
        // Remember to run cases one at a time bec hashmap saved, will affect next solution
        // System.out.println("123 decoded is: " + decode.numDecodings("123"));
        System.out.println("2326 decoded is: " + decode.numDecodings("2326"));
    }
}
