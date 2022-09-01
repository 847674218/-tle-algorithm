package util;

import java.io.*;

// 拆分需求文档
// 注意：文档格式应该是每个需求占一行的txt文件，且这样的文件应该只有一个。
public class RequirementsDivisionUtil {
    public static void requirementsDivision(String sourcePath,String targetPath) throws IOException {
        File dir = new File(sourcePath);
        if(dir.isDirectory()) {
            File[] children = dir.listFiles();
            for(File child:children) {
                requirementsDivision(child.getAbsolutePath(),targetPath);
            }
        }
        else {
            writeFile(dir,targetPath);
        }
    }

    // 复制文件
    private static void writeFile(File javaFile, String targetPath) throws IOException {
        int number = 1;
        BufferedReader br = new BufferedReader(new FileReader(javaFile));

        String line;
        while((line=br.readLine())!=null) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath+File.separator+"F"+number+".txt"));
            bw.write(line);
            number++;
            bw.close();
        }
        br.close();
    }
}
