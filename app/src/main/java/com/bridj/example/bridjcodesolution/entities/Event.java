package com.bridj.example.bridjcodesolution.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Event {

    private final String name;
    private final Date date;
    private final int availableSeats;
    private final double price;
    private final String venue;
    private final List<String> labels = new ArrayList<>();

    public Event(JSONObject json) throws JSONException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ", Locale.ENGLISH);
        name = json.getString("name");
        date = sdf.parse(json.getString("date"));
        availableSeats = json.getInt("available_seats");
        price = json.getDouble("price");
        venue = json.getString("venue");
        JSONArray jLabels = json.getJSONArray("labels");
        for(int i = 0; i<jLabels.length(); i++){
            labels.add(jLabels.getString(i));
        }
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public String getVenue() {
        return venue;
    }

    public List<String> getLabels() {
        return labels;
    }
}
