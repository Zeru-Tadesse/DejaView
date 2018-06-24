import java.util.ArrayList;

public class LocationTools
{
    public static ArrayList<Location> getNearBy(Location location, ArrayList<Location> list)
    {
        ArrayList<Location> nearby = new ArrayList<Location>();
        for (Location loc : list)
        {
            if (Math.abs(location.getLatitude() - loc.getLatitude()) <= .0007)
            {
                if (Math.abs(location.getLongitude() - loc.getLongitude()) <= .0009)
                {
                    nearby.add(loc);
                }
            }
        }
        return nearby;
    }
}
