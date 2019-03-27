import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WritePathResult {
    private static int count = 0;

    public static void ergodicDir(File dir, PrintWriter targetFilePW, Map<String, String> API2IndexMap) throws IOException {

        if(dir.isDirectory()){
            for(File file : dir.listFiles()){
                if(file.isDirectory()){
                    System.out.println("ergodicDir...");
                    ergodicDir(file, targetFilePW, API2IndexMap);
                }
                if(file.isFile() && file.getName().endsWith("dot")){
                    System.out.println("processing " + ++count + " data");
                    writePath(file, targetFilePW, API2IndexMap);
                }
            }
        }
        else {
            if(dir.isFile() && dir.getName().endsWith("dot")){
                System.out.println("processing " + ++count + " data");
                writePath(dir, targetFilePW, API2IndexMap);
            }
        }
    }

    private static void writePath(File groumFile, PrintWriter targetFilePW, Map<String, String> API2IndexMap) throws IOException {
        ConstructGroum CG = new ConstructGroum();
        Groum groum = CG.constructGroum(groumFile.getAbsolutePath(), API2IndexMap);
        Map<String, GroumNode> nodeMap = groum.getNodeMap();
        List<String> startList = null;
        for (String id : nodeMap.keySet()) {
            startList = new ArrayList<>();
            startList.add(id);
            List<List<String>> outList = GetPath.getAllPath(groum, startList, 4);
            writeFile(outList, targetFilePW);
        }
    }

    private static String convertListToString(List<String> list) {
        StringBuilder sB = new StringBuilder();
        boolean ifStarted = false;
        for (String item : list) {
            if (ifStarted) {
                sB.append(",");
            }
            sB.append(item);
            if (!ifStarted) ifStarted = true;
        }
        return sB.toString();
    }

    private static void writeFile(List<List<String>> outList, PrintWriter targetFilePW) throws IOException {
        String tempStr = null;
        for (List<String> path : outList) {
            tempStr = convertListToString(path);
            targetFilePW.println(tempStr);
        }
    }
}
