package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static void logIOException(IOException exception) {

        String exceptionData = exception.getMessage();
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        String fileName =("IOException:"+new Timestamp(new Date().getTime())).replace('.' , ',');
        String fileContent = "Exception Msg: " + exceptionData +"\n" + "Exception Time: " + date;
        writeToFile(fileName , fileContent);

    }
    private static void writeToFile(String fileName , String content)
    {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File file = new File(s+"/Logs/"+fileName+".txt");

        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException ex) {

            ex.printStackTrace();
            System.out.println();
        }


    }
}
