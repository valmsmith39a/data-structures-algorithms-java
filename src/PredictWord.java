import java.util.Stack;
import java.util.ArrayList;

public class PredictWord {
	/**
	 * “bcloaprokhtelwoahperdlue”, [“bread”, “tea”, “apple”, “car”, “plane”] => [“bread”, “tea”, “apple”];
	 *  https://www.cnet.com/a/img/j2KNm880rmkTBjN7WM2KTAlYwlw=/1200x675/2014/09/20/aa3e0654-6c7d-41fe-8434-505780785e7a/swype-for-ios.jpg
	 */
	public ArrayList<String> searchWords(String userInput, String[] potentialMatches) {
		Stack<Character> userInputStack = new Stack<>();
		String foundWord = ""; 
		ArrayList<String> wordMatches = new ArrayList();

		for (String word : potentialMatches) {
			for (int i = userInput.length() - 1; i >= 0; i--) {
				userInputStack.push(userInput.charAt(i));	
			}
			for (char c : word.toCharArray()) {
				char userInputChar = userInputStack.pop();
				if (c == userInputChar) {
					foundWord = foundWord + Character.toString(userInputChar);
				}
			}
			if (foundWord.equals(word)) {
				wordMatches.add(word);		
			}
		}
		return wordMatches;
	}
}
