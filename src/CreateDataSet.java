import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输入：hole由正确答案填充后的groum，以及top10结果集合，且正确答案为结果集合中的第一个元素
 */
public class CreateDataSet {

    static HashMap<String, Integer> pathCounter;
    static Map<String, String> API2Index;

    /**
     * 读取api2index的Map
     * @param file
     * @throws IOException
     */
    private static void getPathCounter(File file) throws IOException {
        pathCounter = new HashMap<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String row;
        String path; Integer num;
        while ((row = bufferedReader.readLine()) != null) {
            path = row.split(" ")[0];
            num = new Integer(row.split(" ")[1]);
            pathCounter.put(path, num);
        }
        bufferedReader.close();
        fileReader.close();
    }

    /**
     * 创建适用于SVM_Rank的数据集
     * 对单个Groum进行处理
     * 在做top-10结果替换的时候，做path数目的查询
     * @param groum     hole由正确答案填充后的groum
     * @param results   top10结果集合, results第一个为正确答案
     */
    public static void create(Groum groum, List<String> results, int qId, File file, PrintWriter pW, Map<String, String> A2I) throws IOException {
        getPathCounter(file);
        API2Index = A2I;
        Map<String, GroumNode> nodeMap = groum.getNodeMap();
        GroumNode groumNode = null;
        List<String> startList = null;
        String rows = null;
        for (String id : nodeMap.keySet()) {
            groumNode = nodeMap.get(id);
            if (groumNode.getOriginalApi().equals(results.get(0))) {
                startList = new ArrayList<>();
                startList.add(id);
                List<List<String>> outList = GetPath.getAllPath(groum, startList, 4);   // 此处所得到的path表示是使用Map中的API的index构成
                rows = createRows(outList, results, groumNode.getApi(), qId);
                writeFile(pW, rows);
            }
        }
    }

    /**
     * 将String变为List，并得到填充hole的Path（aPath）以及除去hole以外的剩余API组成的Path（bPath）
     * @param list
     * @param id
     * @param result
     * @return
     */
    private static List<String> convertListToStringAndGetAPathBPath(List<String> list, String id, String result) {
        List<String> ret = new ArrayList<String>();
        StringBuilder aPath = new StringBuilder();
        StringBuilder bPath = new StringBuilder();
        boolean ifStarted = false;
        boolean ifMeetHole = false;
        for (String item : list) {
            if (item.equals(id)) {
                aPath.append(result);
                aPath.append(",");
            }
            else {
                aPath.append(item);
                bPath.append(item);
                aPath.append(",");
                bPath.append(",");
            }
        }
        if (aPath.charAt(aPath.length() - 1) == ',') aPath.deleteCharAt(aPath.length() - 1);
        if (bPath.charAt(bPath.length() - 1) == ',') bPath.deleteCharAt(bPath.length() - 1);
        ret.add(aPath.toString());
        ret.add(bPath.toString());
        return ret;
    }

    /**
     * 构建形式为"3 qid:1 1:1 2:1 3:0 4:0.2 5:0"每行数据元
     * @param outList 使用hole抽取的Path
     * @param results top10结果集
     * @param id 正确答案在Map中的Id
     * @param qId 数据元中的qid
     */
    private static String createRows(List<List<String>> outList, List<String> results, String id, int qId) {
        StringBuilder rows = new StringBuilder();
        List<String> midRes = null;
        double feature = 0;
        int featureId = 1;
        boolean ifFirst = true;
        String resultId;
        for (String result : results) {
            if (ifFirst) {
                rows.append("2");    // 正确答案的rank值为2
                ifFirst = false;
            }
            else {
                rows.append("1");    // 非正确答案的rank值均为1
            }
            rows.append(" ");rows.append("qid:");rows.append(qId);rows.append(" ");
            featureId = 1;
            boolean ifFirstFeature = true;
            for (List<String> path : outList) {
                resultId = API2Index.get(result);
                midRes = convertListToStringAndGetAPathBPath(path, id, resultId);
                if (midRes.get(1).length() == 1) continue;
                System.out.println(midRes.get(1));
                System.out.println(midRes.get(0));
                feature = (double)pathCounter.getOrDefault(midRes.get(1), 0) / pathCounter.getOrDefault(midRes.get(0), 1); // 此处对a值设定最小为1
                if (ifFirstFeature) ifFirstFeature = false;
                else rows.append(" ");
                rows.append(featureId);rows.append(":");rows.append(feature);
                ++featureId;
            }
            rows.append("\n");
        }
        return rows.toString();
    }

    /**
     * 将一个Groum的数据集写入文件
     * @param pW
     * @param rows
     */
    private static void writeFile(PrintWriter pW, String rows) {
        pW.write(rows);
    }
}
