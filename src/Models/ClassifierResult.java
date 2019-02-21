package Models;

public class ClassifierResult {

    private int digitClass;
    private double distanceToClassifier;


    public ClassifierResult( int digitClass, double distanceToClassifier )
    {
        this.digitClass = digitClass;
        this.distanceToClassifier = distanceToClassifier;
    }

    public int getDigitClass() {
        return digitClass;
    }

    public double getDistanceToClassifier() {
        return distanceToClassifier;
    }
}
