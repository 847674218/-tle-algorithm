package preprocess;

import util.*;

import java.io.File;

public class DataPreprocess {
    // 清理目录下的数据
    private void cleanData() {
        deleteFileInDir(DataPath.getClassNamePath());
        deleteFileInDir(DataPath.getOldClassPath());
        deleteFileInDir(DataPath.getNewClassPath());
        deleteFileInDir(DataPath.getMethodsNamePath());
        deleteFileInDir(DataPath.getOldMethodsPath());
        deleteFileInDir(DataPath.getNewMethodsPath());
        deleteFileInDir(DataPath.getOldClassTxtPath());
        deleteFileInDir(DataPath.getNewClassTxtPath());
//        deleteFileInDir(DataPath.getOldClassXmlPath());
//        deleteFileInDir(DataPath.getNewClassXmlPath());
//        deleteFileInDir(DataPath.getAssociationRelationshipPath());
        deleteFileInDir(DataPath.getOldClassPreProcessedPath());
        deleteFileInDir(DataPath.getNewClassPreProcessedPath());
        deleteFileInDir(DataPath.getOldMethodsPreProcessedPath());
        deleteFileInDir(DataPath.getNewMethodsPreProcessedPath());
        deleteFileInDir(DataPath.getOldRequirementsDivision());
        deleteFileInDir(DataPath.getNewRequirementsDivision());
        deleteFileInDir(DataPath.getOldRequirementsPreProcessedPath());
        deleteFileInDir(DataPath.getNewRequirementsPreProcessedPath());
    }

    // 删除目录下的文件
    private void deleteFileInDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
    }

    public void arrangeData() {
        try {
            cleanData();
            // 拆分需求文档：把每条需求拆分为一个文件
            RequirementsDivisionUtil.requirementsDivision(DataPath.getOldRequirementsPath(),DataPath.getOldRequirementsDivision());
            RequirementsDivisionUtil.requirementsDivision(DataPath.getNewRequirementsPath(),DataPath.getNewRequirementsDivision());
            // 提取java文件：把项目中的java和jsp文件复制到目标路径下
            GetClassFileUtil.getClassFromProject(DataPath.getOldProjectPath(),DataPath.getOldClassPath());
            GetClassFileUtil.getClassFromProject(DataPath.getNewProjectPath(),DataPath.getNewClassPath());
            // 得到java文件的文件名（以.java结尾）
            GetFileNameUtil.getClassNameFromProject(DataPath.getOldClassPath(),DataPath.getClassNamePath(),"oldClassName.txt");
            GetFileNameUtil.getClassNameFromProject(DataPath.getNewClassPath(),DataPath.getClassNamePath(),"newClassName.txt");
            // 把java文件转化成txt文件
            TransferToTxtUtil.transferTXT(DataPath.getOldClassPath(), DataPath.getOldClassTxtPath());
            TransferToTxtUtil.transferTXT(DataPath.getNewClassPath(), DataPath.getNewClassTxtPath());
            // 把java文件转化为xml文件（srcML）

            // 生成类之间的依赖关系（callGraph）

            // 把类文件拆分成方法文件
            BreakClassIntoMethods.breakClassIntoMethods(DataPath.getOldClassXmlPath(),DataPath.getOldMethodsPath());
            BreakClassIntoMethods.breakClassIntoMethods(DataPath.getNewClassXmlPath(),DataPath.getNewMethodsPath());
            // 得到方法的名称列表（java文件：方法名）
            GetFileNameUtil.getMethodsNameFromProject(DataPath.getOldMethodsPath(),DataPath.getMethodsNamePath(),"oldMethodName.txt");
            GetFileNameUtil.getMethodsNameFromProject(DataPath.getNewMethodsPath(),DataPath.getMethodsNamePath(),"newMethodName.txt");
            // 预处理：整理成词干的形式
            BatchingPreprocess oldPreprocess = new BatchingPreprocess(DataPath.getOldRequirementsDivision(), DataPath.getOldRequirementsPreProcessedPath(),DataPath.getOldClassTxtPath(), DataPath.getOldClassPreProcessedPath(), DataPath.getOldMethodsPath(),DataPath.getOldMethodsPreProcessedPath());
            oldPreprocess.doProcess();
            BatchingPreprocess newPreprocess = new BatchingPreprocess(DataPath.getNewRequirementsDivision(), DataPath.getNewRequirementsPreProcessedPath(),DataPath.getNewClassTxtPath(), DataPath.getNewClassPreProcessedPath(), DataPath.getNewMethodsPath(),DataPath.getNewMethodsPreProcessedPath());
            newPreprocess.doProcess();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
