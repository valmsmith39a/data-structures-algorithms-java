/**
 * Design Add / Search Words #211
 * 
 * Key Insights: 
 * 1. Use a Trie datastruture 
 * 
 * Time Complexity: 
 *  add word: O(m), m = length of the word  
 *  search: O(26^m) worst case, O(b^m), b = branching factor, m = length of word 
 *  branching factor: number of times trie splits. Ex store dog, cat, the branching factor for first layer is 2 
 *      To get branching factor, you add up all the branching factors at each node with children and get the average. 
 *      So lets say 3 nodes have children and branching factor at each node is 2, 3, 1, the branching factor is 6 / 2 = 3 
 * 
 * Space Complexity: 
 *   add word: O(m), Trie structure if each character is unique 
 *   search word: O(m), for recursive call stack 
 */
class DesignAddSearchWords {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    private boolean searchHelper(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isEndOfWord;
        }
        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode child : node.children.values()) {
                if (searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            TrieNode child = node.children.get(c);
            return child != null && searchHelper(word, index + 1, child);
        }
    }

    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
}