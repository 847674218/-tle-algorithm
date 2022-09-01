package algorithm;

import java.io.IOException;

public class AlgorithmFortysix {
    public static boolean isOldClassNotExists(String oldClassName1,String oldClassName2) throws IOException {
        boolean flag1 = AlgorithmFortyfour.isOldClassExistsInNewVersion(oldClassName1);
        boolean flag2 = AlgorithmFortyfour.isOldClassExistsInNewVersion(oldClassName2);
        return !flag1 && !flag2;
    }
}
