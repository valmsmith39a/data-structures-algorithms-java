import java.util.*;

public class SuffixTrie {
    /**
     *
     *  Suffix Trie 
     *
     *  Time Complexity to create the Suffix Trie: O(n^2), because for each character, iterate through all the characters to the end (like a double for loop)
     *  Space Complexity to create the Suffix Trie: O(n^2) space 
     *
     *  Time Complexity to search for a suffix: O(n) time, because lookup character in hash map of each character 
     *  Space Complexity to search for a suffix: O(1) time, no new memory used to search through suffix trie 
     * 
     *  History: 
     *      Trie: Axel Thue in 1912 was first described by Axel Thue in 1912. Rene de la Briandais, in 1959, described tries in a computer context. In 1960, Edward Fredkin independently described the idea and coined the term trie "tree" (based on "retrieval").
     *
     */
    TrieNode root = new TrieNode();
    char endSymbol = '*';

    public SuffixTrie(String str) {
        populateSuffixTrieFrom(str);
    }

    public class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }

    public void populateSuffixTrieFrom(String str) {
        for (int i = 0; i < str.length(); i++) {
            String word = str.substring(i);
            TrieNode current = root;
            for (Character c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    TrieNode trieNode = new TrieNode();
                    current.children.put(c, trieNode);
                }
                current = current.children.get(c);
            }
            current.children.put(endSymbol, null);
        }
    }
   
    public boolean contains(String str) {
        TrieNode current = root;
        for (Character c : str.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        if (current.children.containsKey(endSymbol)) {
            return true;
        }
        return false;
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
