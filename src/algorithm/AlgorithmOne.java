package algorithm;

import java.util.List;

public class AlgorithmOne {
    public static List<String> getAddedClass(String newClassPath,String oldClassPath) {
        List<String> addedClass = UniversalMethod.getClassSet(newClassPath);
        List<String> deletedClass = UniversalMethod.getClassSet(oldClassPath);

        // 求并集
        deletedClass.retainAll(addedClass);
        // 求新版本中存在的部分
        addedClass.removeAll(deletedClass);

        return addedClass;
    }
}
