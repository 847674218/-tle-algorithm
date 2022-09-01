package util;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetSimilarityThreshold {
    public static double getSimilarityThreshold(){
        double similarityThreshold = 0;

        List<Double> tempResult = new ArrayList<>();
        String irModelName = "ir.VSM";

        TextDataset textDataset1 = new TextDataset(DataPath.getNewClassPreProcessedPath(), DataPath.getNewRequirementsPreProcessedPath());
        List<similarObject> result1 = IR.culSimilar(textDataset1, irModelName);
        for(similarObject so : result1){
            tempResult.add(so.getSimilar());
        }

        similarityThreshold = Collections.max(tempResult);
        System.out.println(similarityThreshold);

        TextDataset textDataset2 = new TextDataset(DataPath.getOldClassPreProcessedPath(), DataPath.getOldRequirementsPreProcessedPath());
        List<similarObject> result2 = IR.culSimilar(textDataset2, irModelName);
        for(similarObject so : result2){
            tempResult.add(so.getSimilar());
        }

        similarityThreshold = Collections.max(tempResult);
        System.out.println(similarityThreshold);

        TextDataset textDataset3 = new TextDataset(DataPath.getOldClassPreProcessedPath(), DataPath.getNewClassPreProcessedPath());
        List<similarObject> result3 = IR.culSimilar(textDataset3, irModelName);
        for(similarObject so : result3){
            System.out.println(so.getOrigin() + " " + so.getTarget() + " " + so.getSimilar());
            tempResult.add(so.getSimilar());
        }

        similarityThreshold = Collections.max(tempResult);
        System.out.println(similarityThreshold);

        TextDataset textDataset4 = new TextDataset(DataPath.getOldMethodsPreProcessedPath(), DataPath.getNewMethodsPreProcessedPath());
        List<similarObject> result4 = IR.culSimilar(textDataset4, irModelName);
        for(similarObject so : result4){
            System.out.println(so.getOrigin() + " " + so.getTarget() + " " + so.getSimilar());
            tempResult.add(so.getSimilar());
        }

        similarityThreshold = Collections.max(tempResult);
        System.out.println(similarityThreshold);

        return similarityThreshold;

    }
}