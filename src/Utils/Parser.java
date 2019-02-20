package Utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public String loadFile(String path, Charset encoding)
    {
        byte[] encoded;
        try{
            encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);
        } catch (IOException e) {
            //Logger.logIOException(e);
            return null;
        }
    }

    public List<List<String>> parseString(String text, String regex)
    {
        String[] lines = text.split("\\r?\\n");
        List<List<String>> parsedArray = new ArrayList<>();

        for(int x = 0; x < lines.length; ++x)
        {
            List<String> list = Arrays.asList(lines[x].split(regex));
            parsedArray.add(list);
        }
        return parsedArray;
    }

}
