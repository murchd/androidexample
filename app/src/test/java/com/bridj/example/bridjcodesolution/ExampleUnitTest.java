package com.bridj.example.bridjcodesolution;

import com.bridj.example.bridjcodesolution.dao.EventDAO;
import com.bridj.example.bridjcodesolution.entities.Event;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static Event eventWithNoSeats;
    private static Event eventWithSeats;
    private static Event eventWithLabelPlay;
    private static Event eventWithNoLabelPlay;
    private static List<Event> events = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() {
        eventWithNoSeats = new Event("Old Event 1234", new Date(1541810148000L), 0, 10.99, "My Place", Arrays.asList("here", "now"));
        eventWithSeats = new Event("New Event", new Date(1542242148000L), 17, 5.99, "My Place", Arrays.asList("here", "now"));
        eventWithLabelPlay = new Event("Test 123", new Date(1532242148000L), 0, 7.99, "My Place", Arrays.asList("play", "now"));
        eventWithNoLabelPlay = new Event("Test 123 987", new Date(1530242148000L), 0, 1.99, "My Place", Arrays.asList("here", "now"));
        events.add(eventWithNoSeats);
        events.add(eventWithSeats);
    }


    @Test
    public void testHasSeatsIs() {
        assertTrue(eventWithSeats.hasAvailableSeats());
    }

    @Test
    public void testHasNoSeatsIs() {
        assertFalse(eventWithNoSeats.hasAvailableSeats());
    }

    @Test
    public void testHasPlayLabel() {
        assertTrue(eventWithLabelPlay.hasLabel("play"));
    }
    @Test
    public void testHasPlayLabelCase() {
        assertTrue(eventWithLabelPlay.hasLabel("Play"));
    }

    @Test
    public void testHasNoPlayLabel() {
        assertFalse(eventWithNoLabelPlay.hasLabel("play"));
    }

    @Test
    public void testSortEventsByDate() {
        EventDAO dao = new EventDAO();
        dao.sortByDateDesc(events);
        assertEquals(eventWithSeats, events.get(0));
    }
}