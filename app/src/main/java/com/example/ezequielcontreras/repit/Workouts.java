package com.example.ezequielcontreras.repit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by dgist on 4/24/2016.
 */
public class Workouts extends Activity{

    ListView workouts;
    ArrayAdapter<String> workoutAdapter;
    static final String[] muscles = {"Chest","Arms","Back","Legs","Shoulders","Abs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        workouts = (ListView)findViewById(R.id.workouts);
        workoutAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,muscles);
    }
}
