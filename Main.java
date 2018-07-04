import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * 
 * @author Zeru Tadesse
 * @version 07/04/2018 V1.2
 * @fix lat/long input streams
 * @update: pressing on the canvas clears screen
 *
 */
public class Main
{
    @SuppressWarnings("all")
    public static void main(String[] args) throws DataFormatException, IOException
    {
        double minLong = 1000;
        double maxLong = -1000;
        double minLat = 1000;
        double maxLat = -1000;
        double startTime = System.currentTimeMillis();
        ArrayList<Location> locs = new ArrayList<Location>();
        locs = getLocationsTade();
        ArrayList<Location> nearby = new ArrayList<Location>();
        double endTime = System.currentTimeMillis();
        System.out.println("Read Time: " + (endTime - startTime) / 1000 + " seconds.");
        for (int i = 0; i < locs.size(); i++)
        {
            if (locs.get(i).getLatitude() < minLat)
            {
                minLat = locs.get(i).getLatitude();
            } else if (locs.get(i).getLatitude() > maxLat)
            {
                maxLat = locs.get(i).getLatitude();
            }
            if (locs.get(i).getLongitude() < minLong)
            {
                minLong = locs.get(i).getLongitude();
            } else if (locs.get(i).getLongitude() > maxLong)
            {
                maxLong = locs.get(i).getLongitude();
            }
        }
        StdDraw.setCanvasSize(720, 720);
        StdDraw.setXscale(-79.55, -76.32);
        StdDraw.setYscale(37.83, 39.535);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(-120, 0, 120, 0);
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.line(0, -120, 0, 120);
        StdDraw.setPenColor(StdDraw.BLACK);
        double zoomXmin = -79.55;
        double zoomXmax = -76.32;
        double zoomYmin = 37.83;
        double zoomYmax = 39.535;
        for (int i = locs.size() - 1; i > 1; i--)
        {
            if (StdDraw.isMousePressed())
            {
                StdDraw.clear();
            }
            StdDraw.setPenRadius(.0005);
            StdDraw.point(locs.get(i).getLongitude(), locs.get(i).getLatitude());
            StdDraw.setPenRadius(.00001);
            StdDraw.line(locs.get(i).getLongitude(), locs.get(i).getLatitude(),
                            locs.get(i - 1).getLongitude(), locs.get(i - 1).getLatitude());
            if (i % 10000 == 0 || i == locs.size() - 1)
                System.out.println(locs.get(i).toStringSimple() + " \t" + locs.get(i).getAddy());
        }
    }

    public static ArrayList<Location> getLocationsZeru()
                    throws FileNotFoundException, DataFormatException
    {
        return LocationReader.readFromFile("zeru.txt", "locZSimple.txt");
    }

    public static ArrayList<Location> getLocationsTade()
                    throws FileNotFoundException, DataFormatException
    {
        return LocationReader.readFromFile("tadesse.txt", "locTSimple.txt");
    }

    public static void getLocationsTadesse() throws FileNotFoundException, DataFormatException
    {
        LocationReader.readFromFile("tadesse.txt", "locTSimple.txt");
    }
}
