import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;

public class AccesoAleatorio {
    public static String stream(String url) {
        try (InputStream input = new URL(url).openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String caturl = "https://catfact.ninja/facts";

        //parseamos un string a un JSONObject
        String jsonString = stream(caturl);
        JSONObject jo = new JSONObject(jsonString);
        JSONArray ja =  jo.getJSONArray("data");
        JSONObject jo2 = ja.getJSONObject(1);
        System.out.println(jo2.getString("fact"));


    }
}
