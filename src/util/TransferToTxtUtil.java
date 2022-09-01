package util;

import java.io.*;

// 把java文件转化成txt文件
public class TransferToTxtUtil {
    public static void transferTXT(String origin,String target) throws IOException {
        File dir = new File(origin);
        File[] children = dir.listFiles();
        for(File child:children) {
            if(child.getName().endsWith(".java")||child.getName().endsWith(".jsp")) {
                copy(child,target);
            }
        }
    }

    private static void copy(File child, String target) throws IOException {
        String name = child.getName();
        if(name.endsWith(".jsp")) {
            name = name.substring(0, name.lastIndexOf("."))+"_jsp.txt";
        }
        else {
            name = name.substring(0, name.lastIndexOf("."))+".txt";
        }

        String targetFileName = target + File.separator + name;
        CopyFileUtil.copyFile(child,targetFileName);
    }
}
