package algorithm;

import java.io.IOException;
import java.util.List;

public class AlgorithmFour {
    public static List<String> getDeletedMethod() throws IOException {
        List<String> addedMethod = UniversalMethod.getMethodSet("newMethodName.txt");
        List<String> deletedMethod = UniversalMethod.getMethodSet("oldMethodName.txt");

        // 求并集
        addedMethod.retainAll(deletedMethod);
        // 求旧版本中存在的部分
        deletedMethod.removeAll(addedMethod);

        return deletedMethod;
    }
}
