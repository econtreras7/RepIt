package com.example.ezequielcontreras.repit;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
