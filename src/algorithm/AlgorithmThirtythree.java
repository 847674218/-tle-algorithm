package algorithm;

import java.io.IOException;
import java.util.List;

public class AlgorithmThirtythree {
    public static boolean judgeAssociationIsSubset(String name1,String name2,String newClassName) throws IOException {
        List<String> associationWithOldClass = UniversalMethod.getAllAssociationOfClass(name1,"oldAssociationRelationship.txt");
        List<String> associationWithOldClass1 = UniversalMethod.getAllAssociationOfClass(name2,"oldAssociationRelationship.txt");
        associationWithOldClass.retainAll(associationWithOldClass1);

        List<String> associationWithNewClass = UniversalMethod.getAllAssociationOfClass(newClassName,"newAssociationRelationship.txt");

        int count = 0;
        for(String oldClass : associationWithOldClass){
            for(String newClass : associationWithNewClass){
                if(oldClass.equals(newClass)){
                    count++;
                }
            }
        }

        return (count == associationWithOldClass.size() && count!=0);
    }
}
