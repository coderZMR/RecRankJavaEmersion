import java.util.ArrayList;
import java.util.List;

public class FuncTest {
    private static List<Integer> getRangeNums(int starter, int ender) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = starter; i < ender; ++i) {
            result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> rangeNums = getRangeNums(1, 3);
        for (int i : rangeNums) {
            System.out.println(i);
        }
    }
}
