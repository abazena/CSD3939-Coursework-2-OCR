package Models;

import java.util.ArrayList;

public class KNearestNeighborResult {

    private ArrayList<ClassifierResult> KResults;
    private int finalClass;
    private int[] resultStats;
    private int multiplier;

    public KNearestNeighborResult(ArrayList<ClassifierResult> results , int multiplier)
    {
        this.KResults = new ArrayList<>(results);
        this.resultStats = new int[10];
        this.multiplier = multiplier;
        this.getFinalResult();
    }

    private void getFinalResult()
    {
        int[] classes = new int[KResults.size()];

        for(int i = 0; i< KResults.size() ; i++)
        {
            classes[i] = KResults.get(i).getDigitClass();
        }
        int[] duplicates = new int[10];
        for (int i = 0; i < duplicates.length; i++)
        {
            duplicates[i] = getDuplicates(classes , i);
        }

        int confidence = 0 ;
        for(int i = 0; i <duplicates.length; i++)
        {
            if(duplicates[i] > confidence)
            {
                confidence = duplicates[i];
                finalClass = i;
            }
            resultStats[i] = duplicates[i] * multiplier;
        }

    }

    private int getDuplicates(int arr[], int DuplicateNum)
    {
        int numOfDuplicatesFound = 0;

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == DuplicateNum)
            {
                numOfDuplicatesFound++;
            }
        }
        return numOfDuplicatesFound;
    }

    public ArrayList<ClassifierResult> getKResults() {
        return KResults;
    }

    public int getFinalClass() {
        return finalClass;
    }

    public int[] getResultStats() {
        return resultStats;
    }
}
