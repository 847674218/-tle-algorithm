import preprocess.DataPreprocess;
import util.GetSimilarityThreshold;
import scenarios.JudgeScenarios;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        // 预处理
        DataPreprocess dataProcess = new DataPreprocess();
        dataProcess.arrangeData();

        // 得到相似度阈值
        GetSimilarityThreshold.getSimilarityThreshold();

        // 场景判断
        JudgeScenarios.judgeScenarios();

        long endTime = System.currentTimeMillis();
        System.out.println("time cost:" + (endTime - startTime) * 1.0 / 1000 / 60 + "min");
    }
}