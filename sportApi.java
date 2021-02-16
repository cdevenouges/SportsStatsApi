import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class sportsApi {

    public static void SportsStats(){
        //Create a HTTP Connection.
        String baseUrl = "https://www.thesportsdb.com/api/v1/json/1/all_sports.php";
        String callAction = "/1/all_sports/";
        String sportsId = "102";
        String apiKey = "1";
        String urlString = baseUrl + callAction + sportsId + "?api_key=" + apiKey + "&language=en-US";
        URL url;
        try{
            //Make connection.
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //Examine  the response code.
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.printf("Error: Could not load sports information: " + status);
            } else {
                // Parsing input Stream into a text string
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                // Close the connections.
                in.close();
                con.disconnect();
                // Print out our complete JSON string.
                System.out.println("Output: " + content.toString());
                // Parse that object into a usable Java JSON object.
                JSONObject obj = new JSONObject(content.toString());

                // Print out the movie name.
                String SportDescription = obj.getString("strSportDescription");
                System.out.println("Sports Stats: " + SportDescription);
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex);
            return;
        }
    }
}


