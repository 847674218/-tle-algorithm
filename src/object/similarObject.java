package object;
// 建立源代码与目标代码的相似度关系
public class similarObject {
    String origin;
    String target;
    double similar;

    public similarObject(String origin, String target, double similar) {
        this.origin = origin;
        this.target = target;
        this.similar = similar;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getSimilar() {
        return similar;
    }

    public void setSimilar(double similar) {
        this.similar = similar;
    }
}
