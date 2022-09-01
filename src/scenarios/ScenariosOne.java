package scenarios;

import algorithm.AlgorithmForty;
import algorithm.AlgorithmTen;
import object.similarObject;

import java.util.ArrayList;
import java.util.List;

public class ScenariosOne {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<String>();

    public static double judgeScenariosOne(String name) {
        isEarlyTermination = false;
        double algorithmNum = 2;
        double score = 0;
        List<String>  targetRequirement = new ArrayList<String>();

        // 规则10：新版本的需求中至少有一条与新添加的类相似
        List<similarObject> resultOfTen = AlgorithmTen.judgeSimilarity(name);
        if(resultOfTen.isEmpty()){
            isEarlyTermination = true;
        }else{
            score++;
            for (similarObject object : resultOfTen) {
                String requirement = object.getTarget();
                targetRequirement.add(requirement);
            }
        }

        // 规则40：新添加的类和旧版本中的类都不相似
        List<similarObject> resultOfForty = AlgorithmForty.judgeSimilarity(name);
        if(resultOfForty.isEmpty()){
            score++;
        }

        target = targetRequirement;
        System.out.println("场景一的分数是：" + score/algorithmNum);
        return score/algorithmNum;
    }

    public static void evolveOfScenariosOne(String name) {
        if(isEarlyTermination){
            System.out.println("符合场景一 ---> 没有目标链接，提前中止判断。");
        }else{
            for(String requirement : target){
                System.out.println("符合场景一 ---> 添加跟踪链接：" + requirement + " " + name);
            }
        }
    }
}