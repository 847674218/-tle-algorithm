package algorithm;

import document.TextDataset;
import object.similarObject;
import preprocess.DataPath;
import ir.IR;

import java.util.ArrayList;
import java.util.List;

// 规则14：至少存在一个新版本的需求与新类相似与旧类不相似
public class AlgorithmFourteen {
    public static boolean judgeSimilarity(String name,String oldClassName) {
        String irModelName = "ir.VSM";

        // 找出与新版本的类相似的需求
        List<String> similarityRequirement = new ArrayList<String>(UniversalMethod.getSimilarNewRequirementOfNewClass(name));

        // 判断与新版本的类相似的需求是否与旧版本的类相似
        List<String> oldRequirement = new ArrayList<String>();
        TextDataset textDataset = new TextDataset(oldClassName, DataPath.getNewClassPreProcessedPath(), DataPath.getNewRequirementsPreProcessedPath());
        List<similarObject> result = IR.culSimilar(textDataset,irModelName);
        for (similarObject object : result) {
            oldRequirement.add(object.getTarget());
        }

        if(similarityRequirement.isEmpty()){
            return false;
        }else if(oldRequirement.isEmpty()){
            return true;
        }else{
            return !similarityRequirement.retainAll(oldRequirement);
        }
    }
}
