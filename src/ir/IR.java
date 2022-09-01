package ir;

import document.*;
import object.similarObject;

import java.util.ArrayList;
import java.util.List;

public class IR {
    public static void compute(TextDataset textDataset, String modelType) {
        try {
            // 动态加载和创建Class对象，根据用户输入的字符串来创建对象：
            Class modelTypeClass = Class.forName(modelType);
            IRModel irModel = (IRModel) modelTypeClass.newInstance();
            // 计算得到IR候选追踪矩阵
            SimilarityMatrix similarityMatrix = irModel.Compute(textDataset.getSourceCollection(),
                    textDataset.getTargetCollection());
            System.out.print(similarityMatrix);

            for (SingleLink link : similarityMatrix.allLinks()) {
                String source = link.getSourceArtifactId();
                String target = link.getTargetArtifactId();
                // System.out.println(source+" "+target+" "+link.getScore());

                similarityMatrix.addLink(source, target, link.getScore());
                // if (textDataset.getRtm().sourceArtifactsIds().contains(source) &&
                // textDataset.getRtm().targetArtifactsIds().contains(target)) {
                // similarityMatrix.addLink(source, target, link.getScore());
                // }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No such IR model exists");
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static List<similarObject> culSimilar(TextDataset textDataset, String modelType) {
        List<similarObject> res = new ArrayList<>();

        try {
            // 动态加载和创建Class对象，根据用户输入的字符串来创建对象：
            Class modelTypeClass = Class.forName(modelType);
            IRModel irModel = (IRModel) modelTypeClass.newInstance();
            // 计算得到IR候选追踪矩阵
            SimilarityMatrix similarityMatrix = irModel.Compute(textDataset.getSourceCollection(),
                    textDataset.getTargetCollection());

            for (SingleLink link : similarityMatrix.allLinks()) {
                String source = link.getSourceArtifactId();
                String target = link.getTargetArtifactId();
                if (link.getScore() > 0.5) {
                    similarObject object = new similarObject(source, target, link.getScore());
                    res.add(object);
                    // System.out.println(source+" "+target+" "+link.getScore());
                }

                // System.out.println(source+" "+target+" "+link.getScore());

                similarityMatrix.addLink(source, target, link.getScore());
                // if (textDataset.getRtm().sourceArtifactsIds().contains(source) &&
                // textDataset.getRtm().targetArtifactsIds().contains(target)) {
                // similarityMatrix.addLink(source, target, link.getScore());
                // }

            }
        } catch (ClassNotFoundException e) {
            System.out.println("No such IR model exists");
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<similarObject> culNotSimilar(TextDataset textDataset, String modelType) {
        List<similarObject> res = new ArrayList<>();

        try {
            // 动态加载和创建Class对象，根据用户输入的字符串来创建对象：
            Class modelTypeClass = Class.forName(modelType);
            IRModel irModel = (IRModel) modelTypeClass.newInstance();
            // 计算得到IR候选追踪矩阵
            SimilarityMatrix similarityMatrix = irModel.Compute(textDataset.getSourceCollection(),
                    textDataset.getTargetCollection());

            for (SingleLink link : similarityMatrix.allLinks()) {
                String source = link.getSourceArtifactId();
                String target = link.getTargetArtifactId();
                if (link.getScore() < 0.5) {
                    similarObject object = new similarObject(source, target, link.getScore());
                    res.add(object);
                    // System.out.println(source+" "+target+" "+link.getScore());
                }

                // System.out.println(source+" "+target+" "+link.getScore());

                similarityMatrix.addLink(source, target, link.getScore());
                // if (textDataset.getRtm().sourceArtifactsIds().contains(source) &&
                // textDataset.getRtm().targetArtifactsIds().contains(target)) {
                // similarityMatrix.addLink(source, target, link.getScore());
                // }

            }
        } catch (ClassNotFoundException e) {
            System.out.println("No such IR model exists");
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }
}
