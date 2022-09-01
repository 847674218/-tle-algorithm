package object;

import java.util.List;

public class ScenariosResult {
    double scenariosScore;
    List<String> targetResult;
    boolean isEarlyTermination;

    public ScenariosResult(double scenariosScore,List<String> targetRequirement){
        this.scenariosScore = scenariosScore;
        this.targetResult = targetRequirement;
        this.isEarlyTermination = false;
    }

    public ScenariosResult() {

    }

    public double getScenariosScore() { return scenariosScore; }
    public void setScenariosScore(double scenariosScore) { this.scenariosScore = scenariosScore; }

    public List<String> getTargetResult() { return targetResult; }
    public void setTargetResult(List<String> targetResult) { this.targetResult = targetResult; }

    public boolean isEarlyTermination() { return isEarlyTermination; }
    public void setEarlyTermination(boolean earlyTermination) { isEarlyTermination = earlyTermination; }
}
