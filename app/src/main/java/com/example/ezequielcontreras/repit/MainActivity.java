package com.example.ezequielcontreras.repit;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }

}
