package com.bridj.example.bridjcodesolution.api;

import com.bridj.example.bridjcodesolution.entities.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class APIClient {
    private final static String EVENTS_DATA_URL = "https://s3-ap-southeast-2.amazonaws.com/bridj-coding-challenge/events.json";

    public static List<Event> getEvents() throws APIException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(APIClient.EVENTS_DATA_URL)
                .get()
                .addHeader("Cache-Control", "no-cache")
                .build();

        ResponseBody body = null;
        try {
            Response response = client.newCall(request).execute();
            if(response.code() != 200) {
                throw new APIException("Non 200 http code returned with message " + response.message());
            }
            body = response.body();
            if(body == null) {
                throw new APIException("null body");
            }
            JSONObject res = new JSONObject(body.string());
            JSONArray jEvents = res.getJSONArray("events");
            List<Event> ret = new ArrayList<>();
            for (int i = 0; i<jEvents.length(); i++) {
                ret.add(new Event(jEvents.getJSONObject(i)));
            }
            return ret;
        } catch (IOException e) {
            throw new APIException(e.getMessage(), e);
        } catch (JSONException e) {
            throw new APIException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new APIException(e.getMessage(), e);
        } finally {
            if(body != null) {
                body.close();
            }
        }
    }
}
