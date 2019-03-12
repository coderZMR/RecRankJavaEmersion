import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, String> API2Index;
        File api2index = new File("");
        File dotFile = new File("");
        File allPathFile = new File("");
        File countPathFile = new File("");
        API2Index = ReadAPI2Index.getAPI2IndexMap(api2index);
        WritePathResult.ergodicDir(dotFile, allPathFile, API2Index);
        ReadAndCountPathResult.readAndCount(allPathFile, countPathFile);
    }
}
