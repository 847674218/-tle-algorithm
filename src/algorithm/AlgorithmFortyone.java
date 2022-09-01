package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;

import java.util.List;

// 规则41：新版本中不存在与删除类相似的类
public class AlgorithmFortyone {
    public static List<similarObject> judgeSimilarity(String name){
        String irModelName = "ir.VSM";
        TextDataset textDataset = new TextDataset(name, DataPath.getOldClassPreProcessedPath(), DataPath.getNewClassPreProcessedPath());

//        List<similarObject> similarObjects = IR.culSimilar(textDataset, irModelName);
//        for (similarObject object : similarObjects) {
//            System.out.println(object.getOrigin()+" :"+object.getTarget()+":"+object.getSimilar());
//        }
//
//        return similarObjects;

        return IR.culSimilar(textDataset,irModelName);
    }
}
