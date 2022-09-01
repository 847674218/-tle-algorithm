package algorithm;

import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 规则17：新版本中与相似类相似的需求的交集是删除类关联需求的集合的子集
public class AlgorithmSeventeen {
    public static boolean judgeRequirementsIsSubset(String name,String newClassName1,String newClassName2) throws IOException {
        // 从旧版本的跟踪矩阵中寻找，与删除类链接的需求
        Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
        List<String> oldRequirements;
        if(!traceMatrix.containsKey(name)){
            return false;
        }else{
            oldRequirements = new ArrayList<String>(traceMatrix.get(name));
        }

        List<String> newRequirements = new ArrayList<String>(UniversalMethod.getSimilarNewRequirementOfNewClass(newClassName1));
        List<String> newRequirement2 = new ArrayList<String>(UniversalMethod.getSimilarNewRequirementOfNewClass(newClassName2));
        newRequirements.retainAll(newRequirement2);
//        System.out.println("交集为：" + newRequirements);

        // 判断newRequirements是否是oldRequirements的子集
        int count = 0;
        for (String newRequirement : newRequirements) {
            for (String oldRequirement : oldRequirements) {
                if (oldRequirement.equals(newRequirement)) {
                    count++;
                }
            }
        }

//        System.out.println((count == newRequirements.size()) && (count!=0));
        return (count == newRequirements.size()) && (count!=0);
    }
}
