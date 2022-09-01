package algorithm;

import java.io.IOException;
import java.util.List;

// 规则25：删除类中的方法是两个相似类方法交集的子集
public class AlgorithmTwentyfive {
    public static boolean judgeMethodsIsSubset(String name,String newClassName1,String newClassName2) throws IOException {
        List<String> newMethods = UniversalMethod.getAllMethods(newClassName1,"newMethodName.txt");
        List<String> newMethods1 = UniversalMethod.getAllMethods(newClassName2,"newMethodName.txt");
        newMethods.retainAll(newMethods1);//交集
//        System.out.println("新类中的方法有："+newMethods);

        List<String> oldMethods = UniversalMethod.getAllMethods(name,"oldMethodName.txt");
//        System.out.println("新类中的方法有："+newMethods);

        int count = 0;
        for(String oldMethod : oldMethods){
            for(String newMethod : newMethods){
                if(oldMethod.equals(newMethod)){
                    count++;
                }
            }
        }

//        System.out.println(count == oldMethods.size() && count!=0);
        return (count == oldMethods.size() && count!=0);
    }
}
