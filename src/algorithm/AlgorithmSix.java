package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;

import java.util.List;

// 规则6：新添加的类和旧版本中的至少一个非同名类相似
public class AlgorithmSix {
    public static List<similarObject> judgeSimilarity(String name){
        String irModelName = "ir.VSM";
        TextDataset textDataset = new TextDataset(name, DataPath.getNewClassPreProcessedPath(), DataPath.getOldClassPreProcessedPath());

//        List<similarObject> similarObjects = IR.culSimilar(textDataset, irModelName);
//        for (similarObject object : similarObjects) {
//            System.out.println("新添加类与某旧类相似 -> class:" + object.getOrigin() + "  oldClass:" + object.getTarget() + "  source:" + object.getSimilar());
//        }

//        return similarObjects;
        return IR.culSimilar(textDataset, irModelName);
    }
}