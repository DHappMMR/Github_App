package com.example.dhapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbManager extends SQLiteOpenHelper {

    static SQLiteDatabase db;


    public DbManager(Context context) {
        super(context,
                "stockDB.db",
                null,
                1);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL("CREATE TABLE depot (" +
                    "depotID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "symbol TEXT NOT NULL," +
                    "open TEXT NOT NULL," +
                    "change TEXT NOT NULL)"
            );

            db.execSQL("CREATE INDEX depot_index ON depot(symbol)");

            db.execSQL("CREATE TABLE history (" +
                    "historyID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT)"
            );

            db.execSQL("CREATE INDEX history_index ON history(name)");


        } catch (SQLException e) { e.printStackTrace();}
    }

    public void addDepotElement(String elementName, String elementSymbol, String elementOpen, String elementChange){
        db.execSQL("INSERT INTO depot (name, symbol, open, change) VALUES ('" + elementName + "', '" + elementSymbol + "', '" + elementOpen + "', '" + elementChange + "')");
    }

    //TODO: SQL-Statement nicht erfolgreich, Aufruf scheint aber richtig zu sein
    public void deleteDepotElement(String name){
        db.execSQL("DELETE FROM depot WHERE name = '" + name + "'");
    }

    public void addHistoryElement(String historyName) {
        db.execSQL("INSERT INTO history (name) VALUES ('" + historyName + "')");
    }

    public void deleteHistoryElement(String historyverlauf) {
        db.execSQL("DELETE FROM history WHERE historyID IN (SELECT historyID FROM history WHERE name = '" + historyverlauf + "' LIMIT 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //leer
    }

    public String[] getElements(String ColumnName, String TableName) {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+ ColumnName + " FROM " + TableName, null);
        if (TableName.equals("depot")){
            cursor = db.rawQuery("SELECT DISTINCT "+ ColumnName + " FROM " + TableName, null);
        }
        int amountResultRows = cursor.getCount();
        if (amountResultRows == 0) {
            return new String[]{};
        }

        String[] resultValues = new String[amountResultRows];
        int counter = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            resultValues[counter] = cursor.getString(0);
            counter++;
        }

        cursor.close();
        return resultValues;
    }
}
