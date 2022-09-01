package algorithm;

import java.io.IOException;

// 规则37：判断两个方法是否相互依赖
public class AlgorithmThirtyseven {
    public static boolean judgeAssociation(String newMethodName1,String newMethodName2) throws IOException {
        return UniversalMethod.judgeAssociationOfMethod(newMethodName1, newMethodName2,"newAssociationRelationship.txt");
    }
}
