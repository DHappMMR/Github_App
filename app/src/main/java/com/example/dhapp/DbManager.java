package com.example.dhapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import javax.xml.namespace.QName;

public class DbManager extends SQLiteOpenHelper {

    public DbManager (Context context){
        super (context,
                "stockDB",
                null,
                1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {


            db.execSQL("CREATE TABLE name (" +
                    "symbole TEXT PRIMARY KEY NOT NULL, " +
                    "stockname TEXT)"
            );

            db.execSQL("CREATE TABLE value ( " +
                    "symb TEXT PRIMARY KEY NOT NULL," +
                    "value INTEGER," +
                    "marketCap INTEGER," +
                    "volume INTEGER," +
                    "FOREIGN KEY (symb) REFERENCES name(symbole) )"
            );

            db.execSQL("INSERT INTO value (symb, value, marketCap, volume) VALUES (APPL, 108, 1850000000, 6000)");

            db.execSQL("INSERT INTO name (symbole, stockname) VALUES (APPL, Apple_Inc)");

            System.out.println("Correct");
            Log.e("dbCorrect", "Erfolgreich");

        } catch (SQLException e) {
            Log.d("dbFail", "Exception bei Create Methode" + e);
            System.out.println("Fail");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //leer
    }
}