package document;

import util.FileIOUtil;

import java.io.File;

public class CreateArtifactsCollection {
    // 异常终止
    public static void abort(String m) {
        System.err.println(m);
        // 强制输出数据来清空缓存
        System.err.flush();
        // Thread.dumpStack() 方法打印当前线程的堆栈跟踪到标准错误流
        Thread.dumpStack();
        // 非正常退出程序
        System.exit(1);
    }

    // 工件中文件的id只有文件名没有后缀！！
    public static ArtifactsCollection getCollections(String dirPath, String postfixName) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            abort("Artifacts directory doesn't exist");
        }

        if (!dirFile.isDirectory()) {
            abort("Artifacts path should be a directory");
        }

        ArtifactsCollection collections = new ArtifactsCollection();
        for (File f : dirFile.listFiles()) {
            if (f.getName().endsWith(postfixName)) {
                String id = f.getName().substring(0, f.getName().indexOf("."));
                // warning!! jsp文件名 存在“-”字符的编码问题
                id = id.replace("‐", "-");

                Artifact artifact = new Artifact(id, FileIOUtil.readFile(f.getPath()));
                collections.put(id, artifact);
            }
        }
        return collections;
    }

    public static ArtifactsCollection getCollectionsWithoutSameName(String name,String dirPath, String postfixName) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            abort("Artifacts directory doesn't exist");
        }

        if (!dirFile.isDirectory()) {
            abort("Artifacts path should be a directory");
        }

        ArtifactsCollection collections = new ArtifactsCollection();
        for (File f : dirFile.listFiles()) {
            if (f.getName().endsWith(postfixName)) {
                if(!f.getName().equals(name)) {
                    String id = f.getName().substring(0, f.getName().indexOf("."));
                    // warning!! jsp文件名 存在“-”字符的编码问题
                    id = id.replace("‐", "-");

                    Artifact artifact = new Artifact(id, FileIOUtil.readFile(f.getPath()));
                    collections.put(id, artifact);
                }
            }
        }
        return collections;
    }


    //参数：文件名，预处理完成的结果文件
    public static ArtifactsCollection getSingleFileCollections(String name,String preprocessDirPath) {
        File dirFile = new File(preprocessDirPath);

        if (!dirFile.exists()) {
            abort("Artifacts directory doesn't exist");
        }

        if (!dirFile.isDirectory()) {
            abort("Artifacts path should be a directory");
        }

        ArtifactsCollection collections = new ArtifactsCollection();

        if(name.contains(".")){
            name = name.substring(0,name.indexOf("."));
        }
        // 预处理文件因为是.txt文件，因此可以进行字符串截取，但是name未必带后缀，所以要判断
        for (File file : dirFile.listFiles()) {
            if ((file.getName().substring(0, file.getName().indexOf("."))).equals(name)) {
                String id = file.getName().substring(0, file.getName().indexOf("."));
                // warning!! jsp文件名 存在“-”字符的编码问题
                id = id.replace("‐", "-");

                Artifact artifact = new Artifact(id, FileIOUtil.readFile(file.getPath()));
                collections.put(id, artifact);
            }
        }
        return collections;
    }
}