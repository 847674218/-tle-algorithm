package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;

import java.util.List;

// 规则10：新版本的需求中至少有一条与新添加的类相似
public class AlgorithmTen {
    public static List<similarObject> judgeSimilarity(String name){
        String irModelName = "ir.VSM";
        TextDataset textDataset = new TextDataset(name,DataPath.getNewClassPreProcessedPath(), DataPath.getNewRequirementsPreProcessedPath());

//        List<similarObject> similarObjects = IR.culSimilar(textDataset, irModelName);
//        for (similarObject object : similarObjects) {
//            System.out.println(object.getOrigin()+" :"+object.getTarget()+":"+object.getSimilar());
//        }
//        return similarObjects;

        return IR.culSimilar(textDataset, irModelName);
    }
}