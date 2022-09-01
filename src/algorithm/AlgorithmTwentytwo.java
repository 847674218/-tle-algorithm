package algorithm;

import java.io.*;
import java.util.List;

// 规则22：新添加类中的方法是相似类方法的集合的真子集
public class AlgorithmTwentytwo {
    public static boolean judgeMethodsIsSubset(String name,String oldClassName) throws IOException {
        List<String> oldMethods = UniversalMethod.getAllMethods(oldClassName,"oldMethodName.txt");
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

        return (count == newMethods.size() && count!=0 && newMethods.size() < oldMethods.size());
    }
}