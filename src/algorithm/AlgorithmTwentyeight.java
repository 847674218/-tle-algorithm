package algorithm;

import java.io.IOException;
import java.util.List;

// 规则28：跟目标方法依赖的其他方法是新添加方法依赖的其他方法的子集
public class AlgorithmTwentyeight {
    public static boolean judgeAssociation(String oldMethodName,String newMethodName) throws IOException {
        List<String> associationWithOldMethod = UniversalMethod.getAllAssociationOfMethod(oldMethodName,"oldAssociationRelationship.txt");
//        System.out.println("与旧类依赖的类有：" + associationWithOldMethod);

        List<String> associationWithNewMethod = UniversalMethod.getAllAssociationOfMethod(newMethodName,"newAssociationRelationship.txt");
//        System.out.println("与新类依赖的类有：" + associationWithNewMethod);

        int count = 0;
        for(String newMethod : associationWithNewMethod){
            for(String oldMethod : associationWithOldMethod){
                if(newMethod.equals(oldMethod)){
                    count++;
                }
            }
        }

//        System.out.println(count == associationWithNewMethod.size() && count!=0);
        return (count == associationWithNewMethod.size() && count!=0);
    }
}
