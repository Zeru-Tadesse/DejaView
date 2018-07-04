import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class LocationReader
{
    public static ArrayList<Location> readFromFile(String fileName, String newFile)
                    throws FileNotFoundException, DataFormatException
    {
        ArrayList<Location> locations = new ArrayList<Location>();
        try
        {
            BufferedReader br = null;
            String line;
            br = new BufferedReader(new FileReader(fileName));
            System.out.println("======================");
            System.out.println("Opening: " + fileName);
            System.out.println("======================\n\n");
            int count = 0;
            int limit = 0;
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            while ((line = br.readLine()) != null)
            {
                limit++;
            }
            br.close();
            br = new BufferedReader(new FileReader(fileName));
            line = "";
            while (count < limit && (line = br.readLine()) != null)
            {
                count++;
                if (line.contains("timestamp"))
                {
                    String[] timeLine = line.split("\"timestamp");
                    Location newLocation = new Location(
                                    Long.parseLong(timeLine[1].substring(7, 20)));
                    line = br.readLine();
                    count++;
                    if (line.contains("latitudeE7\" : "))
                    {
                        String[] latitudeLine = line.split("latitudeE7\" : ");
                        newLocation.setLatitude(Double.parseDouble(latitudeLine[1].split(",")[0])
                                        / 10000000);
                    }
                    line = br.readLine();
                    count++;
                    if (line.contains("longitudeE7\" : "))
                    {
                        String[] longitudeLine = line.split("longitudeE7\" : ");
                        newLocation.setLongitude(Double.parseDouble(longitudeLine[1].split(",")[0])
                                        / 10000000);
                        locations.add(newLocation);
                        // bw.write(newLocation.toStringSimple() + "\n");
                    }
                }
            }
            bw.close();
            br.close();
            return locations;
        } catch (IOException e)
        {
            throw new FileNotFoundException();
        }
    }
}
