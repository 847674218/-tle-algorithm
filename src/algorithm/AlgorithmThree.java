package algorithm;

import java.io.IOException;
import java.util.List;

public class AlgorithmThree {
    public static List<String> getAddedMethod() throws IOException {
        List<String> addedMethod = UniversalMethod.getMethodSet("newMethodName.txt");
        List<String> deletedMethod = UniversalMethod.getMethodSet("oldMethodName.txt");

        // 求并集
        deletedMethod.retainAll(addedMethod);
        // 求新版本中存在的部分
        addedMethod.removeAll(deletedMethod);

        return addedMethod;
    }
}
