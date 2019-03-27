import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        FileWriter fW = null;
        PrintWriter pW = null;

        fW = getFileWriter(allPathFile);
        pW = getPrintWriter(fW);

        WritePathResult.ergodicDir(dotFile, pW, API2Index);

        ClosePWFW(pW, fW);

        System.out.println("Finish Write Path");
        System.out.println("Read And Count PathResult...");

        fW = getFileWriter(countPathFile);
        pW = getPrintWriter(fW);
        ReadAndCountPathResult.readAndCount(allPathFile, pW);

        ClosePWFW(pW, fW);

        System.out.println("Finish Read And Count PathResult");
    }

    private static FileWriter getFileWriter(File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        return new FileWriter(file, true);
    }

    private static PrintWriter getPrintWriter(FileWriter fW) {
        return new PrintWriter(fW);
    }

    private static void ClosePWFW(PrintWriter pW, FileWriter fW) throws IOException {
        pW.flush();
        fW.flush();
        pW.close();
        fW.close();
    }
}
