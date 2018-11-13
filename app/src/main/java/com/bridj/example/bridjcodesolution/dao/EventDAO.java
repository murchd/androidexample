package com.bridj.example.bridjcodesolution.dao;

import android.support.annotation.NonNull;

import com.bridj.example.bridjcodesolution.api.APIClient;
import com.bridj.example.bridjcodesolution.api.APIException;
import com.bridj.example.bridjcodesolution.entities.Event;

import java.util.List;

public class EventDAO {

    public List<Event> getAllEvents() throws APIException {
        return APIClient.getEvents();
    }

    public List<Event> filterWithAvailableSeats(List<Event> events) throws APIException {
        List<Event> events = getAllEvents();
    }

    private Comparable<Event> sortByDate = new Comparable<Event>() {
        @Override
        public int compareTo(@NonNull Event o) {
            return 0;
        }
    }

}
