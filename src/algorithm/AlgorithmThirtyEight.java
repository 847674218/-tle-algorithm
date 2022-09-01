package algorithm;

import java.io.IOException;

// 规则38：旧类相互依赖
public class AlgorithmThirtyEight {
    public static boolean judgeAssociation(String name1,String name2) throws IOException {
        return UniversalMethod.judgeAssociationOfClass(name1,name2,"oldAssociationRelationship.txt");
    }
}
