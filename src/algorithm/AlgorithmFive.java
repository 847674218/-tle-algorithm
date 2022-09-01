package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmFive {
    public static List<String> getChangeMethod(){
        List<String> changedMethod = new ArrayList<String>();

        String irModelName = "ir.VSM";
        TextDataset textDataset = new TextDataset(DataPath.getNewMethodsPreProcessedPath(), DataPath.getOldMethodsPreProcessedPath());
        List<similarObject> result = IR.culNotSimilar(textDataset,irModelName);
        for (similarObject object : result) {
            if(object.getOrigin().equals(object.getTarget())){
                String[] temp = object.getOrigin().split("_");
                String method = temp[0] + ".java:" + temp[1];
                changedMethod.add(method);
            }
        }

        return changedMethod;
    }
}
