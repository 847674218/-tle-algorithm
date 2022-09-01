package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 规则34：两个删除方法依赖方法的交集是疑似目标类中某个方法依赖方法的子集，找到这个方法
public class AlgorithmThirtyfour {
    public static List<String> getMethod(String oldMethod1,String oldMethod2,String newClassName) throws IOException {
        List<String> result = new ArrayList<>();

        List<String> associationWithOldMethod = UniversalMethod.getAllAssociationOfMethod(oldMethod1,"oldAssociationRelationship.txt");
        List<String> associationWithOldMethod1 = UniversalMethod.getAllAssociationOfMethod(oldMethod2,"oldAssociationRelationship.txt");
        associationWithOldMethod.retainAll(associationWithOldMethod1);

        List<String> allMethods = UniversalMethod.getAllMethods(newClassName,"newMethodName.txt");
        for(String method : allMethods){
            String newMethodName = newClassName + ":" + method;
            List<String> associationWithNewMethod = UniversalMethod.getAllAssociationOfMethod(newMethodName,"newAssociationRelationship.txt");

            int count = 0;
            for(String oldMethod : associationWithOldMethod){
                for(String newMethod : associationWithNewMethod){
                    if(oldMethod.equals(newMethod)){
                        count++;
                    }
                }
            }

            if(count == associationWithOldMethod.size() && count != 0){
                result.add(newMethodName);
            }
        }
        return result;
    }
}
