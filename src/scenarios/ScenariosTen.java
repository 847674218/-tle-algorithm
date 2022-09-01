package scenarios;

import algorithm.AlgorithmForty;
import algorithm.AlgorithmTen;
import object.similarObject;

import java.util.ArrayList;
import java.util.List;

public class ScenariosTen {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<String>();

    public static double judgeScenariosTen(String name) {
        double algorithmNum = 2;
        double score = 0;
        List<String>  targetRequirement = new ArrayList<String>();

        String className = name.substring(0,name.indexOf(":"));

        // 规则10：新版本的需求中至少有一条与新添加方法的所在类相似
        List<similarObject> resultOfTen = AlgorithmTen.judgeSimilarity(className);
        if(resultOfTen.isEmpty()){
            isEarlyTermination = true;
        }else{
            score++;
            for (similarObject object : resultOfTen) {
                String requirement = object.getTarget();
                targetRequirement.add(requirement);
            }
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

        target = targetRequirement;
        System.out.println("场景十的分数是：" + score/algorithmNum);

        return score/algorithmNum;
    }

    public static void evolveOfScenariosTen(String name) {
        if(isEarlyTermination){
            System.out.println("符合场景十 ---> 没有目标链接，提前中止判断。");
        }else{
            for(String requirement : target){
                System.out.println("符合场景十 ---> 添加跟踪链接：" + requirement + " " + name);
            }
        }
    }
}
