package com.bridj.example.bridjcodesolution.entities;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Event implements Parcelable {

    private final String name;
    private final Date date;
    private final int availableSeats;
    private final double price;
    private final String venue;
    private final List<String> labels;

    public Event(JSONObject json) throws JSONException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ", Locale.ENGLISH);
        name = json.getString("name");
        date = sdf.parse(json.getString("date"));
        availableSeats = json.getInt("available_seats");
        price = json.getDouble("price");
        venue = json.getString("venue");
        labels = new ArrayList<>();
        JSONArray jLabels = json.getJSONArray("labels");
        for(int i = 0; i<jLabels.length(); i++){
            labels.add(jLabels.getString(i).toLowerCase());
        }
    }

    public Event(String name, Date date, int availableSeats, double price, String venue, List<String> labels) {
        this.name = name;
        this.date = date;
        this.availableSeats = availableSeats;
        this.price = price;
        this.venue = venue;
        this.labels = labels;
    }

    protected Event(Parcel in) {
        name = in.readString();
        availableSeats = in.readInt();
        price = in.readDouble();
        venue = in.readString();
        labels = in.createStringArrayList();
        date = new Date(in.readLong());
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

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

    public boolean hasAvailableSeats() {
        return getAvailableSeats() > 0;
    }

    public boolean hasLabel(String label) {
        return getLabels().contains(label.toLowerCase());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(availableSeats);
        dest.writeDouble(price);
        dest.writeString(venue);
        dest.writeStringList(labels);
        dest.writeLong(date.getTime());
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", venue='" + getVenue() + '\'' +
                '}';
    }
}
