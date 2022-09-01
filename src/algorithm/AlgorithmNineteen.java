package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 规则19：旧类链接需求的并集是新版本中类相似需求的子集
public class AlgorithmNineteen {
    public static boolean judgeRequirementsIsSubset(String name1,String name2,String newClassName) throws IOException {
        // 从旧版本的跟踪矩阵中寻找，与疑似关联类链接的需求（oldRequirement）
        List<String> oldRequirements;
        List<String> result = UniversalMethod.getRequirementUnion(name1,name2);
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
