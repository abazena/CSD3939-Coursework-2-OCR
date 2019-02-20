package CLI;

import Models.Digit;
import Utils.Parser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) throws IOException
    {

        Parser parser = new Parser();
        String rawDataSet = parser.loadFile("DataSets/DataSet1.csv" , Charset.defaultCharset());

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
    }
}
