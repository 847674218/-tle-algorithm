package algorithm;

import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 规则11：与新添加类相似的需求的集合是旧版本中相似类链接的需求集合的真子集
public class AlgorithmEleven {
    public static boolean judgeRequirementsIsSubset(String name,String oldClassName) throws IOException {
        // 从旧版本的跟踪矩阵中寻找，与相似类链接的需求（oldRequirements）
        Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
        List<String> oldRequirements;
        if(!traceMatrix.containsKey(oldClassName)){
            return false;
        }else{
            oldRequirements = new ArrayList<String>(traceMatrix.get(oldClassName));
        }
//        System.out.println("与旧版本中相似类链接的需求有："+oldRequirements);

        // 找出与新添加的类相似的需求（newRequirements）
        List<String> newRequirements = new ArrayList<String>(UniversalMethod.getSimilarNewRequirementOfNewClass(name));
        if(newRequirements.isEmpty()){
            return false;
        }
//        System.out.println("与新添加的类相似的需求有：" + newRequirements);

        // 判断newRequirements是否是oldRequirements的真子集
        int count = 0;
        for (String newRequirement : newRequirements) {
            for (String oldRequirement : oldRequirements) {
                if (newRequirement.equals(oldRequirement)) {
                    count++;
                }
            }
        }

        return (count == newRequirements.size() && newRequirements.size() < oldRequirements.size());
    }
}