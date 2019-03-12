import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadAndCountPathResult {
    static Map<String, Integer> PathCounter;
    public static void readAndCount(File file, File targetFile) throws IOException {
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
        writePathAndCount(targetFile);
    }
    private static void writePathAndCount(File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fileWriter);
        for (String key : PathCounter.keySet()) {
            pw.println(key + " " + PathCounter.get(key));
        }
        pw.flush();
        fileWriter.flush();
        pw.close();
        fileWriter.close();
    }
}
