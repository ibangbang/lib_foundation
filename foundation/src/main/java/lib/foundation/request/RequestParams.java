package lib.foundation.request;

import org.json.JSONException;
import org.json.JSONObject;


public class RequestParams {

    private JSONObject data;

    private RequestParams() {
        data = new JSONObject();
    }

    public static RequestParams newInstance() {
        return new RequestParams();
    }

    public void put(String key, String value) {
        try {
            data.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void remove(String key) {
        data.remove(key);
    }

    public String getParams() {
        return data.toString();
    }

}
