//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class Test {
//
//    static Map<String, String> API2Index;
//
//    /**
//     * 单个Groum测试
//     */
//    private static void test1() {
//        ConstructGroum CG = new ConstructGroum();
//        Groum groum = CG.constructGroum("C:\\Users\\RuI\\Desktop\\ZMR_RecRankEmersion\\RecRankJavaEmersion\\dotFile\\Demo.resizeArray.dot", API2Index);
//        List<String> startList = new ArrayList<>();
//        startList.add("2");
//        List<List<String>> outList = GetPath.getAllPath(groum, startList, 6);
//        for (List<String> path : outList) {
//            System.out.println(path);
//        }
//    }
//
//
//
//    private static void test2() throws IOException {
//        WritePathResult.ergodicDir(new File("C:\\Users\\RuI\\Desktop\\ZMR_RecRankEmersion\\RecRankJavaEmersion\\dotFile"), new File("C:\\Users\\RuI\\Desktop\\ZMR_RecRankEmersion\\RecRankJavaEmersion\\AllPath.txt"), API2Index);
//    }
//
//    private static void test3() throws IOException {
//        ReadAndCountPathResult.readAndCount(new File("C:\\Users\\RuI\\Desktop\\ZMR_RecRankEmersion\\RecRankJavaEmersion\\AllPath.txt"), new File("C:\\Users\\RuI\\Desktop\\ZMR_RecRankEmersion\\RecRankJavaEmersion\\PathCount.txt"));
//    }
//
//    private static void test4() throws IOException {
//        API2Index = ReadAPI2Index.getAPI2IndexMap(new File("C:\\Users\\RuI\\Desktop\\ZMR_RecRankEmersion\\RecRankJavaEmersion\\APIIndexMap.txt"));
//        for (String keyStr : API2Index.keySet()) {
//            System.out.println(keyStr + API2Index.get(keyStr));
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        test4();    // 获得APIIndexMap
//        test3();
//    }
//}
