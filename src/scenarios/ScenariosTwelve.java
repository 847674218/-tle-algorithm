package scenarios;

import algorithm.*;
import object.similarObject;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenariosTwelve {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<String>();

    public static double judgeScenariosTwelve(String name) throws IOException {
        isEarlyTermination = false;
        double algorithmNum = 4;
        double score = 0;
        List<String> tempTarget = new ArrayList<String>();

        String className = name.substring(0,name.indexOf(":"));

        // 旧版本中与新添加类相似的类
        List<String> oldClass = new ArrayList<String>();
        // 规则7：新添加的类分别和两个旧类同时相似（在规则6上进行处理，应该有大于等于两个的相似结果）
        List<similarObject> resultOfSeven = AlgorithmSeven.judgeSimilarity(className);
        if(resultOfSeven.size() < 2 ||  (resultOfSeven.size() == 2 && ((resultOfSeven.get(0).getTarget() + ".java").equals(className) || (resultOfSeven.get(1).getTarget() + ".java").equals(className)))){
            isEarlyTermination = true;
            System.out.println("场景十二的分数是：0.0");
            return 0;
        }else{
            for (similarObject object : resultOfSeven) {
                String oldClassName = object.getTarget() + ".java";
                if(!oldClassName.equals(className)) {
                    oldClass.add(oldClassName);
                }
            }
        }

        // 对每组疑似目标类做接下来的判断
        for(int i = 0; i < oldClass.size();i++) {
            for (int j = i + 1; j < oldClass.size(); j++) {
                target.clear();
                String oldClassName1 = oldClass.get(i);
                String oldClassName2 = oldClass.get(j);
                double tempScoreForMethod = 0;
                List<String> tempTarget1 = new ArrayList<>();

                double scoreForEveryClass = 0;

                // 规则16：找到可能的目标方法（全部满足）
                List<String> resultOfSixteen = AlgorithmSixteen.getTargetMethod(className, oldClassName1, oldClassName2); // 仅方法名
                if(resultOfSixteen.isEmpty()){
                    isEarlyTermination = true;
                }else{
                    scoreForEveryClass++;
                }

                for(int k = 0;k < resultOfSixteen.size();k = k + 2){
                    String oldMethodName1 = resultOfSixteen.get(k);
                    String oldMethodName2 = resultOfSixteen.get(k+1);

                    double scoreForEveryMethod = 0;

                    // 规则30：跟新方法的依赖是旧方法依赖的并集的子集
                    if(AlgorithmThirty.judgeAssociation(oldMethodName1,oldMethodName2,name)){
                        scoreForEveryMethod++;
                    }

                    // 规则45：这两个方法在新版本中不存在
                    if(AlgorithmFortyseven.isNotExist(oldMethodName1,oldClassName2)){
                        scoreForEveryMethod++;
                    }

                    if(scoreForEveryMethod > tempScoreForMethod){
                        tempTarget1.clear();
                        tempScoreForMethod = scoreForEveryMethod;
                        tempTarget1.add(oldMethodName1);
                        tempTarget1.add(oldMethodName2);
                    }
                }
                if(tempScoreForMethod + scoreForEveryClass > score){
                    score = tempScoreForMethod + scoreForEveryClass;
                    tempTarget = tempTarget1;
                }
            }
        }

        target = tempTarget;

        System.out.println("场景十二的分数是：" + (score+1)/algorithmNum);
        return (score+1)/algorithmNum;
    }

    public static void evolveOfScenariosTwelve(String name)throws IOException {
        if(isEarlyTermination){
            System.out.println("符合场景十二 ---> 没有目标链接，提前中止判断。");
        }else{
            if(target.isEmpty()){
                System.out.println("符合场景十二 ---> 没有目标链接");
            }else{
                Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
                String oldMethodName1 = target.get(0);
                String oldMethodName2 = target.get(1);
                if(traceMatrix.containsKey(oldMethodName1) && traceMatrix.containsKey(oldMethodName2)){
                    List<String> requirement1 = traceMatrix.get(oldMethodName1);
                    System.out.println("符合场景十二 ---> 删除跟踪矩阵:" + requirement1 + " " + oldMethodName1);
                    List<String> requirement2 = traceMatrix.get(oldMethodName2);
                    System.out.println("符合场景十二 ---> 删除跟踪矩阵:" + requirement2 + " " + oldMethodName2);
                    requirement1.removeAll(requirement2);
                    requirement1.addAll(requirement2);
                    System.out.println("符合场景十二 ---> 添加跟踪矩阵:" + requirement1 + " " + name);
                } else if(traceMatrix.containsKey(oldMethodName1) && !traceMatrix.containsKey(oldMethodName2)) {
                    List<String> requirement1 = traceMatrix.get(oldMethodName1);
                    System.out.println("符合场景十二 ---> 删除跟踪矩阵:" + requirement1 + " " + oldMethodName1);
                    System.out.println("符合场景十二 ---> 添加跟踪矩阵:" + requirement1 + " " + name);
                }else if(!traceMatrix.containsKey(oldMethodName1) && traceMatrix.containsKey(oldMethodName2)) {
                    List<String> requirement2 = traceMatrix.get(oldMethodName2);
                    System.out.println("符合场景十二 ---> 删除跟踪矩阵:" + requirement2 + " " + oldMethodName2);
                    System.out.println("符合场景十二 ---> 添加跟踪矩阵:" + requirement2 + " " + name);
                }else {
                    System.out.println("符合场景十二 ---> 没有目标链接");
                }
            }
        }
    }
}
