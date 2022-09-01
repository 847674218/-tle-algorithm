package algorithm;

import preprocess.DataPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 规则44：新版本中不存在与相似旧类同名的新类
public class AlgorithmFortyfour {
    public static boolean isOldClassExistsInNewVersion(String oldClassName) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader(DataPath.getClassNamePath() + "\\newClassName.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(oldClassName)) {
                    return true;
                }
            }
            br.close();
        }

        return false;
    }
}