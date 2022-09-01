package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 规则15：与新添加类相似的需求集合是旧类链接的需求的并集的子集
public class AlgorithmFifteen {
    public static boolean judgeRequirementsIsSubset(String name,String oldClassName1,String oldClassName2) throws IOException {
        // 从旧版本的跟踪矩阵中寻找，与疑似关联类链接的需求（oldRequirement）
        List<String> oldRequirements;
        List<String> result = UniversalMethod.getRequirementUnion(oldClassName1,oldClassName2);
        if(result.get(0).equals("noResult")){
            return false;
        }else{
            oldRequirements = result;
        }
//         System.out.println("与相似类链接的需求有：" + oldRequirements);

        // 找出与新添加的类相似的需求（newRequirement）
        List<String> newRequirements = new ArrayList<String>(UniversalMethod.getSimilarNewRequirementOfNewClass(name));
        if(newRequirements.size() == 0){
            return false;
        }
//        System.out.println("与新添加的类相似的需求有：" + newRequirements);

        // 判断newRequirements是否是oldRequirements的子集
        int count = 0;
        for (String newRequirement : newRequirements) {
            for (String oldRequirement : oldRequirements) {
                if (newRequirement.equals(oldRequirement)) {
                    count++;
                }
            }
        }

//        System.out.println((count == oldRequirements.size()) && (count!=0));
        return (count == oldRequirements.size()) && (count!=0);
    }
}
