package CLI;

import CLI.KNearestNeighbor.KNearestNeighborDriver;
import CLI.NearestNeighbor.NearestNeighborDriver;
import CLI.SimulatedAnnealing.SimulatedAnnealingDriver;
import Models.ClassifierResult;
import Models.Digit;
import Utils.Parser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) throws IOException
    {
        ArrayList<Digit> dataSet = new ArrayList<>(loadDataSet("DataSets/DataSet1.csv"));

        NearestNeighborDriver nearestNeighborDriver = new NearestNeighborDriver(dataSet);

        ArrayList<Digit> testingDataSet = new ArrayList<>(loadDataSet("DataSets/DataSet2.csv"));

        KNearestNeighborDriver kNearestNeighborDriver = new KNearestNeighborDriver(dataSet , 5);


        for(Digit dgt : testingDataSet)
        {
            ArrayList<ClassifierResult> classifierResults = new ArrayList<>(kNearestNeighborDriver.getKNearestNeighbor(dgt));
            int i = 0;
            for (ClassifierResult classifierResult :classifierResults)
            {
                System.out.println("result at index: " + i + " Result Class: " + classifierResult.getDigitClass() + " Actual Class: " + dgt.getDigitClass());
                i++;
            }
        }




    }


    private static ArrayList<Digit> loadDataSet(String path) throws IOException {
        Parser parser = new Parser();
        String rawDataSet = parser.loadFile(path , Charset.defaultCharset());

        if(rawDataSet == null)
        {
            throw new IOException();
        }

        List<List<String>> data = parser.parseString(rawDataSet , ",");
        ArrayList<Digit> dataSet = new ArrayList<>();

        for (int i = 0; i< data.size() ; i++)
        {
            dataSet.add( new Digit(data.get(i).get(data.get(i).size()-1) , data.get(i) ) );
        }
        return dataSet;
    }
}
//99.45612401664513
 /*

        int matches = 0;
        for(int i = 0; i <testingDataSet.size() ; i++)
        {
            int digitClass = nearestNeighborDriver.getNearestNeighbor(testingDataSet.get(i));

           // System.out.println("Nearest Neighbor: actual digit class: " +testingDataSet.get(i).getDigitClass() + " result digit class: " + digitClass );//digitClass
            if(testingDataSet.get(i).getDigitClass()  == digitClass)
            {
                matches++;
            }
        }
        double accuracy =  matches  * 100 / testingDataSet.size();
        System.out.println("Accuracy: " + accuracy );
        System.out.println("Size: " + (testingDataSet.size() - 1));
        System.out.println("Matches: " + (matches - 1));



        matches = 0;
        for(int i = 0; i <testingDataSet.size() ; i++)
        {
            SimulatedAnnealingDriver simulatedAnnealingDriver = new SimulatedAnnealingDriver(dataSet);
            int classifierResult = simulatedAnnealingDriver.getClosestMatch(testingDataSet.get(i));
            System.out.println("classifier Result: actual digit class: " +testingDataSet.get(i).getDigitClass() + " result digit class: " + classifierResult );

            if(testingDataSet.get(i).getDigitClass()  == classifierResult)
            {
                matches++;
            }
        }
        double accuracySa =  matches  * 100 / testingDataSet.size();
        System.out.println("Accuracy: " + accuracySa );
        System.out.println("Size: " + (testingDataSet.size() - 1));
        System.out.println("Matches: " + (matches - 1));*/