import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestForCreateDataSet {
    static Map<String, String> API2Index;

    public static void main(String[] args) throws IOException {
        API2Index = ReadAPI2Index.getAPI2IndexMap(new File("/Users/zhangmingrui/Desktop/RecRankJavaEmersion/APIIndexMap.txt"));
        ConstructGroum CG = new ConstructGroum();
        Groum groum1 = CG.constructGroum("/Users/zhangmingrui/Desktop/RecRankJavaEmersion/dotFile/Demo.resizeArray.dot", API2Index);
        Groum groum2 = CG.constructGroum("/Users/zhangmingrui/Desktop/RecRankJavaEmersion/dotFile/Demo.resizeArray1.dot", API2Index);
        List<Groum> groumList = new ArrayList<Groum>();
        groumList.add(groum1); groumList.add(groum2);
        List<String> results = new ArrayList<String>();
        results.add("Array.newInstance");
        results.add("Class.getComponentType");
        results.add("CONTROL.IF");
        results.add("Array.getLength");
        results.add("Math.min");

        int qId = 1;

        FileWriter fW = getFileWriter(new File("/Users/zhangmingrui/Desktop/RecRankJavaEmersion/DataSet.txt"));
        PrintWriter pW = getPrintWriter(fW);

        for (Groum groum : groumList){
            CreateDataSet.create(groum, results, qId, new File("/Users/zhangmingrui/Desktop/RecRankJavaEmersion/PathCount.txt"), pW, API2Index);
            ++qId;
        }

        ClosePWFW(pW, fW);
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
