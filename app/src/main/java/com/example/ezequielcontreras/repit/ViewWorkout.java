package com.example.ezequielcontreras.repit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dgist on 4/24/2016.
 */
public class ViewWorkout extends AppCompatActivity {

    CardView cardView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_workout);
        cardView = (CardView)findViewById(R.id.card_view);
        textView =  (TextView)findViewById(R.id.TextView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("", "CardView clicked");
            }
        });

    }
}
