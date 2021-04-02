package com.example.dhapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                    "symb INTEGER PRIMARY KEY," +
                    "value INTEGER," +
                    "marketCap INTEGER," +
                    "volume INTEGER," +
                    "FOREIGN KEY (symb) REFERENCES name(symbole) )"
                    );



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
