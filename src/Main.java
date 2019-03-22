import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, String> API2Index;
        File api2index = new File("/home/x/mydisk/RecRank/apiNew.txt");
        File dotFile = new File("/home/x/mydisk/Gralan/wjwase/datafile2");
        File allPathFile = new File("/home/x/mydisk/RecRank/allPathFile.txt");
        File countPathFile = new File("/home/x/mydisk/RecRank/countPathFile.txt");
        System.out.println("Read API2Index...");
        API2Index = ReadAPI2Index.getAPI2IndexMap(api2index);
        System.out.println("Finish Read API2Index");
        System.out.println("Write Path...");
        WritePathResult.ergodicDir(dotFile, allPathFile, API2Index);
        System.out.println("Finish Write Path");
        System.out.println("Read And Count PathResult...");
        ReadAndCountPathResult.readAndCount(allPathFile, countPathFile);
        System.out.println("Finish Read And Count PathResult");
    }
}
