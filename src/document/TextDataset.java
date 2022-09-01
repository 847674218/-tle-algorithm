package document;

public class TextDataset {
    private ArtifactsCollection sourceCollection;
    private ArtifactsCollection targetCollection;
    // private SimilarityMatrix rtm;

    /**
     *
     * @param sourceDirPath: uc files' directory path
     * @param targetDirPath: class code files' directory path
     */

    // 多对多
    public TextDataset(String sourceDirPath, String targetDirPath) {
        this.setSourceCollection(CreateArtifactsCollection.getCollections(sourceDirPath, ".txt"));
        this.setTargetCollection(CreateArtifactsCollection.getCollections(targetDirPath, ".txt"));

    }

    // 一对多
    public TextDataset(String name, String preprocessDirPath, String targetDirPath) {
        this.setSourceCollection(CreateArtifactsCollection.getSingleFileCollections(name, preprocessDirPath));
        this.setTargetCollection(CreateArtifactsCollection.getCollections(targetDirPath, ".txt"));
    }

    public TextDataset(String name, String preprocessDirPath, String targetDirPath, boolean isAvoidSameName) {
        this.setSourceCollection(CreateArtifactsCollection.getSingleFileCollections(name, preprocessDirPath));
        this.setTargetCollection(CreateArtifactsCollection.getCollectionsWithoutSameName(name, targetDirPath, ".txt"));
    }

    public TextDataset(String name1, String path1, String name2, String path2) {
        this.setSourceCollection(CreateArtifactsCollection.getSingleFileCollections(name1, path1));
        this.setTargetCollection(CreateArtifactsCollection.getSingleFileCollections(name2, path2));
    }

    public ArtifactsCollection getSourceCollection() {
        return sourceCollection;
    }

    public void setSourceCollection(ArtifactsCollection sourceCollection) {
        this.sourceCollection = sourceCollection;
    }

    public ArtifactsCollection getTargetCollection() {
        return targetCollection;
    }

    public void setTargetCollection(ArtifactsCollection targetCollection) {
        this.targetCollection = targetCollection;
    }

}
