package scenarios;

import algorithm.AlgorithmFortyone;
import algorithm.AlgorithmFortythree;
import algorithm.UniversalMethod;
import object.similarObject;

import java.io.IOException;
import java.util.List;

public class ScenariosFourteen {
    public static double judgeScenariosFourteen (String name) throws IOException {
        double algorithmNum = 2;
        double score = 0;

        String className = name.substring(0,name.indexOf(":"));

        // 规则41：新版本中不存在与删除类相似的类
        List<similarObject> resultOfFortyone = AlgorithmFortyone.judgeSimilarity(className);
        if(resultOfFortyone.isEmpty()){
            score++;
        }else if(resultOfFortyone.size() == 1){ //删除方法的所在类可能存在，所有可能存在同名类，但是找的是不同名的相似类
            String testName = resultOfFortyone.get(0).getTarget() + ".java";
            if(testName.equals(className)){
                score++;
            }
        }

        // 规则43：新版本中没有类和删除方法的关联需求相似
        if(AlgorithmFortythree.isRequirementExist(name)){
            score++;
        }

        System.out.println("场景十四的分数是：" + score/algorithmNum);
        return score/algorithmNum;
    }
    public static void evolveOfScenariosFourteen (String name) throws IOException {
        UniversalMethod.evolveOfScenariosSevenAndFourteen(name,"十四");
    }
}
