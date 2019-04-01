import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadAndCountPathResult {
    static Map<String, Integer> pathCounter;
    public static void readAndCount(File file, PrintWriter targetFilePW) throws IOException {
        pathCounter = new HashMap<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String path;
        while ((path = bufferedReader.readLine()) != null) {
            if (pathCounter.containsKey(path)) {
                pathCounter.put(path, pathCounter.get(path) + 1);
            }
            else {
                pathCounter.put(path, 1);
            }
        }
        bufferedReader.close();
        fileReader.close();
        writePathAndCount(targetFilePW);
    }
    private static void writePathAndCount(PrintWriter targetFilePW) throws IOException {
        for (String key : pathCounter.keySet()) {
            targetFilePW.println(key + " " + pathCounter.get(key));
        }
    }
}
