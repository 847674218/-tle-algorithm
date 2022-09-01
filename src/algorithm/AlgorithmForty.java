package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;

import java.util.List;

// 规则40：新添加的类和旧版本中的类都不相似
public class AlgorithmForty {
    private static final String irModelName = "ir.VSM";
    public static List<similarObject> judgeSimilarity(String name){
        TextDataset textDataset = new TextDataset(name,DataPath.getNewClassPreProcessedPath(), DataPath.getOldClassPreProcessedPath());

//        List<similarObject> similarObjects = IR.culSimilar(textDataset, irModelName);
//        for (similarObject object : similarObjects) {
//            System.out.println(object.getOrigin()+" :"+object.getTarget()+":"+object.getSimilar());
//        }
//
//        return similarObjects;

        return IR.culSimilar(textDataset,irModelName);
    }
}