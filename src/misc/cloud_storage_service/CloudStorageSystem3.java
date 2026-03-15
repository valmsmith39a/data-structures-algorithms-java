import java.util.*;

public class CloudStorageSystem {

	public String[] cloudStorageService(String[] queries) {

		// ADD_FILE, return true / false 
		// COPY_FILE, return true / false 
		// GET_FILE_SIZE, return file size or ""
		// FIND_FILE - prefix / suffix, return string of file names "name1(70), name2(50)" in descending order 

		String[] res = new String[queries.length];
		Map<String, String> storage = new HashMap<>();
		for (int i = 0; i < queries.length; i++) {
			String command = queries[0];
			switch(command) {
				case "ADD_FILE":
					res[i] = addFile(storage, queries);
				case "COPY_FILE": 

				case "GET_FILE_SIZE":

				case "FIND_FILE": 

				default: 
					break;
			}

		}
		return res;
	}

	private boolean addFile(Map<String, String> storage, String[] queries) {
		String filename = queries[1];
		String file_size = queries[2];
		if (storage.containsKey(filename)) {
			return false;
		}
		storage.put(filename, file_size);
		return true;
	}

	public static void main(String[] args) {

		
	}

}
