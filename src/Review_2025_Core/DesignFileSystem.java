package Review_2025_Core;

import java.util.HashMap;
import java.util.Map;

public class DesignFileSystem {

	private Map<String, Integer> paths;

	public DesignFileSystem() {
		this.paths = new HashMap<>();
	}

	public boolean createPath(String path, int value) {
		if (path.isEmpty() || (path.length() == 1 && path.equals("/")) || this.paths.containsKey(path)) {
			return false;
		}

		int delimIndex = path.lastIndexOf("/");
		String parent = path.substring(0, delimIndex);

		if (parent.length() > 1 && !this.paths.containsKey(parent)) {
			return false;
		}

		this.paths.put(path, value);

		return true;
	}

	public int get(String path) {
		return this.paths.getOrDefault(path, -1);
	}
}
