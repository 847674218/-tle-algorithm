package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 规则32：跟删除方法依赖的其他方法是新版本目标类中方法的依赖方法的交集的子集
public class AlgorithmThirtytwo {
    public static List<String> getTargetMethod(String name, String newClassName1, String newClassName2) throws IOException {
        List<String> target = new ArrayList<>();

        List<String> associationWithOldMethod = UniversalMethod.getAllAssociationOfMethod(name,"oldAssociationRelationship.txt");

        List<String> newMethods1 = UniversalMethod.getAllMethods(newClassName1,"newMethodName.txt");
        List<String> newMethods2 = UniversalMethod.getAllMethods(newClassName2,"newMethodName.txt");

        for(String newMethod1 : newMethods1){
            for(String newMethod2 :newMethods2){
                List<String> associationWithNewMethod1 = UniversalMethod.getAllAssociationOfMethod(newClassName1+":"+newMethod1,"oldAssociationRelationship.txt");
                List<String> associationWithNewMethod2 = UniversalMethod.getAllAssociationOfMethod(newClassName2+":"+newMethod2,"oldAssociationRelationship.txt");
                associationWithNewMethod1.retainAll(associationWithNewMethod2);

                int count = 0;
                for(String oldMethod : associationWithOldMethod){
                    for(String newMethod : associationWithNewMethod1){
                        if(oldMethod.equals(newMethod)){
                            count++;
                        }
                    }
                }
                if(count == associationWithOldMethod.size() && count != 0){
                    target.add(newClassName1+":"+newMethod1);
                    target.add(newClassName2+":"+newMethod2);
                }
            }
        }
        return target;
    }
}
