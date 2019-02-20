package Models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Digit {

    private int actualValue;

    private List<Integer> bitmaps;

    public Digit(String actualValue, List<String> bitmaps)
    {
        this.actualValue = Integer.parseInt(actualValue);
        this.bitmaps = parseBitmaps(bitmaps);
    }

    private List<Integer> parseBitmaps(List<String> bitmaps)
    {
        List<Integer> parsedBitmaps = new ArrayList<>();
        for (int i = 0; i <bitmaps.size(); i++ )//printDigit
        {
            if(i == bitmaps.size() -1)
            {
                continue;
            }
            parsedBitmaps.add(Integer.parseInt(bitmaps.get(i)));
        }
        return parsedBitmaps;
    }

    public int getActualValue()
    {
        return actualValue;
    }

    public List<Integer> getBitmaps()
    {
        return bitmaps;
    }

    public void printDigit()
    {
        System.out.println("bitmaps: " + Arrays.toString(bitmaps.toArray()));
        System.out.println("Actual Value: " + actualValue);

    }
}
