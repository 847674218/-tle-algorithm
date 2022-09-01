package scenarios;

import algorithm.AlgorithmFortyfive;
import algorithm.AlgorithmSix;
import algorithm.AlgorithmTwelve;
import algorithm.AlgorithmTwentyeight;
import object.similarObject;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenariosEleven {
    private static boolean isEarlyTermination = false;
    private static String target = "";

    public static double judgeScenariosEleven(String name) throws IOException {
        isEarlyTermination = false;
        double algorithmNum = 4;
        double score = 0;
        String tempTarget = "";

        String className = name.substring(0,name.indexOf(":"));

        // 旧版本中与新添加类相似的类
        List<String> oldClass = new ArrayList<String>();
        // 规则6：新添加的类和旧版本中的至少一个非同名类相似
        List<similarObject> resultOfSix = AlgorithmSix.judgeSimilarity(className);
        if(resultOfSix.isEmpty() || (resultOfSix.size() == 1 && (resultOfSix.get(0).getTarget() + ".java").equals(className))){
            isEarlyTermination = true;
            return 0;
        }else{
            for (similarObject object : resultOfSix) {
                String oldClassName = object.getTarget() + ".java";
                if(!oldClassName.equals(className)){
                    oldClass.add(oldClassName);
                }
            }
        }

        // 对每个疑似目标类做接下来的判断
        for(String oldClassName : oldClass){ // 带后缀名的类名
            double scoreForEveryClass = 0;
            double tempScoreForMethod = 0;
            String tempTarget1 = "";

            // 规则12：找到可能的目标方法（可能有多个）
            List<String> resultOfTwelve = AlgorithmTwelve.getTargetMethod(className,oldClassName); // 仅方法名
            if(resultOfTwelve.isEmpty()){
                isEarlyTermination = true;
            }else{
                scoreForEveryClass++;
            }

            // 对当前判断类下的每个方法做如下判断
            for(String method : resultOfTwelve){ // 仅方法名
                double scoreForEveryMethod = 0;

                // 规则28：跟目标方法依赖的其他方法是新添加方法依赖的其他方法的子集
                String targetMethodName = oldClassName + ":" + method; //带后缀类名+方法名
                if(AlgorithmTwentyeight.judgeAssociation(targetMethodName,name)){
                    scoreForEveryMethod++;
                }

                // 规则45：这个方法在新版本中不存在
                if(!AlgorithmFortyfive.isExist(targetMethodName)){
                    scoreForEveryMethod++;
                }

                if(scoreForEveryMethod > tempScoreForMethod){
                    tempScoreForMethod = scoreForEveryMethod;
                    tempTarget1 = targetMethodName;
                }
            }

            if(tempScoreForMethod + scoreForEveryClass > score){
                score = tempScoreForMethod + scoreForEveryClass;
                tempTarget = tempTarget1;
            }
        }

        target = tempTarget;

        System.out.println("场景十一的分数是：" + (score+1)/algorithmNum);
        return (score+1)/algorithmNum;
    }

    public static void evolveOfScenariosEleven(String name)throws IOException {
        if(isEarlyTermination || target.length() == 0){
            System.out.println("符合场景十一 ---> 没有目标链接，提前中止判断。");
        }else{
            Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
            if(traceMatrix.containsKey(target)){
                List<String> oldRequirements = new ArrayList<String>(traceMatrix.get(target));
                for(String oldRequirement : oldRequirements){
                    System.out.println("符合场景十一 ---> 删除跟踪链接：" + oldRequirement + " " + target);
                    System.out.println("符合场景十一 ---> 添加跟踪链接：" + oldRequirement + " " + name);
                }
            }
        }
    }
}
