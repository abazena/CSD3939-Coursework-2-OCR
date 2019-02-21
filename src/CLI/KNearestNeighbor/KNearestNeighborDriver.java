package CLI.KNearestNeighbor;

import Models.ClassifierResult;
import Models.Digit;
import Utils.Utility;

import java.util.ArrayList;

public class KNearestNeighborDriver {

    private static ArrayList<Digit> DIGITS_BANK;
    private static int K;

    public KNearestNeighborDriver(ArrayList<Digit> digitsBank , int k)
    {
        DIGITS_BANK = new ArrayList<>(digitsBank);
        K = k;
    }
    public ArrayList<ClassifierResult> getKNearestNeighbor(Digit digit)
    {
        ArrayList<ClassifierResult> classifierResults = new ArrayList<>();

        // loop thru the training data-set
        for(Digit dgt : DIGITS_BANK)
        {
            //calculate the distance between the passed digit and the current index of the loop
            double newDistance = Utility.calculateDistance(digit, dgt);

            classifierResults.add(new ClassifierResult(dgt.getDigitClass() , newDistance));
        }

        return new ArrayList<>( Utility.sortResults(classifierResults).subList(0 , K));
    }

}
