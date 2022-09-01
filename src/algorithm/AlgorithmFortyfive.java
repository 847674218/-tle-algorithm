package algorithm;

import preprocess.DataPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 规则45：这个方法在新版本中不存在
public class AlgorithmFortyfive {
    public static boolean isExist(String methodName) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader(DataPath.getMethodsNamePath() + "\\newMethodName.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(methodName)) {
                    return true;
                }
            }
            br.close();
        }

        return false;
    }
}
