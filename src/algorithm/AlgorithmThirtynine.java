package algorithm;

import java.io.IOException;

// 规则39：判断两个方法是否相互依赖
public class AlgorithmThirtynine {
    public static boolean judgeAssociation(String oldMethodName1,String oldMethodName2) throws IOException {
        return UniversalMethod.judgeAssociationOfMethod(oldMethodName1, oldMethodName2,"oldAssociationRelationship.txt");
    }
}
