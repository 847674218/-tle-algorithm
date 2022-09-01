package algorithm;

import java.io.IOException;

// 规则49：关联类继承新添加的类
public class AlgorithmFortynine {
    public static boolean isExtendSuperClass(String name,String oldClassName) throws IOException {
        return UniversalMethod.isExtend(oldClassName,name);
    }
}
