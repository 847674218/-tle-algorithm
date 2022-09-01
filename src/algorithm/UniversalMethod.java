package algorithm;

import document.TextDataset;
import ir.IR;
import object.similarObject;
import preprocess.DataPath;
import util.GetTraceMatrix;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class UniversalMethod {
    // 算法1+算法2：获取类名的集合
    public static List<String> getClassSet(String classPath) {
        List<String> classSet = new ArrayList<String>();

        File dir = new File(classPath);
        File[] children = dir.listFiles();
        for (File child : children) {
            String name = child.getName();
            classSet.add(name);
        }

        return classSet;
    }

    // 算法3+算法4：获取方法名的集合
    public static List<String> getMethodSet(String fileName) throws IOException {
        List<String> method = new ArrayList<String>();
        File file = new File(DataPath.getMethodsNamePath() + "\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() != 0) {
                method.add(line);
            }
        }
        br.close();

        return method;
    }

    // 获取某个类里面的所有方法名（仅方法名）
    public static List<String> getAllMethods(String className, String fileName) throws IOException {
        List<String> allMethods = new ArrayList<String>();

        int classNameLength = className.length();
        File file = new File(DataPath.getMethodsNamePath() + "\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            // 防止长度小于截取长度造成越界
            if (line.length() > classNameLength) {
                if (line.substring(0, classNameLength).equals(className)) {
                    allMethods.add(line.substring(classNameLength + 1));
                }
            }
        }
        br.close();

        return allMethods;
    }

    // 获取所有的依赖关系（类级别）
    public static List<String> getAllAssociationOfClass(String className, String fileName) throws IOException {
        List<String> allAssociation = new ArrayList<String>();

        File file = new File(DataPath.getAssociationRelationshipPath() + "\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        className = className.substring(0, className.indexOf("."));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() != 0 && line.substring(0, 2).equals("C:")) {
                if (line.contains(className)) {
                    String[] temp = line.split(" ");
                    String[] temp0 = temp[0].split(":|\\.|\\;");
                    for (String i : temp0) {
                        if ((i.length() > 3) && (i.charAt(0) >= 'A') && (i.charAt(0) <= 'Z') && !(i.contains("$"))
                                && !i.equals(className)) {
                            allAssociation.add(i);
                        }
                    }
                    String[] temp1 = temp[1].split(":|\\.|\\;");
                    for (String i : temp1) {
                        if ((i.length() > 3) && (i.charAt(0) >= 'A') && (i.charAt(0) <= 'Z') && !(i.contains("$"))
                                && !i.equals(className)) {
                            allAssociation.add(i);
                        }
                    }
                }
            }
        }
        br.close();
        noRepeat(allAssociation);

        return allAssociation;
    }

    // 获取所有的依赖关系（方法级别）
    public static List<String> getAllAssociationOfMethod(String methodName, String fileName) throws IOException {
        List<String> allAssociation = new ArrayList<String>();

        File file = new File(DataPath.getAssociationRelationshipPath() + "\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        methodName = methodName.substring(0, methodName.indexOf(".")) + methodName.substring(methodName.indexOf(":"));

        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() != 0 && line.substring(0, 2).equals("M:")) {
                line = line.substring(2);
                if (line.contains(methodName)) {
                    String[] temp = line.split(" ");
                    String[] temp0 = temp[0].split("\\.");
                    for (String i : temp0) {
                        if ((i.length() > 3) && (i.charAt(0) >= 'A') && (i.charAt(0) <= 'Z') && !(i.contains("$"))
                                && !i.equals(methodName)) {
                            allAssociation.add(i);
                        }
                    }
                    String[] temp1 = temp[1].split("\\.");
                    for (String i : temp1) {
                        if ((i.length() > 3) && (i.charAt(0) >= 'A') && (i.charAt(0) <= 'Z') && !(i.contains("$"))
                                && !i.equals(methodName)) {
                            allAssociation.add(i);
                        }
                    }
                }
            }
        }
        br.close();
        noRepeat(allAssociation);

        return allAssociation;
    }

    // 去重
    public static List<String> noRepeat(List<String> list) {
        HashSet<String> h = new HashSet<>(list);
        list.clear();
        list.addAll(h);

        return list;
    }

    // 判断两个类是否依赖
    public static boolean judgeAssociationOfClass(String sourceName, String targetName, String fileName)
            throws IOException {
        boolean flag = false;
        List<String> allAssociation = UniversalMethod.getAllAssociationOfClass(sourceName, fileName);
        for (String association : allAssociation) {
            if (association.equals(targetName.substring(0, targetName.indexOf(".")))) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    // 判断两个方法是否依赖
    public static boolean judgeAssociationOfMethod(String sourceName, String targetName, String fileName)
            throws IOException {
        boolean flag = false;
        List<String> allAssociation = UniversalMethod.getAllAssociationOfMethod(sourceName, fileName);
        for (String association : allAssociation) {
            if (association.equals(
                    targetName.substring(0, targetName.indexOf(".")) + targetName.substring(targetName.indexOf(":")))) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    // 场景四到场景六的演进结果
    public static void evolveOfScenariosFourToSix(String name, String targetClass, String number) throws IOException {
        Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
        if (traceMatrix.containsKey(targetClass)) {
            List<String> requirements = traceMatrix.get(targetClass);
            for (String requirement : requirements) {
                System.out.println("符合场景" + number + " ---> 添加跟踪矩阵:" + requirement + " " + name);
            }
        } else {
            System.out.println("符合场景" + number + " ---> 没有目标链接");
        }
    }

    // 场景七和场景十四的演进结果
    public static void evolveOfScenariosSevenAndFourteen(String name, String number) throws IOException {
        Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
        if (traceMatrix.containsKey(name)) {
            List<String> requirements = traceMatrix.get(name);
            for (String requirement : requirements) {
                System.out.println("符合场景" + number + " ---> 删除跟踪矩阵:" + requirement + " " + name);
            }
        } else {
            System.out.println("符合场景" + number + " ---> 没有目标链接");
        }
    }

    // 判断继承关系：判断source是否继承自target
    public static boolean isExtend(String sourceName, String targetName) throws IOException {
        boolean flag = false;
        File file = new File(DataPath.getExtendsRelationship() + "\\newExtendsRelationship.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() != 0) {
                    if (line.contains(sourceName) && line.contains("extends")
                            && line.contains(targetName.substring(0, targetName.indexOf(".")))) {
                        flag = true;
                        break;
                    }
                }
            }
        }

        return flag;
    }

    // 找出与新类相似的新需求
    public static List<String> getSimilarNewRequirementOfNewClass(String newClassName) {
        List<String> newRequirement = new ArrayList<String>();
        String irModelName = "ir.VSM";
        TextDataset textDataset = new TextDataset(newClassName, DataPath.getNewClassPreProcessedPath(),
                DataPath.getNewRequirementsPreProcessedPath());
        List<similarObject> result = IR.culSimilar(textDataset, irModelName);
        for (similarObject object : result) {
            newRequirement.add(object.getTarget());
        }
        return newRequirement;
    }

    // 获取两个旧类链接的需求的并集
    public static List<String> getRequirementUnion(String name1, String name2) throws IOException {
        Map<String, List<String>> traceMatrix = GetTraceMatrix.fromClassOrMethodToRequirements();
        List<String> oldRequirement1 = new ArrayList<String>();
        List<String> oldRequirement2;

        if (!traceMatrix.containsKey(name1) && !traceMatrix.containsKey(name2)) {
            assert false;
            oldRequirement1.add("noResult");
        } else if (traceMatrix.containsKey(name1) && !traceMatrix.containsKey(name2)) {
            oldRequirement1 = new ArrayList<>(traceMatrix.get(name1));
        } else if (!traceMatrix.containsKey(name1) && traceMatrix.containsKey(name2)) {
            oldRequirement1 = new ArrayList<>(traceMatrix.get(name2));
        } else {
            oldRequirement1 = new ArrayList<>(traceMatrix.get(name1));
            oldRequirement2 = new ArrayList<>(traceMatrix.get(name2));
            oldRequirement1.removeAll(oldRequirement2);
            oldRequirement1.addAll(oldRequirement2);
        }

        return oldRequirement1;
    }
}
