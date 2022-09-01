package document;

import java.util.ArrayList;

public class LinksList extends ArrayList<SingleLink> {

    public LinksList() {
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (SingleLink link : this) {
            sb.append(link);
            sb.append("\n");
        }
        return sb.toString();
    }

    // 更新相似度评分
    public void updateLink(String source, String target, Double score) {
        for (SingleLink link : this) {
            if (link.getSourceArtifactId().equals(source)
                    && link.getTargetArtifactId().equals(target)) {
                link.setScore(score);
            }
        }
    }

    // 获取相似度评分
    public double getScore(String source, String target) {
        for (SingleLink link : this) {
            if (link.getSourceArtifactId().equals(source)
                    && link.getTargetArtifactId().equals(target)) {
                return link.getScore();
            }
        }
        return -1;
    }
}
