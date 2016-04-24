package com.example.ezequielcontreras.repit;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by dgist on 4/23/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "RepIt.db";
    public static final String DATABASE_PATH =
            "/data/data/com.example.ezequielcontreras.repit/";

    SQLiteDatabase myDatabase;
    private final Context myContext;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null,1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDatabase();
        if(!dbExist){
            this.getReadableDatabase();
            try{
                copyDataBase();
            }catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //database dne
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true :false;
    }

    private void copyDataBase() throws IOException{

        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH+DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int size;

        while((size = myInput.read(buffer))>0){
            myOutput.write(buffer,0,size);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLException {
        String myPath = DATABASE_PATH+DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public  ArrayList<String> workoutList(){

        Cursor res=null;
        SQLiteDatabase db=null;

        ArrayList<String> result= new ArrayList<String>();
        try{
            String query = "SELECT DISTINCT b_name FROM body,base_workouts WHERE b_tpe=b_w_id";
            db = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            res = db.rawQuery(query, null);
            while(res.moveToNext()) {
                result.add(res.getString(0));
            }
        }
        catch(Exception e) {
            Log.d("DB", e.getMessage());
        }finally {
            db.close();
            res.close();
        }
        return result;
    }
    public  ArrayList<String> getWorkout(String muscle){

        Cursor res=null;
        SQLiteDatabase db=null;
        ArrayList<String> result= new ArrayList<String>();
        try{
            String query = "SELECT b_w_name,b_w_reps,image,e_description\n" +
                    "FROM body, base_workouts,exercise,pictures\n" +
                    "WERE b_w_type=e_type and\n" +
                    "    e_type=b_type and\n" +
                    "    exercise.e_id=pictures.e_id and\n" +
                    "    b_name Like '%"+ muscle +"%'\n" +
                    "    GROUP BY b_w_name";
            db = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            res = db.rawQuery(query, null);
            while(res.moveToNext()) {
                result.add(res.getString(0));
            }
        }
        catch(Exception e) {
            Log.d("DB", e.getMessage());
        }finally {
            db.close();
            res.close();
        }
        return result;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
