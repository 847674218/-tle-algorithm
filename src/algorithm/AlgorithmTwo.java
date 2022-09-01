package algorithm;

import java.io.IOException;
import java.util.List;

public class AlgorithmTwo {
    public static List<String> getDeletedClass(String newClassPath,String oldClassPath) throws IOException {
        List<String> addedClass = UniversalMethod.getClassSet(newClassPath);
        List<String> deletedClass = UniversalMethod.getClassSet(oldClassPath);

        // 求并集
        addedClass.retainAll(deletedClass);
        // 求旧版本中存在的部分
        deletedClass.removeAll(addedClass);

        return deletedClass;
    }
}
