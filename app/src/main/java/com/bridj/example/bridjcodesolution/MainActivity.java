package com.bridj.example.bridjcodesolution;

import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bridj.example.bridjcodesolution.entities.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity {
    ArrayList<Event> eventsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            FetchEventsTask fetchEventsTask = new FetchEventsTask();
            fetchEventsTask.execute();
        } else {
            savedInstanceState.getParcelableArrayList("events");
        }

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

    static class FetchEventsTask extends AsyncTask<Boolean, Boolean, Boolean> {

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        @Override
        protected Boolean doInBackground(Boolean... booleans) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
