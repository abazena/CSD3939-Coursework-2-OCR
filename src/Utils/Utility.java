package Utils;

import Models.ClassifierResult;
import Models.Digit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Utility {

    /**
     * @description calculateDistance: this method calculates the Euclidean Distance between two images/bitmaps
     * @param digitOne:Digit image one/ bitmap one
     * @param digitTwo:Digit image two/ bitmap two
     * @return double: Euclidean Distance
     */
    public static double calculateDistance(Digit digitOne, Digit digitTwo)
    {
        //local reference to the bitmaps from the digits passed
        int[] digitOneBitmap = digitOne.getBitmaps();
        int[] digitTwoBitmap = digitTwo.getBitmaps();

        //temp var to hold the distance
        double euclideanDistance = 0.0;

        //loop thru the arrays
        for(int i = 0; i < digitOneBitmap.length; i++)
        {
            //calculate distance using Euclidean Distance using Euclidean formula
            euclideanDistance += Math.sqrt( Math.pow((digitOneBitmap[i] - digitTwoBitmap[i]), 2));

        }
        return euclideanDistance;
    }
    public static ArrayList<ClassifierResult> sortResults(ArrayList<ClassifierResult> resultsDump)
    {
        ArrayList<ClassifierResult> classifierResults = new ArrayList<>(resultsDump);
        classifierResults.sort(new SortByDistance());
        return classifierResults;
    }

    public static double calculateAcceptanceProbability(double currentDistance, double newDistance , double temperature){
        if(newDistance < currentDistance)
            return 1.0;
        return Math.exp((currentDistance - newDistance) / temperature);
    }
    public static double randomDoubleInRange(){
        return new Random().nextInt(1000 )/ 10000.0;
    }
    public static int randomIntInRange(int min , int max){
        if(min > max){
            throw new IllegalArgumentException("max must be greater than min");
        }
        return new Random().nextInt((max - min) + 1) + min;
    }
}



class SortByDistance implements Comparator<ClassifierResult>
{
    @Override
    public int compare(ClassifierResult o1, ClassifierResult o2) {
        return (int) (o1.getDistanceToClassifier() - o2.getDistanceToClassifier());
    }
}
