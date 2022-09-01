package preprocess;

import util.FileIOUtil;

import java.io.File;

// 批处理程序
public class BatchingPreprocess {
    private File ucPath;
    private File classTxtPath;
    private File methodTxtPath;
    private File ucPreProcessedPath;
    private File classProcessedPath;
    private File methodProcessedPath;

    public BatchingPreprocess(String ucPath, String ucPreProcessedPath, String classTxtPath, String classProcessedPath, String methodTxtPath, String methodProcessedPath) {
        this.ucPath = new File(ucPath);
        this.ucPreProcessedPath = new File(ucPreProcessedPath);
        this.classTxtPath = new File(classTxtPath);
        this.classProcessedPath = new File(classProcessedPath);
        this.methodTxtPath = new File(methodTxtPath);
        this.methodProcessedPath = new File(methodProcessedPath);
    }

    public void doProcess() {
        preprocessUCFiles(ucPath);
        preprocessJavaAndJsPFiles(classTxtPath);
        preprocessMethodFiles(methodTxtPath);
    }

    public void preprocessUCFiles(File ucDirPath) {
        if (ucDirPath.isDirectory()) {
            for (File f : ucDirPath.listFiles()) {
                if (f.getName().endsWith(".txt")) {
                    TextPreprocess textPreprocessor = new TextPreprocess(FileIOUtil.readFile(f.getPath()));
                    FileIOUtil.writeFile(textPreprocessor.doUCFileProcess(), ucPreProcessedPath.getPath()+"/"+f.getName());
                }
            }
        }
    }

    private void preprocessJavaAndJsPFiles(File dirPath) {
        if (dirPath.isDirectory()) {
            for (File f : dirPath.listFiles()) {
                if (f.getName().endsWith("_jsp.txt")) {
                    TextPreprocess textPreprocessor = new TextPreprocess(FileIOUtil.readFile(f.getPath()));
                    FileIOUtil.writeFile(textPreprocessor.doJspFileProcess(), classProcessedPath.getPath()+"/"+f.getName());
                } else if (f.getName().endsWith(".txt")) {
                    TextPreprocess textPreprocessor = new TextPreprocess(FileIOUtil.readFile(f.getPath()));
                    FileIOUtil.writeFile(textPreprocessor.doJavaFileProcess(), classProcessedPath.getPath()+"/"+f.getName());
                }
            }
        }
    }

    private void preprocessMethodFiles(File dirPath) {
        if (dirPath.isDirectory()) {
            for (File f : dirPath.listFiles()) {
                if (f.getName().endsWith(".txt")) {
                    TextPreprocess textPreprocessor = new TextPreprocess(FileIOUtil.readFile(f.getPath()));
                    FileIOUtil.writeFile(textPreprocessor.doJavaFileProcess(), methodProcessedPath.getPath()+"/"+f.getName());
                }
            }
        }
    }

}
