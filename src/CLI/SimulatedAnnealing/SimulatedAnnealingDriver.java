package CLI.SimulatedAnnealing;

import Models.Digit;
import Utils.Utility;

import java.io.IOException;
import java.util.ArrayList;


public class SimulatedAnnealingDriver {

    public SimulatedAnnealingDriver( ArrayList<Digit> digitsBank)
    {
        DIGITS_BANK = digitsBank;
    }
    private static double TEMPERATURE = 1000000;
    private static double COOLING_RATE = 0.0003;
    private static ArrayList<Digit> DIGITS_BANK = new ArrayList<>();



    public int getClosestMatch (Digit digit) {

        Digit currentDigit = DIGITS_BANK.get(0);

        Digit bestMatchDigit = DIGITS_BANK.get(0);
        int digitClass  = -1;

        while (TEMPERATURE > 1){

            int digitOneIndex = Utility.randomIntInRange(0 , DIGITS_BANK.size() -1);

            Digit newDigit = DIGITS_BANK.get(digitOneIndex);

            double distanceToDigitOne = Utility.calculateDistance(digit , currentDigit);
            double distanceToDigitTwo = Utility.calculateDistance(digit , newDigit);

            double randomDouble = Utility.randomDoubleInRange();
            if(Utility.calculateAcceptanceProbability(distanceToDigitOne, distanceToDigitTwo , TEMPERATURE) > randomDouble){
                currentDigit = newDigit;
            }
            if( distanceToDigitOne > distanceToDigitTwo){
                digitClass =  newDigit.getDigitClass();
            }
            TEMPERATURE *= 1 - COOLING_RATE;
        }
        return digitClass;

    }
}
