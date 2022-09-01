package scenarios;

import algorithm.*;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenariosSixteen {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<String>();

    public static double judgeScenariosSixteen(String name) throws IOException {
        double algorithmNum = 4;
        double score = 0;
        List<String> tempTarget = new ArrayList<String>();

        List<String> deletedMethods = AlgorithmFour.getDeletedMethod();
        for(String deletedMethod : deletedMethods){
            double testScore = 0;
            List<String> testTarget = new ArrayList<String>();

            if(!deletedMethod.equals(name)){
                String oldClassName1 = name.substring(0,name.indexOf(":"));
                String oldClassName2 = deletedMethod.substring(0,deletedMethod.indexOf(":"));

                List<String> newClass = new ArrayList<>();
                // 规则9：两个删除方法所在类跟新版本中的某个类同时相似（排除跟某个删除类相似的情况）
                List<String> resultOfNine = AlgorithmNine.judgeSimilar(oldClassName1,oldClassName2);
                if(resultOfNine.isEmpty()
                        || (resultOfNine.size() == 1 && (resultOfNine.get(0).equals(oldClassName1) || resultOfNine.get(0).equals(oldClassName2)))
                        || (resultOfNine.size() == 2 && ((resultOfNine.get(0).equals(oldClassName1) && resultOfNine.get(1).equals(oldClassName2)) || (resultOfNine.get(1).equals(oldClassName1) && resultOfNine.get(0).equals(oldClassName2))))
                ){
                    isEarlyTermination = true;
                    System.out.println("场景十六的分数是：0.0");
                    return 0;
                }else{
                    for(String newClassName : resultOfNine){
                        if(!newClassName.equals(oldClassName1) && !newClassName.equals(oldClassName2)){
                            newClass.add(newClassName);
                        }
                    }
                }

                // 对每个疑似目标类做如下判断
                for(String newClassName : newClass){
                    double scoreForEveryNewClass = 0;

                    // 规则20：删除方法链接的需求的并集是跟疑似目标新类相似的需求集合的子集
                    if(AlgorithmTwenty.judgeRequirementsIsSubset(name,deletedMethod,newClassName)){
                        scoreForEveryNewClass++;
                    }

                    // 规则34：两个删除方法依赖方法的交集是疑似目标类中某个方法依赖方法的子集，找到这个方法
                    List<String> resultOfThirtyfour = AlgorithmThirtyfour.getMethod(name,deletedMethod,newClassName);
                    if(resultOfThirtyfour.isEmpty()){
                        isEarlyTermination = true;
                    }else{
                        scoreForEveryNewClass++;
                    }

                    // 规则39：判断两个方法是否相互依赖
                    if(AlgorithmThirtynine.judgeAssociation(name,deletedMethod)){
                        scoreForEveryNewClass++;
                    }

                    if(scoreForEveryNewClass > testScore){
                        testTarget.clear();
                        testScore = scoreForEveryNewClass;
                        testTarget = resultOfThirtyfour;
                    }
                }
                if(testScore > score){
                    tempTarget.clear();
                    score = testScore;
                    tempTarget.add(deletedMethod);
                    tempTarget.addAll(testTarget);
                }
            }
        }

        target = tempTarget;

        System.out.println("场景十六的分数是：" + (score+1)/algorithmNum);
        return (score+1)/algorithmNum;
    }

    public static void evolveOfScenariosSixteen (String name) throws IOException {
        if(isEarlyTermination || target.size() < 2){
            System.out.println("符合场景十六 ---> 没有目标链接，提前中止判断。");
        }else{
            Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
            String key = target.get(0);
            if(!traceMatrix.containsKey(name) && !traceMatrix.containsKey(key)){
                System.out.println("符合场景十六 ---> 没有目标链接");
            }else if(traceMatrix.containsKey(name) && !traceMatrix.containsKey(key)){
                List<String> requirement1 = traceMatrix.get(name);
                System.out.println("符合场景十六 ---> 删除跟踪矩阵:" + requirement1 + " " + name);
                for(int i = 1;i < target.size();i++){
                    System.out.println("符合场景十六 ---> 添加跟踪矩阵:" + requirement1 + " " + target.get(i));
                }
            }else if(!traceMatrix.containsKey(name) && traceMatrix.containsKey(key)){
                List<String> requirement2 = traceMatrix.get(key);
                System.out.println("符合场景十六 ---> 删除跟踪矩阵:" + requirement2 + " " + key);
                for(int i = 1;i < target.size();i++){
                    System.out.println("符合场景十六 ---> 添加跟踪矩阵:" + requirement2 + " " + target.get(i));
                }
            }else{
                List<String> requirement1 = traceMatrix.get(name);
                System.out.println("符合场景十六 ---> 删除跟踪矩阵:" + requirement1 + " " + name);
                List<String> requirement2 = traceMatrix.get(key);
                System.out.println("符合场景十六 ---> 删除跟踪矩阵:" + requirement2 + " " + key);
                for(int i = 1;i < target.size();i++){
                    System.out.println("符合场景十六 ---> 添加跟踪矩阵:" + requirement1 + " " + target.get(i));
                    System.out.println("符合场景十六 ---> 添加跟踪矩阵:" + requirement2 + " " + target.get(i));
                }
            }
        }
    }
}
