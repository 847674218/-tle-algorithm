package scenarios;

import algorithm.*;
import preprocess.DataPath;
import util.GetTraceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenariosNine {
    private static boolean isEarlyTermination = false;
    private static List<String> target = new ArrayList<String>();

    public static double judgeScenariosNine(String name) throws IOException {
        double algorithmNum = 5;
        double score = 0;
        List<String> tempTarget = new ArrayList<String>();

        List<String> deletedClass = AlgorithmTwo.getDeletedClass(DataPath.getNewClassPath(),DataPath.getOldClassPath());
        for(String testClass : deletedClass){
            double testScore = 0;
            String tempNewClass = "";

            // 对两个删除类做如下判断
            if(!testClass.equals(name)){

                List<String> newClass;
                // 规则9：两个删除类跟新版本中的某个类同时相似
                List<String> resultOfNine = AlgorithmNine.judgeSimilar(name,testClass);
                if(resultOfNine.isEmpty()){
                    isEarlyTermination = true;
                    System.out.println("场景九的分数是：0.0");
                    return 0;
                }else{
                    newClass = resultOfNine;
                }

                for(String newClassName : newClass) {

                    double scoreForEveryNewClass = 0;
                    // 规则19：旧类链接需求的并集是新版本中类相似需求的子集
                    if(AlgorithmNineteen.judgeRequirementsIsSubset(name,testClass,newClassName)){
                        scoreForEveryNewClass++;
                    }

                    // 规则24：旧类中方法的并集是新类方法的子集
                    if(AlgorithmTwentyfour.judgeMethodsIsSubset(name,testClass,newClassName)){
                        scoreForEveryNewClass++;
                    }

                    // 规则33：旧类依赖的交集是新类依赖的子集
                    if(AlgorithmThirtythree.judgeAssociationIsSubset(name,testClass,newClassName)){
                        scoreForEveryNewClass++;
                    }

                    // 规则38：旧类相互依赖
                    if(AlgorithmThirtyEight.judgeAssociation(name,testClass)){
                        scoreForEveryNewClass++;
                    }

                    if(scoreForEveryNewClass > testScore){
                        testScore = scoreForEveryNewClass;
                        tempNewClass = newClassName;
                    }
                }
            }

            if(testScore > score){
                tempTarget.clear();
                score = testScore;
                tempTarget.add(testClass);
                tempTarget.add(tempNewClass);

            }
        }

        target = tempTarget;

        System.out.println("场景九的分数是：" + (score+1)/algorithmNum);
        return (score+1)/algorithmNum;
    }

    public static void evolveOfScenariosNine(String name)throws IOException {
        if(isEarlyTermination){
            System.out.println("符合场景九 ---> 没有目标链接，提前中止判断。");
        }else{
            if(target.isEmpty()){
                System.out.println("符合场景九 ---> 没有目标链接");
            }else{
                Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
                String key = target.get(0);
                if(!traceMatrix.containsKey(name) && !traceMatrix.containsKey(key)){
                    System.out.println("符合场景九 ---> 没有目标链接");
                }else if(traceMatrix.containsKey(name) && !traceMatrix.containsKey(key)){
                    List<String> requirement1 = traceMatrix.get(name);
                    System.out.println("符合场景九 ---> 删除跟踪矩阵:" + requirement1 + " " + name);
                    System.out.println("符合场景九 ---> 添加跟踪矩阵:" + requirement1 + " " + target.get(1));
                }else if(!traceMatrix.containsKey(name) && traceMatrix.containsKey(key)){
                    List<String> requirement2 = traceMatrix.get(key);
                    System.out.println("符合场景九 ---> 删除跟踪矩阵:" + requirement2 + " " + key);
                    System.out.println("符合场景九 ---> 添加跟踪矩阵:" + requirement2 + " " + target.get(1));
                }else{
                    List<String> requirement1 = traceMatrix.get(name);
                    System.out.println("符合场景九 ---> 删除跟踪矩阵:" + requirement1 + " " + name);
                    List<String> requirement2 = traceMatrix.get(key);
                    System.out.println("符合场景九 ---> 删除跟踪矩阵:" + requirement2 + " " + key);
                    requirement1.removeAll(requirement2);
                    requirement1.addAll(requirement2);
                    System.out.println("符合场景九 ---> 添加跟踪矩阵:" + requirement1 + " " + target.get(1));
                }
            }
        }
    }
}
