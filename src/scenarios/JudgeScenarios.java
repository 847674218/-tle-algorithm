package scenarios;

import algorithm.*;
import preprocess.DataPath;

import java.io.IOException;
import java.util.*;

public class JudgeScenarios {
    public static void judgeScenarios() throws IOException {
        List<String> addedClass = AlgorithmOne.getAddedClass(DataPath.getNewClassPath(),DataPath.getOldClassPath());
        System.out.println("打印所有新版本中新添加的类：" + addedClass);

        double [] addedClassScenariosScore = new double[6];

        // 对每个新添加的类依次做场景判断
        for (String name : addedClass) {
            System.out.println("当前判断的新添加的类的名字是：" + name);

            // 把所有结果取出来
            addedClassScenariosScore[0] = ScenariosOne.judgeScenariosOne(name);     //
            addedClassScenariosScore[1] = ScenariosTwo.judgeScenariosTwo(name);     //
            addedClassScenariosScore[2] = ScenariosThree.judgeScenariosThree(name); //
            addedClassScenariosScore[3] = ScenariosFour.judgeScenariosFour(name);   //
            addedClassScenariosScore[4] = ScenariosFive.judgeScenariosFive(name);   //
            addedClassScenariosScore[5] = ScenariosSix.judgeScenariosSix(name);     //

            // 得到最大的场景分数
            double maxScore = 0;
            for (int j = 0; j < 6; j++) {
                if (addedClassScenariosScore[j] > maxScore) {
                    maxScore = addedClassScenariosScore[j];
                }
            }
            if(maxScore == 0){
                System.out.println("没有匹配的变更场景");
                System.out.println("-------------------------------------------------------------");
                continue;
            }

            ArrayList<Integer> index = new ArrayList<>();
            if(addedClassScenariosScore[0] == maxScore){ index.add(0); }
            if(addedClassScenariosScore[1] == maxScore){ index.add(1); }
            if(addedClassScenariosScore[2] == maxScore){ index.add(2); }
            if(addedClassScenariosScore[3] == maxScore){ index.add(3); }
            if(addedClassScenariosScore[4] == maxScore){ index.add(4); }
            if(addedClassScenariosScore[5] == maxScore){ index.add(5); }

            for(int i : index){
                switch (i) {
                    case 0:
                        ScenariosOne.evolveOfScenariosOne(name);break;
                    case 1:
                        ScenariosTwo.evolveOfScenariosTwo(name);break;
                    case 2:
                        ScenariosThree.evolveOfScenariosThree(name);break;
                    case 3:
                        ScenariosFour.evolveOfScenariosFour(name);break;
                    case 4:
                        ScenariosFive.evolveOfScenariosFive(name);break;
                    case 5:
                        ScenariosSix.evolveOfScenariosSix(name);break;
                }
            }
            System.out.println("-------------------------------------------------------------");
        }


        List<String> deletedClass = AlgorithmTwo.getDeletedClass(DataPath.getNewClassPath(),DataPath.getOldClassPath());
        System.out.println("打印所有旧版本中删除的类：" + deletedClass);

        double [] deletedClassScenariosScore = new double[3];

        // 对每个删除的类做判断，得到变更场景分数
        for (String name : deletedClass) {
            System.out.println("当前判断的删除类的名字是：" + name);

            deletedClassScenariosScore[0] = ScenariosSeven.judgeScenariosSeven(name);
            deletedClassScenariosScore[1] = ScenariosEight.judgeScenariosEight(name);
            deletedClassScenariosScore[2] = ScenariosNine.judgeScenariosNine(name);

            double maxScore = 0;
            for (int j = 0; j < 3; j++) {
                if (deletedClassScenariosScore[j] > maxScore) {
                    maxScore = deletedClassScenariosScore[j];
                }
            }
            if(maxScore == 0){
                System.out.println("没有匹配的变更场景");
                System.out.println("-------------------------------------------------------------");
                continue;
            }

            ArrayList<Integer> index = new ArrayList<>();
            if(deletedClassScenariosScore[0] == maxScore){ index.add(0); }
            if(deletedClassScenariosScore[1] == maxScore){ index.add(1); }
            if(deletedClassScenariosScore[2] == maxScore){ index.add(2); }

            for(int i : index){
                switch (i) {
                    case 0:
                        ScenariosSeven.evolveOfScenariosSeven(name);
                        break;
                    case 1:
                        ScenariosEight.evolveOfScenariosEight(name);
                        break;
                    case 2:
                        ScenariosNine.evolveOfScenariosNine(name);
                        break;
                }
            }
            System.out.println("-------------------------------------------------------------");
        }


//        List<String> addedMethod = AlgorithmThree.getAddedMethod();
//        System.out.println("打印所有新版本中新添加的方法：" + addedMethod);
//
//        double [] addMethodScenariosScore = new double[4];
//
//        // 对每个新添加的方法做判断，得到变更场景分数
//        for (String name : addedMethod) {
//            System.out.println("当前判断的新添加的方法的名字是：" + name);
//
//            addMethodScenariosScore[0] = ScenariosTen.judgeScenariosTen(name);
//            addMethodScenariosScore[1] = ScenariosEleven.judgeScenariosEleven(name);
//            addMethodScenariosScore[2] = ScenariosTwelve.judgeScenariosTwelve(name);
//            addMethodScenariosScore[3] = ScenariosThirteen.judgeScenariosThirteen(name);
//
//            double maxScore = 0;
//            for (int j = 0; j < 4; j++) {
//                if (addMethodScenariosScore[j] > maxScore) {
//                    maxScore = addMethodScenariosScore[j];
//                }
//            }
//            if(maxScore == 0){
//                System.out.println("没有匹配的变更场景");
//                System.out.println("-------------------------------------------------------------");
//                continue;
//            }
//
//            ArrayList<Integer> index = new ArrayList<>();
//            if(addMethodScenariosScore[0] == maxScore){ index.add(0); }
//            if(addMethodScenariosScore[1] == maxScore){ index.add(1); }
//            if(addMethodScenariosScore[2] == maxScore){ index.add(2); }
//            if(addMethodScenariosScore[3] == maxScore){ index.add(3); }
//
//            for(int i : index){
//                switch (i) {
//                case 0:
//                    ScenariosTen.evolveOfScenariosTen(name);
//                    break;
//                case 1:
//                    ScenariosEleven.evolveOfScenariosEleven(name);
//                    break;
//                case 2:
//                    ScenariosTwelve.evolveOfScenariosTwelve(name);
//                    break;
//                case 3:
//                    ScenariosThirteen.evolveOfScenariosThirteen(name);
//                    break;
//                }
//            }
//            System.out.println("-------------------------------------------------------------");
//        }
//
//
//        List<String> deletedMethod = AlgorithmFour.getDeletedMethod();
//        System.out.println("打印所有新版本中删除的方法：" + deletedMethod);
//
//        double [] deletedMethodScenariosScore = new double[3];
//
//        // 对每个新添加的方法做判断，得到变更场景分数
//        for (String name : deletedMethod) {
//            System.out.println("当前判断的删除的方法的名字是：" + name);
//
//            deletedMethodScenariosScore[0] = ScenariosFourteen.judgeScenariosFourteen(name);
//            deletedMethodScenariosScore[1] = ScenariosFifteen.judgeScenariosFifteen(name);
//            deletedMethodScenariosScore[2] = ScenariosSixteen.judgeScenariosSixteen(name);
//
//            double maxScore = 0;
//            for (int j = 0; j < 3; j++) {
//                if (deletedMethodScenariosScore[j] > maxScore) {
//                    maxScore = deletedMethodScenariosScore[j];
//                }
//            }
//            if(maxScore == 0){
//                System.out.println("没有匹配的变更场景");
//                System.out.println("-------------------------------------------------------------");
//                continue;
//            }
//
//            ArrayList<Integer> index = new ArrayList<>();
//            if(deletedMethodScenariosScore[0] == maxScore){ index.add(0); }
//            if(deletedMethodScenariosScore[1] == maxScore){ index.add(1); }
//            if(deletedMethodScenariosScore[2] == maxScore){ index.add(2); }
//
//
//            for(int i : index){
//                switch (i) {
//                    case 0:
//                        ScenariosFourteen.evolveOfScenariosFourteen(name);
//                        break;
//                    case 1:
//                        ScenariosFifteen.evolveOfScenariosFifteen(name);
//                        break;
//                    case 2:
//                        ScenariosSixteen.evolveOfScenariosSixteen(name);
//                        break;
//                }
//            }
//            System.out.println("-------------------------------------------------------------");
//        }
//
//        List<String> changedMethod = AlgorithmFive.getChangeMethod();
//        System.out.println("打印所有新版本中变更的方法：" + changedMethod);
//
//        double [] changedMethodScenariosScore = new double[2];
//
//        // 对每个新添加的方法做判断，得到变更场景分数
//        for (String name : changedMethod) {
//            System.out.println("当前判断的变更方法的名字是：" + name);
//
//            changedMethodScenariosScore[0] = ScenariosSeventeen.judgeScenariosSeventeen(name);
//            changedMethodScenariosScore[1] = ScenariosEighteen.judgeScenariosEighteen(name);
//
//            double maxScore = 0;
//            for (int j = 0; j < 2; j++) {
//                if (changedMethodScenariosScore[j] > maxScore) {
//                    maxScore = changedMethodScenariosScore[j];
//                }
//            }
//            if(maxScore == 0){
//                System.out.println("没有匹配的变更场景");
//                System.out.println("-------------------------------------------------------------");
//                continue;
//            }
//
//            ArrayList<Integer> index = new ArrayList<>();
//            if(changedMethodScenariosScore[0] == maxScore){ index.add(0); }
//            if(changedMethodScenariosScore[1] == maxScore){ index.add(1); }
//
//            for(int i : index){
//                switch (i) {
//                    case 0:
//                        ScenariosSeventeen.evolveOfScenariosSeventeen(name);
//                        break;
//                    case 1:
//                        ScenariosEighteen.evolveOfScenariosEighteen(name);
//                        break;
//                }
//            }
//            System.out.println("-------------------------------------------------------------");
//        }
    }
}
