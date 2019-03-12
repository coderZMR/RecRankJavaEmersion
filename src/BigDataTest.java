import java.io.*;

public class BigDataTest {
    public static void main(String[] args) throws IOException {
        File sourceFile = new File("train.dat");
        FileWriter fileWriter = new FileWriter(sourceFile, true);
        PrintWriter pw = new PrintWriter(fileWriter);
        for (int i = 2; i <= 7000000; ++i) {
            if (i == 2) pw.print("\n");
            pw.println("3 qid:" + i + " 1:1 2:1 3:0 4:0.2 5:0");
            for (int j = 1; j <= 9; ++j) {
                pw.println("1 qid:" + i + " 1:2 2:3 3:1 4:0.1 5:1");
            }
        }
        pw.flush();
        fileWriter.flush();
        pw.close();
        fileWriter.close();
    }
}
