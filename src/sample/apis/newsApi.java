package sample.apis;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Scanner;

public class newsApi {

    private int responseCode;
    private String apiUrl;
    private String inline = "";

    public newsApi(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    private int getrCode() {
        return responseCode;
    }

    private void setrCode(int responseCode) {
        this.responseCode = responseCode;
    }

    private String getInline() {
        return inline;
    }

    private void setInline(String inline) {
        this.inline += inline;
    }

    public JSONArray getJSONResult() {

        try {
//            Setting up api url.
            URL url = new URL(this.apiUrl);

//            Parsing url into HttpsConnection in order to open connection in order to get JSON data.
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

//            Setting api request type.
            conn.setRequestMethod("GET");

//            Creating a connection bridge.
            conn.connect();

//            Get response status of the Rest API.
            setrCode(conn.getResponseCode());
            System.out.println("Response Code: " + this.getrCode());

//          Throw exceptions when response code != 200 (Okay Response).

            if (getrCode() != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

//                Scanner functionality to read the JSON data from the stream.
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    setInline(sc.nextLine());
                }

                System.out.println("JSON response in String format.");
                System.out.println(getInline());

//                Closing the stream when reading the data has been finished.
                sc.close();
            }

//            Creating a JSONParser that takes a string and breaks it down into key value pairs.
            JSONParser parse = new JSONParser();

//            Type casting the parsed line into a json object.
            JSONObject jObject = (JSONObject) parse.parse(getInline());

//            Stores the JSON Object in JSON arrays as objects (For level 1 array elements i.e Results)
            JSONArray jsonArray = (JSONArray) jObject.get("results");

            //Get data for Results array
            for (Object o : jsonArray) {
                //Store the JSON objects in an array
                //Get the index of the JSON object and print the values as per the index
                JSONObject jsonobj_1 = (JSONObject) o;


                //Store the JSON object in JSON array as objects (For level 2 array element i.e Address Components)
                JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("address_components");
                System.out.println("Elements under results array");
                System.out.println("\nPlace id: " + jsonobj_1.get("place_id"));
                System.out.println("Types: " + jsonobj_1.get("types"));


                //Get data for the Address Components array
                System.out.println("Elements under address_components array");
                System.out.println("The long names, short names and types are:");

                for (Object o1 : jsonarr_2) {
                    //Same just store the JSON objects in an array
                    //Get the index of the JSON objects and print the values as per the index
                    JSONObject jsonobj_2 = (JSONObject) o1;
                    //Store the data as String objects
                    String str_data1 = (String) jsonobj_2.get("long_name");
                    System.out.println(str_data1);
                    String str_data2 = (String) jsonobj_2.get("short_name");
                    System.out.println(str_data2);
                    System.out.println(jsonobj_2.get("types"));
                    System.out.println("\n");
                }

            }

//            Disconnect the HttpURLConnection Stream.
//            ----------------------------------------

            conn.disconnect();

            return jsonArray;

        } catch (Exception event) {
            event.printStackTrace();
        }
        return null;
    }
}
