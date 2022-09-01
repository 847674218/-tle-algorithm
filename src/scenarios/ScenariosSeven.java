package scenarios;

import algorithm.AlgorithmFortyone;
import algorithm.AlgorithmFortytwo;
import algorithm.UniversalMethod;
import object.similarObject;

import java.io.IOException;
import java.util.List;

public class ScenariosSeven {
    public static double judgeScenariosSeven(String name) throws IOException {
        double algorithmNum = 2;
        double score = 0;

        // 规则41：新版本中不存在与删除类相似的类
        List<similarObject> resultOfFortyone = AlgorithmFortyone.judgeSimilarity(name);
        if(resultOfFortyone.isEmpty()){
            score++;
        }

        // 规则42：新版本中没有类和删除类的关联需求相似
        if(AlgorithmFortytwo.isRequirementExist(name)){
            score++;
        }

        System.out.println("场景七的分数是：" + score/algorithmNum);

        return score/algorithmNum;
    }

    public static void evolveOfScenariosSeven(String name)throws IOException {
        UniversalMethod.evolveOfScenariosSevenAndFourteen(name,"七");
    }
}