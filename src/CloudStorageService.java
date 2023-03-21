import java.util.*;

public class CloudStorageService {

    Map<String, String> storage = new HashMap<>();

    public String[] cloudStorageService(String[][] queries) {

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            String command = query[i];
            String[] res = new String[queries.length];

            switch (command) {
                case "ADD_FILE": 
                    res[i] = addFile(query);
                    break;
                case "COPY_FILE": 
                    break;
                
                case "GET_FILE_SIZE": 
                    break;

                case "FIND_FILE": 
                    res[i] = findFile(query);
                    break;

            }
        }
    }

    private boolean addFile(String[] query) {
        String filename = query[1];
        String file_size = query[2];
        if (storage.containsKey(filename)) {
            return false;
        }
        storage.put(filename, file_size);
        return true;
    }

    private String findFile(String[] query) {
        String filename = query[1];
        String prefix = query[2];
        String suffix = query[3];
        List<String[]> filesFound = new ArrayList<>();
        Map.Entry<String,String> filenames = storage.entrySet(); 
        String filesFoundStr = ""; 
        for (String filename : filenames) {
            if (filename.startsWith(prefix) && filename.endsWith(suffix)) {
                filesFound.add(new int[] { filename.getKey(), filename.getValue() }; 
            }
        }
        filesFound.sort((a, b) -> b - a);
        for (int i = 0; i < filesFound.size(); i++) {
            filesFoundStr += file[0] + "(" + file[1] + ")";
            if (i < filesFound.size() - 1) {
                filesFoundStr += ", ";
            }
        }
    }
}
