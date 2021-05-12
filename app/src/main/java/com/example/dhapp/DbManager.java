package com.example.dhapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import javax.xml.namespace.QName;

public class DbManager extends SQLiteOpenHelper {

    static SQLiteDatabase db;


    public DbManager(Context context) {
        super(context,
                "stockDB.db",
                null,
                1);
        Log.d("hallo1234", this.toString());
        db=getWritableDatabase();
        //db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("marcsLog", this.toString());

        try {

            db.execSQL("CREATE TABLE name (" +
                    "nameID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "symbole TEXT NOT NULL, " +
                    "stockname TEXT)"
            );

            db.execSQL("CREATE TABLE depot (" +
                    "depotID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "symbol TEXT NOT NULL," +
                    "open TEXT NOT NULL," +
                    "change TEXT NOT NULL)"
            );
            db.execSQL("CREATE INDEX depot_index ON depot(symbol)");
            db.execSQL("INSERT INTO depot (name, symbol) VALUES ('Apple', 'AAPl')");


            db.execSQL("CREATE INDEX name_index ON name(symbole)");

            db.execSQL("CREATE TABLE value ( " +
                    "valueID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "symb TEXT NOT NULL," +
                    "value INTEGER," +
                    "marketCap INTEGER," +
                    "volume INTEGER," +
                    "FOREIGN KEY (symb) REFERENCES name(symbole) )"
            );

            db.execSQL("CREATE INDEX value_index ON value(symb)");

            db.execSQL("INSERT INTO value (symb, value, marketCap, volume) VALUES ('APPL', 108, 1850000000, 6000)");
            db.execSQL("INSERT INTO value (symb, value, marketCap, volume) VALUES ('SAP', 1, 2, 3)");
            db.execSQL("INSERT INTO value (symb, value, marketCap, volume) VALUES ('TSLA', 4, 5, 6)");

            db.execSQL("INSERT INTO name (symbole, stockname) VALUES ('APPL', 'Apple_Inc')");
            db.execSQL("INSERT INTO name (symbole, stockname) VALUES ('SAP', 'SAP SE ADR')");
            db.execSQL("INSERT INTO name (symbole, stockname) VALUES ('TSLA', 'Tesla Inc.')");

            Log.e("dbCorrect", db.getPath());

        } catch (SQLException e) {
            Log.d("dbFail", "Exception bei Create Methode" + e);
        }
    }

    public void addDepotElement(String elementName, String elementSymbol, String elementOpen, String elementChange){
        db.execSQL("INSERT INTO depot (name, symbol, open, change) VALUES ('" + elementName + "', '" + elementSymbol + "', '" + elementOpen + "', '" + elementChange + "')");

    }

    public void deleteHistoryElement(int id) {
        db.execSQL("DELETE FROM value WHERE valueID="+id);
    }

    public void addHistoryElement(int id, String symbole, int val, int market, int vol) {
        db.execSQL("INSERT INTO value (valueID, symb, value, marketCap, volume) VALUES (id, symbole, val, market, vol)");
    }

    public String[] ausgabeAktie() throws SQLException {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT valueID, symb, value " +
                        " FROM value " +
                        " ORDER BY valueID ASC",
                null);

// Ergebnis der Query auswerten
        int anzahlErgebnisZeilen = cursor.getCount();
        if (anzahlErgebnisZeilen == 0) {
            return new String[]{};
        }

        String[] resultStrings = new String[anzahlErgebnisZeilen];
        int counter = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            resultStrings[counter] = cursor.getString(0);
            counter++;
        }

        cursor.close();

        return resultStrings;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //leer
    }

    public String[] getElements(String ColumnName)  {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT "+ ColumnName + " FROM depot", null);
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

    public void deleteHistoryElement(int id) {
        db.execSQL("DELETE FROM value WHERE valueID="+id);
    }
}
