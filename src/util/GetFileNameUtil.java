package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GetFileNameUtil {
    // 从项目中提取所有java文件的名字
    public static void getClassNameFromProject(String originPath,String targetPath,String targetFileName) throws IOException {
        File dir = new File(originPath);
        File[] children = dir.listFiles();

        BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath + File.separator + targetFileName));

        for (File child : children) {
            String name = child.getName();
            bw.write(name);
            bw.newLine();
        }

        bw.close();
    }

    public static void getMethodsNameFromProject(String originPath,String targetPath,String targetFileName) throws IOException {
        File dir = new File(originPath);
        File[] children = dir.listFiles();

        BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath + File.separator + targetFileName));

        for (File child : children) {
            String name = child.getName();
            name = name.substring(0,name.indexOf("."));
            name = name.replaceFirst("_",".java:");
            bw.write(name);
            bw.newLine();
        }

        bw.close();
    }
}
