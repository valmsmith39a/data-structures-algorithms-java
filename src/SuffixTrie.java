import java.util.*;

public class SuffixTrie {
    /**
     *
     * Suffix Trie
     *
     * Time Complexity to create the Suffix Trie: O(n^2), because for each
     * character, iterate through all the characters to the end (like a double for
     * loop)
     * Space Complexity to create the Suffix Trie: O(n^2) space
     *
     * Time Complexity to search for a suffix: O(n) time, because lookup character
     * in hash map of each character
     * Space Complexity to search for a suffix: O(1) time, no new memory used to
     * search through suffix trie
     * 
     * History:
     * Trie: First described by Axel Thue in 1912. Rene de la Briandais, in 1959,
     * described tries in a computer context. In 1960, Edward Fredkin independently
     * described the idea and coined the term trie "tree" (based on "retrieval").
     *
     */
    TrieNode root = new TrieNode();
    char endSymbol = '*';

    public SuffixTrie(String str) {
        populateSuffixTrie(str);
    }

    public class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }

    public void populateSuffixTrie(String str) {
        for (int i = 0; i < str.length(); i++) {
            TrieNode currentNode = root;
            for (int j = i; j < str.length(); j++) {
                char letter = str.charAt(j);
                if (!currentNode.children.containsKey(letter)) {
                    TrieNode newNode = new TrieNode();
                    currentNode.children.put(letter, newNode);
                }
                currentNode = currentNode.children.get(letter);
            }
            currentNode.children.put(endSymbol, null);
        }
    }

    public boolean contains(String str) {
        TrieNode currentNode = root;
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            if (!currentNode.children.containsKey(letter)) {
                return false;
            }
            currentNode = currentNode.children.get(letter);
        }
        return currentNode.children.containsKey(endSymbol);
    }

    public static void main(String[] args) {
        String str1 = "babc";
        SuffixTrie suffixTrie = new SuffixTrie(str1);
        String test1 = "abc";
        String test2 = "bab";
        // true
        System.out.println("Is " + test1 + "a suffix of the string " + str1 + "? " + suffixTrie.contains(test1));
        // false
        System.out.println("Is " + test2 + "a suffix of the string " + str1 + "? " + suffixTrie.contains(test2));
    }
}
