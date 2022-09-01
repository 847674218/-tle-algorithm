package scenarios;

import algorithm.AlgorithmFortyone;
import algorithm.AlgorithmFortytwo;
import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ScenariosEighteen {
    public static double judgeScenariosEighteen (String name) throws IOException {
        double algorithmNum = 2;
        double score = 0;

        String className = name.substring(0,name.indexOf(":"));

        // 规则41：新版本中不存在与删除类相似的类
        List<similarObject> resultOfFortyone = AlgorithmFortyone.judgeSimilarity(className);
        if(resultOfFortyone.isEmpty()){
            score++;
        }else if(resultOfFortyone.size() == 1){
            String testClassName = resultOfFortyone.get(0).getTarget();
            if(className.substring(0,className.indexOf(".")).equals(testClassName)){
                score++;
            }
        }

        // 规则42：新版本中没有类和删除类的关联需求相似
        if(AlgorithmFortytwo.isRequirementExist(className)){
            score++;
        }

        System.out.println("场景十八的分数是：" + score/algorithmNum);
        return score/algorithmNum;
    }

    public static void evolveOfScenariosEighteen (String name) throws IOException {
        Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
        if(traceMatrix.containsKey(name)){
            List<String> requirements = traceMatrix.get(name);
            for(String requirement : requirements ){
                String irModelName = "ir.VSM";
                TextDataset textDataset = new TextDataset(name, DataPath.getNewMethodsPreProcessedPath(),requirement, DataPath.getOldRequirementsPreProcessedPath());
                List<similarObject> result = IR.culSimilar(textDataset, irModelName);
                if(result.isEmpty()){
                    System.out.println("符合场景十八 ---> 删除跟踪链接：" + requirement + " " + name);
                }
            }
        }else{
            System.out.println("符合场景十八 ---> 没有目标链接。");
        }
    }
}
