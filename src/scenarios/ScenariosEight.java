package scenarios;

import algorithm.*;
import object.similarObject;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenariosEight {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<String>();

    public static double judgeScenariosEight(String name) throws IOException {
        isEarlyTermination = false;
        double algorithmNum = 5;
        double score = 0;
        List<String> tempTarget = new ArrayList<String>();

        // 新版本中与删除类相似的类
        List<String> newClass = new ArrayList<String>();
        // 规则8：删除类和新版本中的两个类相似
        List<similarObject> resultOfEight = AlgorithmEight.judgeSimilarity(name);
        if(resultOfEight.size() < 2){
            isEarlyTermination= true;
            System.out.println("场景八的分数是：0.0");
            return 0;
        }else{
            for (similarObject object : resultOfEight) {
                newClass.add(object.getTarget()+".java");
            }
        }

        for(int i = 0; i < newClass.size();i++){
            for(int j = i + 1;j < newClass.size();j++){
                String newClassName1 = newClass.get(i);
                String newClassName2 = newClass.get(j);

                double scoreForEveryClass = 0;

                // 规则17：新版本中与相似类相似的需求的交集是删除类关联需求的集合的子集
                if(AlgorithmSeventeen.judgeRequirementsIsSubset(name,newClassName1,newClassName2)){
                    scoreForEveryClass++;
                }

                // 规则25：删除类中的方法是两个相似类方法交集的子集
                if(AlgorithmTwentyfive.judgeMethodsIsSubset(name,newClassName1,newClassName2)){
                    scoreForEveryClass++;
                }

                // 规则31：删除类的依赖是两个相似类依赖交集的子集
                if(AlgorithmThirtyone.judgeAssociationIsSubset(name,newClassName1,newClassName2)){
                    scoreForEveryClass++;
                }

                // 规则36：两个相似类互相依赖
                if(AlgorithmThirtysix.judgeAssociation(newClassName1,newClassName2)){
                    scoreForEveryClass++;
                }

                if(scoreForEveryClass > score){
                    tempTarget.clear();
                    score = scoreForEveryClass;
                    tempTarget.add(newClassName1);
                    tempTarget.add(newClassName2);
                }
            }
        }

        target = tempTarget;
        System.out.println("场景八的分数是：" + (score+1)/algorithmNum);

        return (score+1)/algorithmNum;
    }

    public static void evolveOfScenariosEight(String name)throws IOException {
        if(isEarlyTermination){
            System.out.println("符合场景八 ---> 没有目标链接，提前中止判断。");
        }else {
            if(target.size() == 2){
                Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
                if(traceMatrix.containsKey(name)){
                    List<String> requirements = traceMatrix.get(name);
                    for(String requirement : requirements){
                        System.out.println("符合场景八 ---> 删除跟踪链接：" + requirement + " " + name);
                        System.out.println("符合场景八 ---> 添加跟踪矩阵：" + requirement + " " + target.get(0));
                        System.out.println("符合场景八 ---> 添加跟踪矩阵：" + requirement + " " + target.get(1));
                    }
                }else{
                    System.out.println("符合场景八 ---> 没有目标链接");
                }
            }else{
                System.out.println("符合场景八 ---> 没有目标链接");
            }
        }
    }
}
