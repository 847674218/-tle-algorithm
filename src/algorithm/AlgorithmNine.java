package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;

import java.util.ArrayList;
import java.util.List;

// 规则9：两个删除类跟新版本中的某个类同时相似
public class AlgorithmNine {
    public static List<String> judgeSimilar(String name1,String name2){
        List<String> result1 = new ArrayList<String>(isSimilar(name1));
        List<String> result2 = new ArrayList<String>(isSimilar(name2));
        result1.retainAll(result2);

        return result1;
    }

    public static List<String> isSimilar(String name){
        String irModelName = "ir.VSM";
        List<String> result = new ArrayList<String>();

        TextDataset textDataset = new TextDataset(name, DataPath.getOldClassPreProcessedPath(), DataPath.getNewClassPreProcessedPath());
        List<similarObject> similarObjects = IR.culSimilar(textDataset, irModelName);
        for (similarObject object : similarObjects) {
            result.add(object.getTarget()+".java");
        }

        return result;
    }
}
