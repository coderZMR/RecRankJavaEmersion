import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WritePathResult {

    public static void ergodicDir(File dir, File targetFile, Map<String, String> API2IndexMap) throws IOException {
        if (targetFile.exists()) {
            targetFile.delete();
        }
        if(dir.isDirectory()){
            for(File file : dir.listFiles()){
                if(file.isDirectory()){
                    ergodicDir(file, targetFile, API2IndexMap);
                }
                if(file.isFile() && file.getName().endsWith("dot")){
                    writePath(file, targetFile, API2IndexMap);
                }
            }
        }
        else {
            if(dir.isFile() && dir.getName().endsWith("dot")){
                writePath(dir, targetFile, API2IndexMap);
            }
        }
    }

    public static void writePath(File groumFile, File targetFile, Map<String, String> API2IndexMap) throws IOException {
        ConstructGroum CG = new ConstructGroum();
        Groum groum = CG.constructGroum(groumFile.getAbsolutePath(), API2IndexMap);
        Map<String, GroumNode> nodeMap = groum.getNodeMap();
        List<String> startList = null;
        for (String id : nodeMap.keySet()) {
            startList = new ArrayList<>();
            startList.add(id);
            List<List<String>> outList = GetPath.getAllPath(groum, startList, 4);
            writeFile(outList, targetFile);
        }
    }

    public static void writeFile(List<List<String>> outList, File target) throws IOException {
        if (!target.exists()) {
            target.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(target, true);
        PrintWriter pw = new PrintWriter(fileWriter);
        for (List<String> path : outList) {
            pw.println(path.toString());
        }
        pw.flush();
        fileWriter.flush();
        pw.close();
        fileWriter.close();
    }
}
