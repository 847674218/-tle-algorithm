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

// 规则12：找到可能的目标方法（可能有多个）
public class AlgorithmTwelve {
    public static List<String> getTargetMethod(String className, String oldClassName) throws IOException {
        List<String> targetMethod = new ArrayList<>();

        List<String> allOldMethods = UniversalMethod.getAllMethods(oldClassName,"oldMethodName.txt"); // 仅方法名
        for(String oldMethod : allOldMethods){
            boolean isTargetMethod = false;
            Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
            String key = oldClassName + ":" + oldMethod;
            if(traceMatrix.containsKey(key)){
                List<String> oldRequirements = traceMatrix.get(key);
                for(String oldRequirement : oldRequirements){
                    String irModelName = "ir.VSM";
                    TextDataset textDataset = new TextDataset(className, DataPath.getNewClassPreProcessedPath(), oldRequirement ,DataPath.getNewRequirementsPreProcessedPath());
                    List<similarObject> result = IR.culSimilar(textDataset,irModelName);
                    if(!result.isEmpty()){
                        isTargetMethod = true;
                    }
                }
            }

            if(isTargetMethod){
                targetMethod.add(oldMethod);
            }
        }
        return targetMethod;
    }
}
