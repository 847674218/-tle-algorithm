package scenarios;

import algorithm.*;
import object.similarObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScenariosSix {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<String>();

    public static double judgeScenariosSix(String name) throws IOException {
        isEarlyTermination = false;
        double algorithmNum = 6;
        double score = 0;
        List<String> tempTarget = new ArrayList<String>();

        // 旧版本中与新添加类相似的类
        List<String> oldClass = new ArrayList<String>();
        // 规则6：新添加的类和旧版本中的至少一个类相似
        List<similarObject> resultOfSix = AlgorithmSix.judgeSimilarity(name);
        if(resultOfSix.isEmpty()){
            isEarlyTermination = true;
            System.out.println("场景六的分数是：0.0");
            return 0;
        }else{
            for (similarObject object : resultOfSix) {
                oldClass.add(object.getTarget()+".java");
            }
        }

        for (String oldClassName : oldClass) {
            double scoreForEveryClass = 0;

            // 规则14：至少存在一个新版本的需求与新类相似与旧类不相似
            if(AlgorithmFourteen.judgeSimilarity(name,oldClassName)){
                scoreForEveryClass++;
            }

            // 规则22：所有新添加类中的方法应该都包含于相似类方法的集合中
            if(AlgorithmTwentytwo.judgeMethodsIsSubset(name,oldClassName)){
                scoreForEveryClass++;
            }

            // 规则26：与新添加类依赖的类是旧版本中相似类的依赖类的子集
            if(AlgorithmTwentysix.judgeAssociationIsSubset(name,oldClassName)){
                scoreForEveryClass++;
            }

            // 规则35：新添加的类在新版本中和目标类的同名类依赖
            if(AlgorithmThirtyfive.judgeAssociation(name,oldClassName)){
                scoreForEveryClass++;
            }

            // 规则49：关联类继承新添加的类
            if(AlgorithmFortynine.isExtendSuperClass(name,oldClassName)){
                scoreForEveryClass++;
            }

            if(scoreForEveryClass > score){
                tempTarget.clear();
                score = scoreForEveryClass;
                tempTarget.add(oldClassName);
            }
        }

        target = tempTarget;
        System.out.println("场景六的分数是：" + (score+1)/algorithmNum);

        return (score+1)/algorithmNum;
    }

    public static void evolveOfScenariosSix(String name)throws IOException {
        if(isEarlyTermination){
            System.out.println("符合场景六 ---> 没有目标链接，提前中止判断。");
        }else{
            String targetClass = target.get(0);
            UniversalMethod.evolveOfScenariosFourToSix(name,targetClass,"六");
        }
    }
}
