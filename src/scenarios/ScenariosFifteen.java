package scenarios;

import algorithm.*;
import object.similarObject;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenariosFifteen {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<String>();

    public static double judgeScenariosFifteen (String name) throws IOException {
        isEarlyTermination = false;
        double algorithmNum = 5;
        double score = 0;

        String className = name.substring(0,name.indexOf(":"));

        // 新版本中与删除类相似的类
        List<String> newClass = new ArrayList<String>();
        // 规则8：删除类和新版本中的两个类相似
        List<similarObject> resultOfEight = AlgorithmEight.judgeSimilarity(className);
        if(resultOfEight.size() < 2 ||  (resultOfEight.size() == 2 && ((resultOfEight.get(0).getTarget() + ".java").equals(className) || (resultOfEight.get(1).getTarget() + ".java").equals(className)))){
            isEarlyTermination= true;
            System.out.println("场景十五的分数是：0.0");
            return 0;
        }else{
            for (similarObject object : resultOfEight) {
                String newClassName = object.getTarget() + ".java";
                if(!newClassName.equals(className)){
                    newClass.add(newClassName);
                }
            }
        }

        // 对每组疑似目标类做接下来的判断
        for(int i = 0; i < newClass.size();i++) {
            for (int j = i + 1; j < newClass.size(); j++) {
                String newClassName1 = newClass.get(i);
                String newClassName2 = newClass.get(j);
                double scoreForEveryClass = 0;
                List<String> tempTarget = new ArrayList<String>();

                // 规则18：新版本中的类相似的需求的并集是跟删除方法关联的需求的子集
                if(AlgorithmEighteen.judgeRequirementsIsSubset(name,newClassName1,newClassName2)){
                    scoreForEveryClass++;
                }

                // 规则32：跟删除方法依赖的其他方法是新版本目标类中方法的依赖方法的交集的子集
                List<String> resultOfThirtytwo = AlgorithmThirtytwo.getTargetMethod(name,newClassName1,newClassName2);
                if(!resultOfThirtytwo.isEmpty()){
                    scoreForEveryClass++;
                    tempTarget =  resultOfThirtytwo;
                }else{
                    isEarlyTermination = true;
                }

                for(int k = 0; k < resultOfThirtytwo.size(); k = k + 2){
                    String newMethod1 = resultOfThirtytwo.get(k);
                    String newMethod2 = resultOfThirtytwo.get(k+1);

                    // 规则37：判断两个方法是否相互依赖
                    if(AlgorithmThirtyseven.judgeAssociation(newMethod1,newMethod2)){
                        scoreForEveryClass++;
                        tempTarget.clear();
                        tempTarget.add(newMethod1);
                        tempTarget.add(newMethod2);
                    }
                }

                if(scoreForEveryClass > score){
                    score = scoreForEveryClass;
                    target = tempTarget;
                }
            }
        }

        System.out.println("场景十五的分数是：" + (score+1)/algorithmNum);
        return (score+1)/algorithmNum;
    }

    public static void evolveOfScenariosFifteen (String name) throws IOException {
        if(isEarlyTermination || target.size() ==0){
            System.out.println("符合场景十五 ---> 没有目标链接，提前中止判断。");
        }else{
            Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
            if(traceMatrix.containsKey(name)){
                List<String> oldRequirements = new ArrayList<String>(traceMatrix.get(name));
                for(String oldRequirement : oldRequirements){
                    System.out.println("符合场景十五 ---> 删除跟踪链接：" + oldRequirement + " " + name);
                    System.out.println("符合场景十五 ---> 添加跟踪链接：" + oldRequirement + " " + target.get(0));
                    System.out.println("符合场景十五 ---> 添加跟踪链接：" + oldRequirement + " " + target.get(1));
                }
            }
        }
    }
}
