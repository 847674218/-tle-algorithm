package algorithm;

import java.io.*;
import java.util.List;

// 规则26：与新添加类依赖的类是旧版本中相似类的依赖类的子集
public class AlgorithmTwentysix {
    public static boolean judgeAssociationIsSubset(String name,String oldClassName) throws IOException {
        List<String> associationWithOldClass = UniversalMethod.getAllAssociationOfClass(oldClassName,"oldAssociationRelationship.txt");
//        System.out.println("与旧类依赖的类有："+associationWithOldClass);

        List<String> associationWithNewClass = UniversalMethod.getAllAssociationOfClass(name,"newAssociationRelationship.txt");
//        System.out.println("与新类依赖的类有："+associationWithNewClass);

        int count = 0;
        for(String newClass : associationWithNewClass){
            for(String oldClass : associationWithOldClass){
                if(newClass.equals(oldClass)){
                    count++;
                }
            }
        }

//        System.out.println(count == associationWithNewClass.size() && count!=0 && associationWithNewClass.size() < associationWithOldClass.size());
        return (count == associationWithNewClass.size() && count!=0 && associationWithNewClass.size() < associationWithOldClass.size());
    }
}
