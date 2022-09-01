package algorithm;

import java.io.IOException;
import java.util.List;

// 规则31：删除类的依赖是两个相似类依赖交集的真子集
public class AlgorithmThirtyone {
    public static boolean judgeAssociationIsSubset(String name,String newClassName1,String newClassName2) throws IOException {
        List<String> associationWithNewClass = UniversalMethod.getAllAssociationOfClass(newClassName1,"newAssociationRelationship.txt");
        List<String> associationWithNewClass1 = UniversalMethod.getAllAssociationOfClass(newClassName2,"newAssociationRelationship.txt");
        associationWithNewClass.retainAll(associationWithNewClass1);
//        System.out.println("与相似类相关的类有："+associationWithNewClass);

        List<String> associationWithOldClass = UniversalMethod.getAllAssociationOfClass(name,"oldAssociationRelationship.txt");
//        System.out.println("与删除类相关的类有："+associationWithOldClass);

        int count = 0;
        for(String oldClass : associationWithOldClass){
            for(String newClass : associationWithNewClass){
                if(oldClass.equals(newClass)){
                    count++;
                }
            }
        }

//        System.out.println(count == associationWithOldClass.size() && count!=0);
        return (count == associationWithOldClass.size() && count!=0);
    }
}
