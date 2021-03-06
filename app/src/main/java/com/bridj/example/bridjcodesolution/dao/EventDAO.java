package com.bridj.example.bridjcodesolution.dao;

import com.bridj.example.bridjcodesolution.api.APIClient;
import com.bridj.example.bridjcodesolution.api.APIException;
import com.bridj.example.bridjcodesolution.entities.Event;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventDAO {

    /**
     * returns list of Events filtering out those with no available seats
     * @return the List of events
     * @throws APIException if there was a problem fetching the events from the data source
     */
    public List<Event> getEventsWithAvailableSeats() throws APIException {
        return new APIClient().getEvents(new Filterable<Event>() {

            @Override
            public boolean pass(Event o) {
                return o.hasAvailableSeats();
            }

        });
    }

    /**
     * Sort events by date
     * @param events the collection reference
     */
    public void sortByDateDesc(List<Event> events) {
        Collections.sort(events, sortByDateDesc);
    }

    // Comparator for sorting date
    private Comparator<Event> sortByDateDesc = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            if(o1.getDate().after(o2.getDate())) {
                return -1;
            }
            if(o1.getDate().before(o2.getDate())) {
                return 1;
            }
            return 0;
        }
    };

}
