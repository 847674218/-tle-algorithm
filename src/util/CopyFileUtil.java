package util;

import java.io.*;

public class CopyFileUtil {
    public static void copyFile(File source,String target) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(source));
        BufferedWriter bw = new BufferedWriter(new FileWriter(target));

        String line;
        while((line=br.readLine())!=null) {
            bw.write(line);
            bw.newLine();
        }
        br.close();
        bw.close();
    }
}
