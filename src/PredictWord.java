import java.util.Stack;
import java.util.ArrayList;

public class PredictWord {
	/**
	 * “bcloaprokhtelwoahperdlue”, [“bread”, “tea”, “apple”, “car”, “plane”] =>
	 * [“bread”, “tea”, “apple”, "car"];
	 * https://www.cnet.com/a/img/j2KNm880rmkTBjN7WM2KTAlYwlw=/1200x675/2014/09/20/aa3e0654-6c7d-41fe-8434-505780785e7a/swype-for-ios.jpg
	 */
	public ArrayList<String> searchWords(String userInput, String[] potentialMatches) {
		Stack<Character> userInputStack = new Stack<>();
		String foundWord = "";
		ArrayList<String> wordMatches = new ArrayList<String>();

		for (String word : potentialMatches) {
			for (int i = userInput.length() - 1; i >= 0; i--) {
				userInputStack.push(userInput.charAt(i));
			}
			for (char c : word.toCharArray()) {
				while (userInputStack.size() > 0) {
					char userInputChar = userInputStack.pop();
					if (c == userInputChar) {
						foundWord = foundWord + Character.toString(userInputChar);
						break;
					}
				}
			}
			if (foundWord.equals(word)) {
				wordMatches.add(foundWord);
			}
			userInputStack.clear();
			foundWord = "";
		}
		return wordMatches;
	}

	public static void main(String[] args) {
		String userInput = "bcloaprokhtelwoahperdlue";
		String[] potentialMatches = new String[] { "bread", "tea", "apple", "car", "plane" };
		PredictWord predictWord = new PredictWord();
		ArrayList<String> foundWords = predictWord.searchWords(userInput,
				potentialMatches);
		// Answer: bread, tea, apple, car
		System.out.println("Predicted words:");
		for (String w : foundWords) {
			System.out.println(w);
		}
	}
}
