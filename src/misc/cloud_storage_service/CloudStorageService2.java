import java.util.*;

public class CloudStorageService2 {

    public String[] cloudStorageService(String[] queries) {
        Map<String, String> storage = new HashMap<>();
        String[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            String command = query[0];
            switch(command) {
                case "ADD_FILE": 
                    res[i] = addFile(storage, query);
                    break;
                case "COPY_FILE":
                    res[i] = copyFile(storage, query);
                    break;
                case "GET_FILE_SIZE": 
                    break;
                case "FIND_FILES": 
                    res[i] = findFiles(storage, query);
                    break;
                default: 
                    break;
            }
        }
        return res;
    }

    private String addFile(Map<String, String> storage, String query) {
        String filename = query[1];
        String file_size = query[2];
        if (storage.containsKey(filename)) {
            return "false";
        }
        storage.put(filename, file_size);
        return "true";
    }

    private String copyFile(Map<String, String> storage, String query) {
        String fromName = query[1];
        String toName = query[2];
        if (!storage.containsKey(fromName) || storage.containsKey(toName)) {
            return "false";
        }
        storage.put(toName, storage.get(fromName));
        return "true";
    }

    private String findFile(Map<String, String> storage, String query) {
        String prefix = query[1];
        String suffix = query[2];
        Set<String> names = storage.keySet();
        List res = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith(prefix) && name.endsWith(suffix)) {
                EntrySet<String, String> file = new EntrySet<>();
                file.add(name, storage.get(name));
                res.add(file);
            }
        }
        res.sort((a, b) -> b - a);
        String resStr = "";
        for (EntrySet<String, String> file : res) {
            resStr += file.getKey() + "(" + file.getValue() + ")";
        }
    }
}
