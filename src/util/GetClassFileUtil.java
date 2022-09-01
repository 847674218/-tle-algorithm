package util;

import java.io.*;

// 把项目中的java文件复制到目标路径下
public class GetClassFileUtil {
    // 从项目中提取java文件
    public static void getClassFromProject(String originPath,String targetPath) throws IOException {
        // dir是目标路径下的文件夹或者文件
        File dir = new File(originPath);
        if(dir.isDirectory()) {
            if(!dir.getName().equals("test")) {
                File[] children = dir.listFiles();
                for(File child:children) {
                    getClassFromProject(child.getAbsolutePath(), targetPath);
                }
            }
        }
        else {
            String fileName = dir.getName();
            if(fileName.endsWith(".java") || fileName.endsWith(".jsp")) {
                writeFile(dir,targetPath);
            }
        }
    }

    // 复制文件
    public static void writeFile(File javaFile, String targetPath) throws IOException {
        String name = javaFile.getName();
        String targetFileName = targetPath + File.separator + name;
        CopyFileUtil.copyFile(javaFile,targetFileName);
    }
}
