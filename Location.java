import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Location
{
    private long time;
    private double latitude;
    private double longitude;
    private double latConfidence;
    private double longConfidence;
    private String display_name;
    private int buildingNumber;
    private String streetName;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    public Location()
    {
        this.time = 0;
        this.latitude = 0;
        this.longitude = 0;
        this.latConfidence = 0;
        this.latConfidence = 0;
        System.out.println("Invalid");
    }

    public Location(long time, double latitude, double longitude)
    {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.buildingNumber = 0;
        this.streetName = "STREET_NAME";
        this.city = "CITY";
        this.state = "STATE";
        this.zipcode = "ZIP_CODE";
        this.country = "COUNTRY";
    }

    public Location(Long time)
    {
        this.time = time;
    }

    public void setTime(String time)
    {
        this.time = Long.parseLong(time);
    }

    public void setLatitudeX(String latitude)
    {
        this.latitude = Long.parseLong(latitude);
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitudeX(String longitude)
    {
        this.longitude = Long.parseLong(longitude);
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return this.latitude;
    }

    public double getLongitude()
    {
        return this.longitude;
    }

    public String toString()
    {
        return String.format(
                        "Time: " + new Date(this.time)
                                        + "\t\t Latitude: %8.6f \t\t Longitude: %9.6f",
                        this.latitude, this.longitude);
    }

    public String toStringSimple()
    {
        return String.format("Time: " + new Date(this.time) + "\t Location: %8.6f , %9.6f",
                        this.latitude, this.longitude);
    }

    public String getAddy(double latitude, double longtitude)
    {
        return "";
    }

    public String getAddy() throws IOException, ArrayIndexOutOfBoundsException
    {
        String tempLat = "" + this.latitude;
        String tempLong = "" + this.longitude;
        URL url = new URL("https://us1.locationiq.org/v1/reverse.php?key=9df0bcdb935f91&lat="
                        + tempLat + "&lon=" + tempLong + "&format=json");
        // System.out.println(url.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
        {
            content.append(inputLine);
        }
        con.disconnect();
        String jsonFile = content.toString();
        String addressInfo = jsonFile.split(",\"address\":\\{\"")[1].split(",\"country_code")[0];
        if (addressInfo.contains("house_number"))
            this.buildingNumber = Integer.parseInt(
                            addressInfo.split("house_number\":\"")[1].split("\",\"road\"")[0]);
        if (addressInfo.contains("road"))
            this.streetName = addressInfo.split("\"road\":\"")[1]
                            .split("\",\"neighbourhood\":\"")[0];
        if (addressInfo.contains("city"))
            this.city = addressInfo.split("\",\"city\":\"")[1].split("\",\"state\":\"")[0];
        else if (addressInfo.contains("county"))
            this.city = addressInfo.split("\",\"county\":\"")[1].split("\",\"state\":\"")[0];
        if (addressInfo.contains("state"))
            this.state = addressInfo.split("\",\"state\":\"")[1].split("\",\"postcode\":\"")[0];
        if (addressInfo.contains("country"))
            this.country = addressInfo.split("\",\"country\":\"")[1].split("\"")[0];
        if (addressInfo.contains("postcode"))
            this.zipcode = addressInfo.split("\",\"postcode\":\"")[1].split("\",\"country\"")[0];
        // System.out.println("========================\n\n");
        // System.out.println(this.buildingNumber + this.streetName + this.city + this.state
        // + this.zipcode + this.country);
        // System.out.println("Building number: " + this.buildingNumber);
        // System.out.println("Street Name: " + this.streetName);
        // System.out.println("City name: " + this.city);
        // System.out.println("State name: " + this.state);
        // System.out.println("Zip code: " + this.zipcode);
        // System.out.println("Country name:" + this.country);
        // System.out.println("========================");
        // System.out.println(jsonFile);
        this.display_name = jsonFile.split("display_name\":\"")[1].split("\",\"address\"")[0];
        return (this.buildingNumber + " " + this.streetName + " " + this.city + ", " + this.state
                        + " " + this.zipcode + " " + this.country);
    }
}
