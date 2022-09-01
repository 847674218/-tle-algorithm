package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 规则42：新版本中没有类和删除类的关联需求相似
public class AlgorithmFortytwo {
    public static boolean isRequirementExist(String name) throws IOException {
        // 从旧版本的跟踪矩阵中寻找与删除类链接的需求
        Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
        List<String> oldRequirement;
        if(!traceMatrix.containsKey(name)){
            return false;
        }else{
            oldRequirement = new ArrayList<String>(traceMatrix.get(name));
        }
//        System.out.println("与删除类链接的需求："+oldRequirement);

        List<String> similarClass = new ArrayList<String>();

        for(String requirement : oldRequirement){
            String irModelName = "ir.VSM";
            TextDataset textDataset = new TextDataset(requirement, DataPath.getOldRequirementsPreProcessedPath(), DataPath.getNewClassPreProcessedPath());
            List<similarObject> result = IR.culSimilar(textDataset,irModelName);
            for (similarObject object : result) {
                if(!object.getTarget().equals(name.substring(0,name.indexOf(".")))){
                    similarClass.add(object.getTarget());
                }
            }
        }

        return similarClass.isEmpty();
    }
}
