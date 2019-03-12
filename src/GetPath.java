import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class GetPath {

    private static int threshold;
    private static String curNode;
    private static GroumNode groumNode;
    private static Groum targetGraph;
    private static List<List<String>> forPathList;
    private static List<List<String>> backPathList;
    private static List<List<String>> fuzzyPathList;
    private static List<List<String>> forAndbackPathList;
    private static List<List<String>> pathResult;


    private static void getForPath(List<String> successors) {
        if (successors.size() + 1 > threshold)
            return;
        curNode = successors.get(successors.size() - 1);
        groumNode = targetGraph.getNodeMap().get(curNode);
        for (GroumNode predecessor : groumNode.getParents()) {
            List<String> successorsFor = new ArrayList<>();
            Collections.addAll(successorsFor, new String[successors.size()]);
            Collections.copy(successorsFor, successors);
            successorsFor.add(predecessor.getId());
            List<String> successorsCopy = new ArrayList<>();
            Collections.addAll(successorsCopy, new String[successorsFor.size()]);
            Collections.copy(successorsCopy, successorsFor);
            Collections.reverse(successorsFor);
            forPathList.add(successorsFor);
            getForPath(successorsCopy);
        }
    }
    private static void getBackPath(List<String> predecessors) {
        if (predecessors.size() + 1 > threshold)
            return;
        curNode = predecessors.get(predecessors.size() - 1);
        groumNode = targetGraph.getNodeMap().get(curNode);
        for (GroumNode successor : groumNode.getChildren()) {
            List<String> predecessorsFor = new ArrayList<>();
            Collections.addAll(predecessorsFor, new String[predecessors.size()]);
            Collections.copy(predecessorsFor, predecessors);
            predecessorsFor.add(successor.getId());
            backPathList.add(predecessorsFor);
            getBackPath(predecessorsFor);
        }
    }

    /**
     * 产生指定范围的左闭右开区间List
     * @param starter 起始
     * @param ender   终止
     * @return        区间List
     */
    private static List<Integer> getRangeNums(int starter, int ender) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = starter; i < ender; ++i) {
            result.add(i);
        }
        return result;
    }

    /**
     * 构建一条fuzzy path
     * @param target 需要修改的path
     * @param start fuzzy的起始index
     * @param end   fuzzy的终止index + 1
     */
    private static void makeOneFuzzyPath(List<String> target, int start, int end) {
        for (int i = start; i < end; ++i) {
            target.set(i, "*");
        }
    }

    private static void getFuzzyPath(){
        forAndbackPathList = new ArrayList<List<String>>();
        forAndbackPathList.addAll(forPathList);
        forAndbackPathList.addAll(backPathList);
        for (List<String> path : forAndbackPathList) {
            for (int starLen : getRangeNums(1, path.size() - 1)) {
                List<String> pathCopyFor = new ArrayList<>();
                Collections.addAll(pathCopyFor, new String[path.size()]);
                Collections.copy(pathCopyFor, path);
                makeOneFuzzyPath(pathCopyFor, 1, starLen + 1);
                fuzzyPathList.add(pathCopyFor);
                List<String> pathCopyBack = new ArrayList<>();
                Collections.addAll(pathCopyBack, new String[path.size()]);
                Collections.copy(pathCopyBack, path);
                makeOneFuzzyPath(pathCopyBack, path.size() - 1 - starLen, path.size() - 1);
                fuzzyPathList.add(pathCopyBack);
            }
        }
    }

    /**
     * List 去重
     * @param target
     */
    private static void removeDuplicate(List<List<String>> target) {
        LinkedHashSet<List<String>> set = new LinkedHashSet<List<String>>(target.size());
        set.addAll(target);
        target.clear();
        target.addAll(set);
    }
    public static void convert(List<List<String>> pathResult, List<List<String>> source) {
        Map<String, GroumNode> nodeMap = targetGraph.getNodeMap();
        for (List<String> sourcePath : source) {
            List<String> apiPath = new ArrayList<String>();
            for (String id : sourcePath) {
                if (id.equals("*")) {
                    apiPath.add("*");
                }
                else {
                    apiPath.add(nodeMap.get(id).getApi());
                }
            }
            pathResult.add(apiPath);
        }
    }
    public static List<List<String>> getAllPath(Groum groum, List<String> startList, int d) {
        threshold = d;
        targetGraph = groum;
        // get forward paths
        forPathList = new ArrayList<List<String>>();
        getForPath(startList);
        // get backward paths
        backPathList = new ArrayList<List<String>>();
        getBackPath(startList);
        // get fuzzy paths
        fuzzyPathList = new ArrayList<List<String>>();
        getFuzzyPath();
        removeDuplicate(fuzzyPathList);
        // 构建要返回的path list
        pathResult = new ArrayList<List<String>>();
        convert(pathResult, forPathList);
        convert(pathResult, backPathList);
        convert(pathResult, fuzzyPathList);
        return pathResult;
    }
}
