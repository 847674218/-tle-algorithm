package algorithm;

import java.io.IOException;
import java.util.List;

// 规则23：新添加类中方法是旧类方法的并集的子集
public class AlgorithmTwentythree {
    public static boolean judgeMethodsIsSubset(String name,String oldClassName1,String oldClassName2) throws IOException {
        // 求并集
        List<String> oldMethods = UniversalMethod.getAllMethods(oldClassName1,"oldMethodName.txt");
        List<String> oldMethods1 = UniversalMethod.getAllMethods(oldClassName2,"oldMethodName.txt");
        oldMethods.removeAll(oldMethods1);
        oldMethods.addAll(oldMethods1);
//        System.out.println("旧类中的方法有："+oldMethods);

        List<String> newMethods = UniversalMethod.getAllMethods(name,"newMethodName.txt");
//        System.out.println("新类中的方法有："+newMethods);

        int count = 0;
        for(String newMethod : newMethods){
            for(String oldMethod : oldMethods){
                if(newMethod.equals(oldMethod)){
                    count++;
                }
            }
        }

//        System.out.println(count == oldMethods.size() && count!=0);
        return (count == oldMethods.size() && count!=0);
    }
}
