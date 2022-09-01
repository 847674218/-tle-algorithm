package algorithm;

import java.io.IOException;
import java.util.List;

// 规则24：旧类中方法的并集是新类方法的子集
public class AlgorithmTwentyfour {
    public static boolean judgeMethodsIsSubset(String name1,String name2,String newClassName) throws IOException {
        List<String> oldMethods = UniversalMethod.getAllMethods(name1,"oldMethodName.txt");
        List<String> oldMethods1 = UniversalMethod.getAllMethods(name2,"oldMethodName.txt");
        oldMethods.retainAll(oldMethods1);
        oldMethods.addAll(oldMethods1);

        List<String> newMethods = UniversalMethod.getAllMethods(newClassName,"newMethodName.txt");

        int count = 0;
        for(String oldMethod : oldMethods){
            for(String newMethod : newMethods){
                if(oldMethod.equals(newMethod)){
                    count++;
                }
            }
        }

        return (count == oldMethods.size() && count!=0 && oldMethods.size() < newMethods.size());
    }
}
