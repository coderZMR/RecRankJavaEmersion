import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadAndCountPathResult {
    static Map<String, Integer> PathCounter;
    public static void readAndCount(File file, PrintWriter targetFilePW) throws IOException {
        PathCounter = new HashMap<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String path;
        while ((path = bufferedReader.readLine()) != null) {
            if (PathCounter.containsKey(path)) {
                PathCounter.put(path, PathCounter.get(path) + 1);
            }
            else {
                PathCounter.put(path, 1);
            }
        }
        bufferedReader.close();
        fileReader.close();
        writePathAndCount(targetFilePW);
    }
    private static void writePathAndCount(PrintWriter targetFilePW) throws IOException {
        for (String key : PathCounter.keySet()) {
            targetFilePW.println(key + " " + PathCounter.get(key));
        }
    }
}
