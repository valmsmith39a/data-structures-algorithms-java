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
                    res[i] = copyFile(query);
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

    private boolean copyFile(String[] query) {
        String filename = query[1];
        String newFilename = query[2];
        if (!storage.containsKey(filename) && storage.containskey(newFilename)) {
            return false; 
        }
        storage.put(newFilename, storage.get(filename));
        return true;
    }

    private String findFile(String[] query) {
        String filename = query[0];
        String prefix = query[1];
        String suffix = query[2];
        Set<String> filenames = storage.keySet();
        List<String[]> foundNames = new ArrayList<>();

        for (int i = 0; i < filenames.length; i++) {
            String filename = filenames[i];
            if (filename.startsWith(prefix) && filename.endsWith(suffix)) {
                foundNames.add(new String[] { filename, storage.get(filename)});
            }
        }
        Collections.sort(foundNames, (a, b) -> Integer.value(b[1]) - Integer.value(a[1]));
        String foundStr = "";
        for (int i = 0; i < foundNames.length; i++) {
            foundStr += foundNames.get(i)[0] + "(" + foundNames.get(i)[1] + ")";
            if (i < foundNames.length - 1) {
                foundStr += ", ";
            }
        }
        return foundStr;
    }
}
