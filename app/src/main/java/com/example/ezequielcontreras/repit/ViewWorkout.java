package com.example.ezequielcontreras.repit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dgist on 4/24/2016.
 */
public class ViewWorkout extends AppCompatActivity {

    CardView cardView;
    TextView textView;
    ImageView image1,image2;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_workout);
        cardView = (CardView)findViewById(R.id.card_view);
        textView =  (TextView)findViewById(R.id.TextView);
        image1 = (ImageView)findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image2);
        db = new DBHelper(this);

       image1.setImageResource(R.drawable.barbell_squats1);
        image2.setImageResource(R.drawable.barbell_squats2);
        textView.setText("Barbell Squats 5x8");

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewWorkout.this,"the . . . . test",Toast.LENGTH_LONG).show();
            }
        });

    }
}
