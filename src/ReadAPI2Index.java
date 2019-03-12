import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadAPI2Index {
    static Map<String, String> API2Index;
    public static Map<String, String> getAPI2IndexMap(File file) throws IOException {
        API2Index = new HashMap<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] temp;
        while ((line = bufferedReader.readLine()) != null) {
            temp = line.split(" -> ");
            API2Index.put(temp[0], temp[1]);
        }
        return API2Index;
    }
}
