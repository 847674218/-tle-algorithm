package algorithm;

import java.io.IOException;

// 规则45：这两个方法在新版本中不存在
public class AlgorithmFortyseven {
    public static boolean isNotExist(String methodName1, String methodName2) throws IOException {
        boolean result1 = AlgorithmFortyfive.isExist(methodName1);
        boolean result2 = AlgorithmFortyfive.isExist(methodName2);

        return !result1 && !result2;
    }
}
