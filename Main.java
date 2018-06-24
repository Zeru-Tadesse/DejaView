import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class Main
{
    //TO DO todo todo todo todo todo tdooooo
    /**
     * Location.getAddy() parser for county needs correcting
     * Attach image to canvas
     * 
     */
    public static void main(String[] args) throws DataFormatException, IOException
    {
        double minLong = 1000;
        double maxLong = -1000;
        double minLat = 1000;
        double maxLat = -1000;
        double startTime = System.currentTimeMillis();
        // getLocationsZeru();
        // getLocationsTadesse();
        ArrayList<Location> locs = new ArrayList<Location>();
        locs = getLocationsZeru();
        ArrayList<Location> nearby = new ArrayList<Location>();
        Location home = new Location(0, 38.8578620, -77.1021790);
        nearby = LocationTools.getNearBy(home, locs);
        double endTime = System.currentTimeMillis();
        System.out.println("Home count: " + nearby.size());
        System.out.println("Operation took: " + (endTime - startTime) + "Ms.");
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
        // StdDraw.setXscale(-120, 60);
        // StdDraw.setYscale(-15, 60);
        StdDraw.setXscale(-79.55, -76.32);
        StdDraw.setYscale(37.83, 39.535);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(-120, 0, 120, 0);
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.line(0, -120, 0, 120);
        StdDraw.setPenColor(StdDraw.BLACK);
        // StdDraw.setPenRadius(.01);
        for (int i = locs.size() - 1; i > 1; i--)
        {
            StdDraw.setPenRadius();
            StdDraw.point(locs.get(i).getLongitude(), locs.get(i).getLatitude());
            StdDraw.setPenRadius(.00001);
            StdDraw.line(locs.get(i).getLongitude(), locs.get(i).getLatitude(),
                            locs.get(i - 1).getLongitude(), locs.get(i - 1).getLatitude());
            if (i % 10000 == 0 || i == locs.size() - 1)
                System.out.println(locs.get(i).toStringSimple() + " \t" + locs.get(i).getAddy());
        }
        // StdDraw.clear();
    }

    public static ArrayList<Location> getLocationsZeru()
                    throws FileNotFoundException, DataFormatException
    {
        return LocationReader.readFromFile("zeru.txt", "locZSimple.txt");
    }

    public static void getLocationsTadesse() throws FileNotFoundException, DataFormatException
    {
        LocationReader.readFromFile("tadesse.txt", "locTSimple.txt");
    }
}
