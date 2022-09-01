package preprocess;

public class DataPath {

    // 原始文件（新版本需求、旧版本需求、新版本代码、旧版本代码）路径
    private static String oldProjectPath = "data/project_old";
    private static String newProjectPath = "data/project_new";
    private static String oldRequirementsPath = "data/requirements_old";
    private static String newRequirementsPath = "data/requirements_new";

    public static String getOldProjectPath() { return oldProjectPath; }
    public static void setOldProjectPath(String oldProjectPath) { DataPath.oldProjectPath = oldProjectPath; }

    public static String getNewProjectPath() { return newProjectPath; }
    public static void setNewProjectPath(String newProjectPath) { DataPath.newProjectPath = newProjectPath; }

    public static String getOldRequirementsPath() { return oldRequirementsPath; }
    public static void setOldRequirementsPath(String oldRequirementsPath) { DataPath.oldRequirementsPath = oldRequirementsPath; }

    public static String getNewRequirementsPath() { return newRequirementsPath; }
    public static void setNewRequirementsPath(String newRequirementsPath) { DataPath.newRequirementsPath = newRequirementsPath; }

    // 处理结果文件路径
    private static String classNamePath = "intermediateResult/className";
    private static String oldClassPath = "intermediateResult/class_old";
    private static String newClassPath = "intermediateResult/class_new";
    private static String methodsNamePath = "intermediateResult/methodsName";
    private static String oldMethodsPath = "intermediateResult/method_old";
    private static String newMethodsPath = "intermediateResult/method_new";
    private static String oldClassTxtPath =  "intermediateResult/class_old_txt";
    private static String newClassTxtPath =  "intermediateResult/class_new_txt";
    private static String oldClassXmlPath =  "intermediateResult/class_old_xml";
    private static String newClassXmlPath =  "intermediateResult/class_new_xml";
    private static String oldClassPreProcessedPath =  "intermediateResult/class_old_preprocessed";
    private static String newClassPreProcessedPath =  "intermediateResult/class_new_preprocessed";
    private static String oldMethodsPreProcessedPath = "intermediateResult/method_old_preprocessed";
    private static String newMethodsPreProcessedPath = "intermediateResult/method_new_preprocessed";
    private static String oldRequirementsDivision = "intermediateResult/requirements_old_division";
    private static String newRequirementsDivision = "intermediateResult/requirements_new_division";
    private static String oldRequirementsPreProcessedPath =  "intermediateResult/requirements_old_preprocessed";
    private static String newRequirementsPreProcessedPath =  "intermediateResult/requirements_new_preprocessed";
    private static String associationRelationshipPath = "intermediateResult/associationRelationship";
    private static String extendsRelationship = "intermediateResult/extendsRelationship";

    public static String getClassNamePath() {
        return classNamePath;
    }
    public static void setClassNamePath(String classNamePath) { DataPath.classNamePath = classNamePath; }

    public static String getOldClassPath() { return oldClassPath; }
    public static void setOldClassPath(String oldClassPath) { DataPath.oldClassPath = oldClassPath; }

    public static String getNewClassPath() { return newClassPath; }
    public static void setNewClassPath(String newClassPath) { DataPath.newClassPath = newClassPath; }

    public static String getMethodsNamePath() { return methodsNamePath; }
    public static void setMethodsNamePath(String methodsNamePath) { DataPath.methodsNamePath = methodsNamePath; }

    public static String getOldMethodsPath() { return oldMethodsPath; }
    public static void setOldMethodsPath(String oldMethodsPath) { DataPath.oldMethodsPath = oldMethodsPath; }

    public static String getNewMethodsPath() { return newMethodsPath; }
    public static void setNewMethodsPath(String newMethodsPath) { DataPath.newMethodsPath = newMethodsPath; }

    public static String getOldClassTxtPath() { return oldClassTxtPath; }
    public static void setOldClassTxtPath(String oldClassTxtPath) { DataPath.oldClassTxtPath = oldClassTxtPath; }

    public static String getNewClassTxtPath() { return newClassTxtPath; }
    public static void setNewClassTxtPath(String newClassTxtPath) { DataPath.newClassTxtPath = newClassTxtPath; }

    public static String getOldClassXmlPath() { return oldClassXmlPath; }
    public static void setOldClassXmlPath(String oldClassXmlPath) { DataPath.oldClassXmlPath = oldClassXmlPath; }

    public static String getNewClassXmlPath() { return newClassXmlPath; }
    public static void setNewClassXmlPath(String newClassXmlPath) { DataPath.newClassXmlPath = newClassXmlPath; }

    public static String getOldClassPreProcessedPath() { return oldClassPreProcessedPath; }
    public static void setOldClassPreProcessedPath(String oldClassPreProcessedPath) { DataPath.oldClassPreProcessedPath = oldClassPreProcessedPath; }

    public static String getNewClassPreProcessedPath() { return newClassPreProcessedPath; }
    public static void setNewClassPreProcessedPath(String newClassPreProcessedPath) { DataPath.newClassPreProcessedPath = newClassPreProcessedPath; }

    public static String getOldMethodsPreProcessedPath() { return oldMethodsPreProcessedPath; }
    public static void setOldMethodsPreProcessedPath(String oldMethodsPreProcessedPath) { DataPath.oldMethodsPreProcessedPath = oldMethodsPreProcessedPath; }

    public static String getNewMethodsPreProcessedPath() { return newMethodsPreProcessedPath; }
    public static void setNewMethodsPreProcessedPath(String newMethodsPreProcessedPath) { DataPath.newMethodsPreProcessedPath = newMethodsPreProcessedPath; }

    public static String getOldRequirementsDivision() { return oldRequirementsDivision; }
    public static void setOldRequirementsDivision(String oldRequirementsDivision) { DataPath.oldRequirementsDivision = oldRequirementsDivision; }

    public static String getNewRequirementsDivision() { return newRequirementsDivision; }
    public static void setNewRequirementsDivision(String newRequirementsDivision) { DataPath.newRequirementsDivision = newRequirementsDivision; }

    public static String getOldRequirementsPreProcessedPath() {
        return oldRequirementsPreProcessedPath;
    }
    public static void setOldRequirementsPreProcessedPath(String oldRequirementsPreProcessedPath) { DataPath.oldRequirementsPreProcessedPath = oldRequirementsPreProcessedPath; }

    public static String getNewRequirementsPreProcessedPath() { return newRequirementsPreProcessedPath; }
    public static void setNewRequirementsPreProcessedPath(String newRequirementsPreProcessedPath) { DataPath.newRequirementsPreProcessedPath = newRequirementsPreProcessedPath; }

    public static String getAssociationRelationshipPath() { return associationRelationshipPath; }
    public static void setAssociationRelationshipPath(String associationRelationshipPath) { DataPath.associationRelationshipPath = associationRelationshipPath; }

    public static String getExtendsRelationship() { return extendsRelationship; }
    public static void setExtendsRelationship(String extendsRelationship) { DataPath.extendsRelationship = extendsRelationship; }
}
