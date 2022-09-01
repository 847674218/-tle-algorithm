package algorithm;

import java.io.*;

// 规则48：新添加的类继承关联类
public class AlgorithmFortyeight {
    public static boolean isExtendSubClass(String name,String oldClassName) throws IOException {
        return UniversalMethod.isExtend(name,oldClassName);
    }
}
