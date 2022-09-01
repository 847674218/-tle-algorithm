package scenarios;

import algorithm.*;
import object.similarObject;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenariosThree {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<String>();

    public static double judgeScenariosThree(String name) throws IOException {
        isEarlyTermination = false;
        double algorithmNum = 5;
        double score = 0;
        List<String> tempTarget = new ArrayList<String>();

        // 旧版本中与新添加类相似的类
        List<String> oldClass = new ArrayList<String>();
        // 规则7：新添加的类分别和两个旧类同时相似（在规则6上进行处理，应该有大于等于两个的相似结果）
        List<similarObject> resultOfSeven = AlgorithmSeven.judgeSimilarity(name);
        if(resultOfSeven.size() < 2){
            isEarlyTermination = true;
            System.out.println("场景三的分数是：0.0");
            return 0;
        }else{
            for (similarObject object : resultOfSeven) {
                oldClass.add(object.getTarget()+".java");
            }
        }

        for(int i = 0; i < oldClass.size();i++){
            for(int j = i + 1;j <oldClass.size();j++){
                String oldClassName1 = oldClass.get(i);
                String oldClassName2 = oldClass.get(j);

                double scoreForEveryClass = 0;

                // 规则15：与新添加类相似的需求集合是旧类链接的需求的并集的子集
                if(AlgorithmFifteen.judgeRequirementsIsSubset(name,oldClassName1,oldClassName2)){
                    scoreForEveryClass++;
                }

                // 规则23：新添加类中方法是旧类方法的并集的子集
                if(AlgorithmTwentythree.judgeMethodsIsSubset(name,oldClassName1,oldClassName2)){
                    scoreForEveryClass++;
                }

                // 规则29：新添加类中依赖是旧类依赖的并集的子集
                if(AlgorithmTwentynine.judgeAssociationIsSubset(name,oldClassName1,oldClassName2)){
                    scoreForEveryClass++;
                }

                //规则46：两个相关类在新版本中不存在同名类
                if(AlgorithmFortysix.isOldClassNotExists(oldClassName1,oldClassName2)){
                    scoreForEveryClass++;
                }

                if(scoreForEveryClass > score){
                    tempTarget.clear();
                    score = scoreForEveryClass;
                    tempTarget.add(oldClassName1);
                    tempTarget.add(oldClassName2);
                }
            }
        }

        target = tempTarget;
        System.out.println("场景三的分数是：" + (score+1)/algorithmNum);

        return (score+1)/algorithmNum;
    }

    public static void evolveOfScenariosThree(String name)throws IOException {
        if(isEarlyTermination){
            System.out.println("符合场景三 ---> 没有目标链接，提前中止判断。");
        }else{
            if(target.isEmpty()){
                System.out.println("符合场景三 ---> 没有目标链接");
            }else{
                Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
                String oldClassName1 = target.get(0);
                String oldClassName2 = target.get(1);
                if(traceMatrix.containsKey(oldClassName1) && traceMatrix.containsKey(oldClassName2)){
                    List<String> requirement1 = traceMatrix.get(oldClassName1);
                    System.out.println("符合场景三 ---> 删除跟踪矩阵:" + requirement1 + " " + oldClassName1);
                    List<String> requirement2 = traceMatrix.get(oldClassName2);
                    System.out.println("符合场景三 ---> 删除跟踪矩阵:" + requirement2 + " " + oldClassName2);
                    requirement1.removeAll(requirement2);
                    requirement1.addAll(requirement2);
                    System.out.println("符合场景三 ---> 添加跟踪矩阵:" + requirement1 + " " + name);
                } else if(traceMatrix.containsKey(oldClassName1) && !traceMatrix.containsKey(oldClassName2)) {
                    List<String> requirement1 = traceMatrix.get(oldClassName1);
                    System.out.println("符合场景三 ---> 删除跟踪矩阵:" + requirement1 + " " + oldClassName1);
                    System.out.println("符合场景三 ---> 添加跟踪矩阵:" + requirement1 + " " + name);
                }else if(!traceMatrix.containsKey(oldClassName1) && traceMatrix.containsKey(oldClassName2)) {
                    List<String> requirement2 = traceMatrix.get(oldClassName2);
                    System.out.println("符合场景三 ---> 删除跟踪矩阵:" + requirement2 + " " + oldClassName2);
                    System.out.println("符合场景三 ---> 添加跟踪矩阵:" + requirement2 + " " + name);
                }else {
                    System.out.println("符合场景三 ---> 没有目标链接");
                }
            }
        }
    }
}
