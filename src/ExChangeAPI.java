import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExChangeAPI {
    private String result;
    private int time_last_update_unix;
    private String time_last_update_utc;
    private int time_next_update_unix;
    private String time_next_update_utc;
    private String base_code;
    private JSONObject eschRate;

    private  static String url_API ="https://v6.exchangerate-api.com/v6/a8cbb056da994718d625795f/latest/";
    private static JSONObject jsonObject;

    public boolean getConnection(String base_code){
        String json = "";
        URL url =null;
        HttpURLConnection request = null;

        try {
            url = new URL(url_API + base_code);
            request = (HttpURLConnection) url.openConnection();
            request.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();

            if (line.length() >0){
                json += line;
            }
            jsonObject = new JSONObject(json);
            if (jsonObject ==null){
                return false;
            }
            this.result = jsonObject.getString("result");
            this.base_code = jsonObject.getString("base_code");
            this.eschRate = jsonObject.getJSONObject("conversion_rates");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;

    }

    public JSONObject getEschRate() {
        return eschRate;
    }

    public String getResult() {
        return result;
    }

    public String getBase_code() {
        return base_code;
    }

    public double getEschRate(String newCurrency) {
        double eachRate = 0.0;
        try {
            eachRate = this.eschRate.getDouble(newCurrency);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  eachRate;

    }
}


