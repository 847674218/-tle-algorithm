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

// 规则13：找到可能的目标方法（可能有多个）
public class AlgorithmThirteen {
    public static List<String> getTargetMethod(String className, String oldClassName) throws IOException {
        List<String> targetMethod = new ArrayList<>();

        List<String> allOldMethods = UniversalMethod.getAllMethods(oldClassName,"oldMethodName.txt"); // 仅方法名
        for(String oldMethod : allOldMethods){
            // 公式的右半部分
            Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
            String key = oldClassName + ":" + oldMethod;
            List<String> oldRequirements = new ArrayList<String>();
            if(traceMatrix.containsKey(key)) {
                oldRequirements = traceMatrix.get(key);
            }

            // 公式的左半部分
            List<String> newRequirements = new ArrayList<String>();
            String irModelName = "ir.VSM";
            TextDataset textDataset = new TextDataset(className, DataPath.getNewClassPreProcessedPath() ,DataPath.getNewRequirementsPreProcessedPath());
            List<similarObject> result = IR.culSimilar(textDataset,irModelName);
            for (similarObject object : result) {
                newRequirements.add(object.getTarget());
            }

            int count = 0;
            for(String newRequirement : newRequirements){
                for(String oldRequirement : oldRequirements){
                    if(newRequirement.equals(oldRequirement)){
                        count++;
                    }
                }
            }
            if(newRequirements.size() == count && count != 0){
                targetMethod.add(key);
            }
        }
        return targetMethod;
    }
}
