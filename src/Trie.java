import java.util.HashMap;
import java.util.Map;

/**
 * Trie 
 * 
 * Key Points: 
 * 1. TrieNode: children stored in hash map 
 * 2. isEndOfWord flag 
 * 
 * Time Complexity: 
 * 1. insert: O(n), n is the number of characters in the word 
 * 2. search: O(n)
 * 
 * Space Complexity: 
 * 1. insert: O(n)
 * 2. search: O(1), memory used does not scale with the size of the input 
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    private class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean isEndOfWord;

        private TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!current.children.containsKey(ch)) {
                current.children.put(ch, new TrieNode());
            }
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return current.isEndOfWord;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        System.out.println(trie.search("hello")); // true 
        System.out.println(trie.search("he"));    // false 
        System.out.println(trie.search("world")); // false 
    }
}
