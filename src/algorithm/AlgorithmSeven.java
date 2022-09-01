package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;

import java.util.List;

// 规则7：新添加的类分别和两个旧类同时相似（在规则6上进行处理，应该有大于等于两个的相似结果）
public class AlgorithmSeven {
    public static List<similarObject> judgeSimilarity(String name){
        String irModelName = "ir.VSM";
        TextDataset textDataset = new TextDataset(name, DataPath.getNewClassPreProcessedPath(), DataPath.getOldClassPreProcessedPath());

//        List<similarObject> similarObjects = IR.culSimilar(textDataset, irModelName);
//        for (similarObject object : similarObjects) {
//            System.out.println("新添加类与某旧类相似 -> class:" + object.getOrigin() + "  oldClass:" + object.getTarget() + "  source:" + object.getSimilar());
//        }
//
//        return similarObjects;
        return IR.culSimilar(textDataset, irModelName);
    }
}
