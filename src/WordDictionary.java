import java.util.*;

/**
 * Problem: Word Dictionary
 * 
 * Solution: Trie
 * 
 * Time Complexity: O(M) time: M = number of characters
 * Space Complexity: O(M) space
 */
public class WordDictionary {
	TrieNode trie;

	private class TrieNode {
		Map<Character, TrieNode> children = new HashMap<>();
		boolean word = false;

		public TrieNode() {
		}
	}

	public WordDictionary() {
		trie = new TrieNode();
	}

	public void addWord(String word) {
		TrieNode node = trie;

		for (char ch : word.toCharArray()) {
			if (!node.children.containsKey(ch)) {
				node.children.put(ch, new TrieNode());
			}
			node = node.children.get(ch);
		}
		node.word = true;
	}

	/**
	 * 
	 * bad, dad, mad
	 * 
	 * .ad => try every letter until find, cycle through the root node keys
	 * 
	 * b..
	 * 
	 * b, then any letter, any letter
	 * 
	 * when encounter a '.'
	 * 
	 * usually -> return false if no match
	 * 
	 * encounter a ., then... go down first path
	 * encounter another . go down first path
	 * out of letters, see if there is true at that word
	 */
	private boolean searchInNode(String word, TrieNode node) {
		// iterate through characters in word
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (!node.children.containsKey(ch)) {
				for (char x : node.children.keySet()) {
					if (searchInNode(word.substring(i + 1), node.children.get(x))) {
						return true;
					}
				}
				return false;
			} else {
				node = node.children.get(ch);
			}
		}
		return node.word;
	}

	public boolean search(String word) {
		return searchInNode(word, trie);
	}
}