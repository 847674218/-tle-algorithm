package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;

import java.util.List;

// 规则8：删除类和新版本中的两个类相似
public class AlgorithmEight {
    public static List<similarObject> judgeSimilarity(String name){
        String irModelName = "ir.VSM";
        TextDataset textDataset = new TextDataset(name, DataPath.getOldClassPreProcessedPath(), DataPath.getNewClassPreProcessedPath());

//        List<similarObject> similarObjects = IR.culSimilar(textDataset, irModelName);
//        for (similarObject object : similarObjects) {
//            System.out.println("删除类与某新类相似 -> class:" + object.getOrigin() + "  oldClass:" + object.getTarget() + "  source:" + object.getSimilar());
//        }
//
//        return similarObjects;
        return IR.culSimilar(textDataset, irModelName);
    }
}
