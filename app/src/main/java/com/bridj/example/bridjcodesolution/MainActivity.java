package com.bridj.example.bridjcodesolution;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.bridj.example.bridjcodesolution.api.APIException;
import com.bridj.example.bridjcodesolution.dao.EventDAO;
import com.bridj.example.bridjcodesolution.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Event> eventsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            refreshEventList();
        } else {
            eventsList = savedInstanceState.getParcelableArrayList("events");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refreshEventList();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshEventList() {
        FetchEventsTask fetchEventsTask = new FetchEventsTask();
        fetchEventsTask.execute();
    }

    private void displayEvents() {
        logToConsole("*** Events (with available seats) ***");
        for(Event e : eventsList) {
            logToConsole(e.toString());
        }
        logToConsole("*** Events (with available seats and label of 'play') ***");
        for(Event e : eventsList) {
            if(e.hasLabel("play")) {
                logToConsole(e.toString());
            }
        }
    }

    private void logToConsole(String str) {
        Log.d("[Bridj Example]", str);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("events", eventsList);
        super.onSaveInstanceState(outState);
    }

    private class FetchEventsTask extends AsyncTask<Boolean, Boolean, Boolean> {

        @Override
        protected void onPostExecute(Boolean success) {
            if(success) {
                MainActivity.this.displayEvents();
            }
        }

        @Override
        protected Boolean doInBackground(Boolean... booleans) {
            EventDAO dao = new EventDAO();
            try {
                List<Event> results = dao.getEventsWithAvailableSeats();
                dao.sortByDateDesc(results);
                MainActivity.this.eventsList.clear();
                MainActivity.this.eventsList.addAll(results);
                return true;
            }catch (APIException ex) {
                ex.printStackTrace();
            }
            return false;

        }
    }


}
