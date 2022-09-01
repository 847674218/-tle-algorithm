package document;

public class SingleLink implements Comparable<SingleLink> {

    private String sourceArtifactId;
    private String targetArtifactId;
    private Double score;

    public SingleLink(String sourceArtifactId, String targetArtifactId, Double score) {
        this.setSourceArtifactId(sourceArtifactId);
        this.setTargetArtifactId(targetArtifactId);
        this.setScore(score);
    }

    public String getSourceArtifactId() {
        return sourceArtifactId;
    }

    public void setSourceArtifactId(String sourceArtifactId) {
        this.sourceArtifactId = sourceArtifactId;
    }

    public String getTargetArtifactId() {
        return targetArtifactId;
    }

    public void setTargetArtifactId(String targetArtifactId) {
        this.targetArtifactId = targetArtifactId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public int compareTo(SingleLink sl) {
        return getScore().compareTo(sl.getScore());
    }

    @Override
    public int hashCode() {
        int sourceArtifactHash = sourceArtifactId.hashCode();
        int targetArtifactHash = targetArtifactId.hashCode();
        int probabilityHash = score.hashCode();

        int hash = sourceArtifactHash ^ targetArtifactHash ^ probabilityHash;

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        SingleLink other = (SingleLink) object;
        if (other != null) {
            return ((sourceArtifactId.equals(other.sourceArtifactId)
                    && (targetArtifactId.equals(other.targetArtifactId)
                    && (score.equals(other.score)))));
        }
        return false;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(sourceArtifactId);
        sb.append(" ");
        sb.append(targetArtifactId);
        sb.append(" ");
        sb.append(score);
        return sb.toString();
    }
}

