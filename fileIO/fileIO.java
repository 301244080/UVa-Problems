// Working program using BufferedReader
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

 
public class fileIO
{
    // public static void main(String[] args) throws IOException
    // {
    //     // FileReader(String fileName)
    //     // Creates a new FileReader, given the name of the file to read from.
    //     List<String> strs = readFile("test.txt");
    //     System.out.println(strs);
    // }
    public static List<String> readFile(String filename)
    {
      List<String> records = new ArrayList<String>();
      try
      {
        
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null)
        {
          records.add(line);
        }
        reader.close();
        return records;
      }
      catch (Exception e)
      {
        System.err.format("Exception occurred trying to read '%s'.", filename);
        e.printStackTrace();
        return null;
      }
    }
}