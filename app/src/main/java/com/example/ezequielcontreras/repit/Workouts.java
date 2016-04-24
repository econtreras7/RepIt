package com.example.ezequielcontreras.repit;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dgist on 4/24/2016.
 */
public class Workouts extends ListActivity {

    Button custom;
    ListView workouts;
    ArrayAdapter<String> workoutAdapter;
    static final String[] muscles = {"Chest","Arms","Back","Legs","Shoulders","Abs"};
    ArrayList<String> lista = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        custom = (Button)findViewById(R.id.custom);
        workoutAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lista);
        setListAdapter(workoutAdapter);
        lista.addAll(Arrays.asList(muscles));
        workoutAdapter.notifyDataSetChanged();

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*Intent myIntent = new Intent(Workouts.this, .);
                    startActivity(myIntent);*/
            }
        });



    }

    @Override
    protected void onListItemClick (ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        String Data = l.getItemAtPosition(position).toString();

        //String Data="deffwrgr";
        Intent myIntent = new Intent(Workouts.this,ViewWorkout.class);
        myIntent.putExtra("extra", Data);
        startActivity(myIntent);
    }
}
