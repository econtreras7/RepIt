package com.example.ezequielcontreras.repit;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    Button repit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repit = (Button)findViewById(R.id.RepIt);

        db = new DBHelper(this);

        try{
            db.createDataBase();
        }catch (IOException ioe){
            throw new Error("Can't create databse");
        }

        try{
            db.openDatabase();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        repit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Workouts.class);
                startActivity(myIntent);
                MainActivity.this.finish();
            }
        });


    }

}
