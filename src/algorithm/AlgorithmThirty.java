package algorithm;

import java.io.IOException;
import java.util.List;

// 规则30：跟新方法的依赖是旧方法依赖的并集的子集
public class AlgorithmThirty {
    public static boolean judgeAssociation(String oldMethodName1,String oldMethodName2, String newMethodName) throws IOException {
        List<String> associationWithOldMethod = UniversalMethod.getAllAssociationOfMethod(oldMethodName1,"oldAssociationRelationship.txt");
//        System.out.println("与旧类依赖的类有：" + associationWithOldMethod);
        List<String> associationWithOldMethod1 = UniversalMethod.getAllAssociationOfMethod(oldMethodName2,"oldAssociationRelationship.txt");
//        System.out.println("与旧类依赖的类有：" + associationWithOldMethod1);
        associationWithOldMethod.removeAll(associationWithOldMethod1);
        associationWithOldMethod.addAll(associationWithOldMethod1);

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
