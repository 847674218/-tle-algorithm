package algorithm;

import java.io.IOException;

// 规则35：新添加的类在新版本中和目标类的同名类依赖
public class AlgorithmThirtyfive {
    public static boolean judgeAssociation(String name,String oldClassName) throws IOException {
        return UniversalMethod.judgeAssociationOfClass(name,oldClassName,"newAssociationRelationship.txt");
    }
}
