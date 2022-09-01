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

// 规则16：找到可能的目标方法（全部满足）
public class AlgorithmSixteen {
    public static List<String> getTargetMethod(String className, String oldClassName1, String oldClassName2) throws IOException {
        List<String> targetMethod = new ArrayList<>();

        List<String> allOldMethods1 = UniversalMethod.getAllMethods(oldClassName1,"oldMethodName.txt"); // 仅方法名
        List<String> allOldMethods2 = UniversalMethod.getAllMethods(oldClassName2,"oldMethodName.txt"); // 仅方法名
        for(String oldMethod1 : allOldMethods1){
            for(String oldMethod2 : allOldMethods2){
                Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
                String key1 = oldClassName1 + ":" + oldMethod1;
                String key2 = oldClassName2 + ":" + oldMethod2;
                // 公式的右半部分
                List<String> oldRequirements = new ArrayList<String>();
                if(traceMatrix.containsKey(key1) && traceMatrix.containsKey(key2)){
                    oldRequirements = traceMatrix.get(key1);
                    List<String> oldRequirements1 = traceMatrix.get(key2);
                    oldRequirements.removeAll(oldRequirements1);
                    oldRequirements.addAll(oldRequirements1);
                }else if(!traceMatrix.containsKey(key1) && traceMatrix.containsKey(key2)){
                    oldRequirements = traceMatrix.get(key2);
                }else if(traceMatrix.containsKey(key1) && !traceMatrix.containsKey(key2)){
                    oldRequirements = traceMatrix.get(key1);
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
                    targetMethod.add(oldClassName1 + ":" + oldMethod1);
                    targetMethod.add(oldClassName2 + ":" + oldMethod2);
                }
            }
        }

        return targetMethod;
    }
}
