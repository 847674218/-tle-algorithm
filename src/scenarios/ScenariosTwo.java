package scenarios;

import algorithm.*;
import object.similarObject;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenariosTwo {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<>();

    public static double judgeScenariosTwo(String name) throws IOException {
        isEarlyTermination = false;
        double algorithmNum = 5;
        double score = 0;
        List<String> tempTarget = new ArrayList<>();

        // 旧版本中与新添加类相似的类
        List<String> oldClass = new ArrayList<>();
        // 规则6：新添加的类和旧版本中的至少一个类相似
        List<similarObject> resultOfSix = AlgorithmSix.judgeSimilarity(name);
        if(resultOfSix.isEmpty()){
            isEarlyTermination = true;
            System.out.println("场景二的分数是：0.0");
            return 0;
        }else{
            for (similarObject object : resultOfSix) {
                oldClass.add(object.getTarget()+".java");
            }
        }

        // 对每个相似类做下面的判断
        for (String oldClassName : oldClass){
//            System.out.println("当前测试类是："+oldClassName);
            double scoreForEveryClass = 0;

             // 规则11：与新添加类相似的需求的集合是旧版本中相似类链接的需求集合的真子集
            if(AlgorithmEleven.judgeRequirementsIsSubset(name,oldClassName)){
                scoreForEveryClass++;
            }

            // 规则22：新添加类中的方法是相似类方法的集合的真子集
            if(AlgorithmTwentytwo.judgeMethodsIsSubset(name,oldClassName)) {
                scoreForEveryClass++;
            }

            // 规则26：与新添加类依赖的类是旧版本中相似类的依赖类的真子集
            if(AlgorithmTwentysix.judgeAssociationIsSubset(name, oldClassName)){
                scoreForEveryClass++;
            }

            // 规则44：新版本中不存在与相似旧类同名的新类
            if(!AlgorithmFortyfour.isOldClassExistsInNewVersion(oldClassName)){
                scoreForEveryClass++;
            }

            if(scoreForEveryClass > score){
                tempTarget.clear();
                score = scoreForEveryClass;
                tempTarget.add(oldClassName);
                tempTarget.addAll(UniversalMethod.getAllMethods(name,"newMethodName.txt"));
            }
        }

        target = tempTarget;
        if(score == 0){ target.addAll(oldClass); }
        System.out.println("场景二的分数是：" + (score+1)/algorithmNum);

        return (score+1)/algorithmNum;
    }

    public static void evolveOfScenariosTwo(String name) throws IOException {
        if(isEarlyTermination || target.isEmpty()){
            System.out.println("符合场景二 ---> 没有目标链接，提前中止判断。");
        }else{
            String oldClassName = target.get(0);
            if(AlgorithmTwentytwo.judgeMethodsIsSubset(name,oldClassName)){
                for(int i = 1;i < target.size(); i++){
                    Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
                    List<String> oldRequirements;
                    String method = target.get(i);
                    String key = oldClassName + ":" + method;
                    if(traceMatrix.containsKey(key)){
                        oldRequirements = new ArrayList<>(traceMatrix.get(key));
                        for(String oldRequirement : oldRequirements){
                            System.out.println("符合场景二 ---> 删除跟踪链接：" + oldRequirement + " " + oldClassName);
                            System.out.println("符合场景二 ---> 添加跟踪链接：" + oldRequirement + " " + name);
                        }
                    }else {
                        System.out.println("符合场景二 ---> 没有目标链接");
                    }
                }
            }else{
                System.out.println("符合场景二 ---> 没有目标链接，提前中止判断。");
            }
        }
    }
}
