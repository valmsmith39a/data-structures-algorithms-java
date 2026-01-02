package Review_2025;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

	private Deque<Integer> stack;
	private Deque<Integer> minStack;

	public MinStack() {
		stack = new ArrayDeque<>();
		minStack = new ArrayDeque<>();
	}

	public void push(int val) {
		stack.push(val);
		if (minStack.isEmpty()) {
			minStack.push(val);
		} else {
			minStack.push(Math.min(val, minStack.peek()));
		}
	}

	public void pop() {
		stack.pop();
		minStack.pop();
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return minStack.peek();
	}

}
