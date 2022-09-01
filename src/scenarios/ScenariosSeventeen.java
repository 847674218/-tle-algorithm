package scenarios;

import algorithm.AlgorithmForty;
import algorithm.AlgorithmTen;
import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ScenariosSeventeen {
    public static double judgeScenariosSeventeen (String name) {
        double algorithmNum = 2;
        double score = 0;

        String className = name.substring(0,name.indexOf(":"));

        // 规则10：新版本的需求中至少有一条与新添加方法的所在类相似
        List<similarObject> resultOfTen = AlgorithmTen.judgeSimilarity(className);
        if(!resultOfTen.isEmpty()){
            score++;
        }

        // 规则40：新添加的类和旧版本中的其他类都不相似
        List<similarObject> resultOfForty = AlgorithmForty.judgeSimilarity(className);
        if(resultOfForty.isEmpty()){
            score++;
        }else if(resultOfForty.size() == 1){
            String testClassName = resultOfForty.get(0).getTarget();
            if(className.substring(0,className.indexOf(".")).equals(testClassName)){
                score++;
            }
        }

        System.out.println("场景十七的分数是：" + score/algorithmNum);
        return score/algorithmNum;
    }

    public static void evolveOfScenariosSeventeen (String name) throws IOException {
        Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
        if(traceMatrix.containsKey(name)){
            List<String> requirements = traceMatrix.get(name);
            for(String requirement : requirements ){
                String irModelName = "ir.VSM";
                TextDataset textDataset = new TextDataset(name, DataPath.getNewMethodsPreProcessedPath(),requirement, DataPath.getOldRequirementsPreProcessedPath());
                List<similarObject> result = IR.culSimilar(textDataset, irModelName);
                if(!result.isEmpty()){
                    System.out.println("符合场景十七 ---> 添加跟踪链接：" + requirement + " " + name);
                }
            }
        }else{
            System.out.println("符合场景十七 ---> 没有目标链接。");
        }
    }
}
