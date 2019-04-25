package sample.apis;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Scanner;

public class apiSource {
    private String inline = "";
    private String apiUrl;

    public apiSource(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getInline() {
        return inline;
    }

    public void setInline(String inline) {
        this.inline += inline;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void useApi() {
        try {
            URL url = new URL(getApiUrl());
            //Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            //Set the request to GET or POST as per the requirements
            conn.setRequestMethod("GET");

            //Use the connect method to create the connection bridge
            conn.connect();

            //Get the response status of the Rest API
            int responsecode = conn.getResponseCode();
            System.out.println("Response code is: " + responsecode);

            //Iterating condition to if response code is not 200 then throw a runtime exception
            //else continue the actual process of getting the JSON data
            if (responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            else {

                //Scanner functionality will read the JSON data from the stream
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    setInline(sc.nextLine());
                }

                //Close the stream when reading the data has been finished
                sc.close();
            }

            //Disconnect the HttpURLConnection stream
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Message: " + e.getMessage());
        }

    }

    public newsApi deserialize(newsApi napi) {

        Gson gson = new Gson();
        napi = gson.fromJson(this.getInline(), newsApi.class);

        return napi;
    }

}
