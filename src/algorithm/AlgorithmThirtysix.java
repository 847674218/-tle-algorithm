package algorithm;

import java.io.IOException;

// 规则36：两个相似类互相依赖
public class AlgorithmThirtysix {
    public static boolean judgeAssociation(String newClassName1,String newClassName2) throws IOException {
        return UniversalMethod.judgeAssociationOfClass(newClassName1,newClassName2,"newAssociationRelationship.txt");
    }
}
