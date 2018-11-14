package com.bridj.example.bridjcodesolution.dao;

import com.bridj.example.bridjcodesolution.api.APIClient;
import com.bridj.example.bridjcodesolution.api.APIException;
import com.bridj.example.bridjcodesolution.entities.Event;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventDAO {

    public List<Event> getEventsWithAvaiableSeats() throws APIException {
        return new APIClient().getEvents(new Filterable<Event>() {

            @Override
            public boolean pass(Event o) {
                return o.getAvailableSeats() > 0;
            }

        });
    }

    public void sortByDate(List<Event> events) {
        Collections.sort(events, sortByDate);
    }

    private Comparator<Event> sortByDate = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };

}
