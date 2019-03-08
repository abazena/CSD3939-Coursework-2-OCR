package CLI;

import CLI.KNearestNeighbor.KNearestNeighborDriver;
import CLI.NearestNeighbor.NearestNeighborDriver;
import Models.Digit;
import Utils.Parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException
    {

        int userInput = -1;

        do{

            String algorithmMenu = "Please select algorithm to use: \n 1. Nearest Neighbor \n 2. K-Nearest Neighbor \n 3. Simulated Annealing";
            System.out.println(algorithmMenu);
            userInput = getUserInputInt();

        }while (userInput == -1);


        int selectedAlgorithmindex = -1;
        int K = 1;
        switch (userInput)
        {
            case 1:
                selectedAlgorithmindex = 1;
                break;
            case 2:
                selectedAlgorithmindex = 2;
                do{
                    System.out.println("Please enter value of K");
                    K = getUserInputInt();
                }while (K == -1);
                break;
            case 3:
                selectedAlgorithmindex = 3;
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Unrecognised option");
                break;
        }

        String dataSetsFileNames;
        int trainingDataSetIndex = -1;
        while (true)
        {
            String trainingDataSetMenu = "Please select data-sets to use for training:";
            System.out.println(trainingDataSetMenu);
            dataSetsFileNames = getAvailableDataSets();
            System.out.println(dataSetsFileNames);

            trainingDataSetIndex = getUserInputInt();
            if(trainingDataSetIndex != -1)
            {
                break;
            }
        }
        int testingDataSetIndex = -1;
        while (true)
        {
            String testingDataSetMenu = "Please select data-sets to use for testing:";
            System.out.println(testingDataSetMenu);
            dataSetsFileNames = getAvailableDataSets();
            System.out.println(dataSetsFileNames);

            testingDataSetIndex = getUserInputInt();
            if(testingDataSetIndex != -1)
            {
                if(trainingDataSetIndex == testingDataSetIndex)
                {
                    System.out.println("Testing and training data-sets should not be the same");
                }
                else
                {
                    break;
                }
            }
        }

        while (true)
        {
            System.out.println("Please select classification method: \n 1. Classify the full data-set. \n 2. Classify a single record by index.");
            userInput = getUserInputInt();
            if(userInput == 1)
            {
                execute();
                //run then break
                break;
            }
            else if (userInput == 2)
            {
                //run then break
                break;
            }
        }


        ArrayList<Digit> dataSet = new ArrayList<>(loadDataSet("DataSets/DataSet1.csv"));

        NearestNeighborDriver nearestNeighborDriver = new NearestNeighborDriver(dataSet);

        ArrayList<Digit> testingDataSet = new ArrayList<>(loadDataSet("DataSets/DataSet2.csv"));

        KNearestNeighborDriver kNearestNeighborDriver = new KNearestNeighborDriver(dataSet , 5);


        for(Digit dgt : testingDataSet)
        {
            break;
            /*KNearestNeighborResult dgtResults = kNearestNeighborDriver.getKNearestNeighbor(dgt);
            for (int i = 0; i< dgtResults.getResultStats().length; i++)
            {
                System.out.println("Class: " + i + " Confidence " + dgtResults.getResultStats()[i] + "%");
            }*/

        }




    }

    private static void execute(int algorithmIndex , String trainingDataPath , String testingDataPath , int runningIndex, boolean saveResults)
    {
        ArrayList<Digit> dataSet = new ArrayList<>(loadDataSet("DataSets/" +trainingDataPath));
        ArrayList<Digit> testingDataSet = new ArrayList<>(loadDataSet("DataSets/" +testingDataPath));

        if(runningIndex > -1)
        {

        }
        else
        {

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
;
    private static int getUserInputInt()
    {
         Scanner scanner = new Scanner(System.in);
         try
         {
             int userInput = Integer.parseInt(scanner.nextLine());
             if(userInput == 0 )
             {
                 return -1;
             }
             return userInput;
         }catch(Exception ex)
         {
             System.out.println("Error: Illegal input format!");
             scanner.close();
             return -1;
         }
    }

    private static String getAvailableDataSets()
    {
        String dataSets = "Available DataSets: ";
        File folder = new File("DataSets/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                dataSets += "\n " + (i+1) + ". " + listOfFiles[i].getName();
            }
        }

        return dataSets;
    }
}
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