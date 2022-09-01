package algorithm;

import java.io.IOException;
import java.util.List;

// 规则29：新添加类中依赖是旧类依赖的并集的子集
public class AlgorithmTwentynine {
    public static boolean judgeAssociationIsSubset(String name,String oldClassName1,String oldClassName2) throws IOException {
        // 求并集
        List<String> associationWithOldClass = UniversalMethod.getAllAssociationOfClass(oldClassName1,"oldAssociationRelationship.txt");
        List<String> associationWithOldClass1 = UniversalMethod.getAllAssociationOfClass(oldClassName2,"oldAssociationRelationship.txt");
        associationWithOldClass.removeAll(associationWithOldClass1);
        associationWithOldClass.addAll(associationWithOldClass1);
//        System.out.println("与旧类相关的类有："+associationWithOldClass);

        List<String> associationWithNewClass = UniversalMethod.getAllAssociationOfClass(name,"newAssociationRelationship.txt");
//        System.out.println("与新类相关的类有："+associationWithNewClass);

        int count = 0;
        for(String newClass : associationWithNewClass){
            for(String oldClass : associationWithOldClass){
                if(newClass.equals(oldClass)){
                    count++;
                }
            }
        }

//        System.out.println(count == associationWithNewClass.size() && count!=0);
        return (count == associationWithOldClass.size() && count!=0);
    }
}
