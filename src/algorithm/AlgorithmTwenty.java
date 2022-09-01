package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 规则20：删除方法链接的需求的并集是跟疑似目标新类相似的需求集合的子集
public class AlgorithmTwenty {
    public static boolean judgeRequirementsIsSubset(String oldMethod1,String oldMethod2,String newClassName) throws IOException {
        // 从旧版本的跟踪矩阵中寻找，与删除类链接的需求（oldRequirement）
        List<String> oldRequirements;
        List<String> result = UniversalMethod.getRequirementUnion(oldMethod1,oldMethod2);
        if(result.get(0).equals("noResult")){
            return false;
        }else{
            oldRequirements = result;
        }

        List<String> newRequirements = new ArrayList<String>(UniversalMethod.getSimilarNewRequirementOfNewClass(newClassName));

        int count = 0;
        for (String oldRequirement : oldRequirements) {
            for (String newRequirement : newRequirements) {
                if (oldRequirement.equals(newRequirement)) {
                    count++;
                }
            }
        }

//        System.out.println(count == oldRequirement1.size()) && (count!=0);
        return (count == oldRequirements.size()) && (count!=0);
    }
}
