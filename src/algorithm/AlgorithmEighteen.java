package algorithm;

import util.GetTraceMatrix;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// 规则18：新版本中的类相似的需求的并集是跟删除方法关联的需求的子集
public class AlgorithmEighteen {
    public static boolean judgeRequirementsIsSubset(String name, String newClassName1, String newClassName2) throws IOException {
        // 公式的左半部分
        List<String> newRequirements = UniversalMethod.getSimilarNewRequirementOfNewClass(newClassName1);
        List<String> newRequirements1 = UniversalMethod.getSimilarNewRequirementOfNewClass(newClassName2);
        newRequirements.removeAll(newRequirements1);
        newRequirements.addAll(newRequirements1);

        // 公式的右半部分
        Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
        List<String> oldRequirements;
        if(!traceMatrix.containsKey(name)) {
            return false;
        }else{
            oldRequirements = traceMatrix.get(name);
        }

        int count = 0;
        for(String newRequirement : newRequirements){
            for(String oldRequirement : oldRequirements){
                if(newRequirement.equals(oldRequirement)){
                    count++;
                }
            }
        }
        return (newRequirements.size() == count && count != 0);
    }
}
